package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.User;
import com.example.generator.mapper.UserMapper;
import com.example.generator.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.jsqlparser.statement.select.Select;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @Override
    public Map<String, Object> login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone,user.getPhone());
        wrapper.eq(User::getPassword,user.getPassword());
        User loginuser =this.baseMapper.selectOne(wrapper);
//      如果返回不为null,则生成token,并存入redis
        if(loginuser!=null) {
//          随机生成token
            String key="user:"+ UUID.randomUUID();
            Map<String,Object> data= new HashMap<>();
            data.put("token",key);
            return data;
        }
        return null;
    }
}
