package com.example.generator.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.Admin;
import com.example.generator.entity.User;
import com.example.generator.mapper.AdminMapper;
import com.example.generator.service.IAdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Map<String, Object> login(Admin admin) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getAccount,admin.getAccount());
        wrapper.eq(Admin::getPassword,admin.getPassword());
        Admin login_admin =this.baseMapper.selectOne(wrapper);
        // 如果返回不为null,则生成token,并存入redis
        if(login_admin!=null) {
            //  随机生成token
            String key="admin:"+ UUID.randomUUID();
            //  将 token 和 用户 作为键值对 存入redis
            //  设置 redis 的 过期时间是30分钟
            redisTemplate.opsForValue().set(key,login_admin,30, TimeUnit.MINUTES);

            Map<String,Object> data= new HashMap<>();
            data.put("token",key);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getAdminInfo(String modifiedString) {
        Object obj=redisTemplate.opsForValue().get(modifiedString);
        // 如果token不为空，那么调用fastjson2里面的方法实现反序列化
        if(obj!=null)
        {
            Admin admin= JSON.parseObject(JSON.toJSONString(obj),Admin.class);
            Map<String, Object> data=new HashMap<>();
            data.put("account",admin.getAccount());
            Map<String,Object> return_data=new HashMap<>();
            return_data.put("admin",data);
            return  return_data;
        }
        return null;
    }
}
