package com.example.generator.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.User;
import com.example.generator.entity.message.*;
import com.example.generator.mapper.UserMapper;
import com.example.generator.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    //登录--验证用户的账号和密码,并为用户生成token,存入redis
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

    //注册--涉及增加
    @Override
    public String register(RegisterMeg registerMeg) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, registerMeg.getPhone());
        User existingPhone = this.baseMapper.selectOne(wrapper);
        if (existingPhone != null) {
            return "该手机号已被注册";
        } 
        wrapper.eq(User::getEmail, registerMeg.getEmail());
        User existingEmail = this.baseMapper.selectOne(wrapper);
        if (existingEmail != null) {
            return "该邮箱已被注册";
        }
        if (!registerMeg.getPassword().equals(registerMeg.getPassword2())) {
            return "两次输入的密码不一致";
        }
        User user = new User(null, registerMeg.getPhone(), registerMeg.getEmail(), registerMeg.getPassword(), registerMeg.getName(), null, LocalDate.now());
        int result = this.baseMapper.insert(user);
        if (result > 0) {
            return "注册成功";
        } else {
            return "注册失败";
        }
    }
    @Override
    public String changePassword(ChangePasswordMeg changePasswordMeg) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, changePasswordMeg.getPhone());
        User existingPhone = this.baseMapper.selectOne(wrapper);
        if (existingPhone == null) {
            return "该手机号不存在";
        }
        if (!existingPhone.getEmail().equals(changePasswordMeg.getEmail())) {
            return "手机号与邮箱不匹配";
        }
        if (!changePasswordMeg.getPassword().equals(changePasswordMeg.getPassword2())) {
            return "两次输入的密码不一致";
        }
        existingPhone.setPassword(changePasswordMeg.getPassword());
        this.baseMapper.update(existingPhone, wrapper);
        return "密码修改成功";
    }
    // 验证token,并返回用户数据
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

    @Override
    public User getUserByToken(String token) {
        Object obj=redisTemplate.opsForValue().get(token);
        // 如果token不为空，那么调用fastjson2里面的方法实现反序列化
        if(obj!=null)
        {
            User user= JSON.parseObject(JSON.toJSONString(obj),User.class);
            return getUserByPhone(user.getPhone());
        }
        return null;
    }

    // 这个是给其他服务类实现的方法
    @Override
    public User getUserByPhone(String phone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, phone);
        User user = this.baseMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public User getUserByID(Integer id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserid, id);
        User user = this.baseMapper.selectOne(wrapper);
        return user;
    }


}
