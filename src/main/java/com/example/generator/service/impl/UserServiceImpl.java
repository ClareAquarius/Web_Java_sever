package com.example.generator.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.User;
import com.example.generator.mapper.UserMapper;
import com.example.generator.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Map<String, Object> login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone,user.getPhone());
        wrapper.eq(User::getPassword,user.getPassword());
        User loginuser =this.baseMapper.selectOne(wrapper);
        // 如果返回不为null,则生成token,并存入redis
        if(loginuser!=null) {
            //  随机生成token
            String key="user:"+ UUID.randomUUID();
            //  将 token 和 用户 作为键值对 存入redis
            //  设置 redis 的 过期时间是30分钟
            redisTemplate.opsForValue().set(key,loginuser,30, TimeUnit.MINUTES);

            Map<String,Object> data= new HashMap<>();
            data.put("token",key);
            return data;
        }
        return null;
    }

    @Override
    public String register(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, user.getPhone());
        User existingUser = this.baseMapper.selectOne(wrapper);
        if (existingUser != null) {
            return "该手机号已被注册";
        } else {
            int result = this.baseMapper.insert(user);
            if (result > 0) {
                return "注册成功";
            } else {
                return "注册失败";
            }
        }
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        Object obj=redisTemplate.opsForValue().get(token);
        // 如果token不为空，那么调用fastjson2里面的方法实现反序列化
        if(obj!=null)
        {
            User user= JSON.parseObject(JSON.toJSONString(obj),User.class);
            Map<String, Object> data=new HashMap<>();
            data.put("name",user.getName());
            data.put("phone",user.getPhone());

            Map<String,Object> return_data=new HashMap<>();
            return_data.put("user",data);
            return  return_data;
        }
        return null;
    }

}
