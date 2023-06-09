package com.example.generator.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.generator.entity.User;
import com.example.generator.entity.message.UserDetails;
import com.example.generator.entity.message.*;
import com.example.generator.mapper.UserMapper;
import com.example.generator.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;


import static java.lang.System.out;

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
    public Object login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone,user.getPhone());
        wrapper.eq(User::getPassword,user.getPassword());
        User loginuser =this.baseMapper.selectOne(wrapper);
        if(loginuser==null) {
            return null;
        }
        // 检查是否被禁
        LocalDate currentDate = LocalDate.now();
        if(loginuser.getBanTime().isAfter(currentDate)) {
            return "你的账号已被封禁";
        }

        // 如果返回不为null,则生成token,并存入redis
        //  随机生成token
        String key="user:"+ UUID.randomUUID();
        //  将 token 和 用户 作为键值对 存入redis
        //  设置 redis 的 过期时间是30分钟
        redisTemplate.opsForValue().set(key,loginuser,30, TimeUnit.MINUTES);

        Map<String,Object> data= new HashMap<>();
        data.put("token",key);
        return data;
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
    @Override
    public String userDelete(String phone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, phone);
        User existingPhone = this.baseMapper.selectOne(wrapper);
        if (existingPhone == null) {
            return "该手机号不存在";
        }
        this.baseMapper.delete(wrapper);
        return "注销成功";
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
    public Integer getUserIdByToken(String token) {
        out.println(token);
        Object obj=redisTemplate.opsForValue().get(token);
        // 如果token不为空，那么调用fastjson2里面的方法实现反序列化
        if(obj!=null)
        {
            User user= JSON.parseObject(JSON.toJSONString(obj),User.class);
            out.println("obj不空！！");
            return user.getUserid();
        }
        out.println("obj空空");
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

    @Override
    public String updateUser(Integer userId, String avatarUrl, String email, String name) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserid, userId);
        User user = this.baseMapper.selectOne(wrapper);
        if (user == null) {
            return "用户不存在";
        } else {
            user.setProfile(avatarUrl);
            user.setEmail(email);
            user.setName(name);
            int result = this.baseMapper.update(user,wrapper);
            if (result > 0) {
                return "更新成功";
            } else {
                return "更新失败";
            }
        }
    }
    
    @Override
    public List<UserDetails> getUserDetailsList(String name, String phone) {
        List<User> userList=this.list();
        List<UserDetails> list =new ArrayList<>();
        for(User user:userList)
        {
            if (phone != "") {
                if (user.getPhone().equals(phone)) {
                    UserDetails userDetails=new UserDetails(user);
                    list.add(userDetails);
                    break;
                }
            } else if (name != "") {
                if (user.getName().equals(name)) {
                    UserDetails userDetails=new UserDetails(user);
                    list.add(userDetails);
                }
            } else {
                UserDetails userDetails=new UserDetails(user);
                list.add(userDetails);
            }
        }
        return list;
    }


    @Override
    public Integer addUserspunishment(Integer postuserid) {
        LocalDate currentDate = LocalDate.now();  // 当前日期
        User user = this.baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserid, postuserid));
        // 获取当前处罚次数
        Integer punishNum = user.getPunishnum()+1;
        LocalDate banTime = user.getBanTime();
        // 第n次处罚 禁n天
        if (banTime != null && banTime.isAfter(currentDate)) {
            banTime = banTime.plusDays(punishNum);
        } else {
            banTime = currentDate.plusDays(punishNum);
        }
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUserid, postuserid)
                .setSql("punishnum = punishnum + 1")
                .set(User::getBanTime, banTime);
        this.update(null, wrapper);
        return punishNum;
    }
}
