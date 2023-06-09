package com.example.generator.service;

import com.example.generator.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.generator.entity.message.UserDetails;
import com.example.generator.entity.message.ChangePasswordMeg;
import com.example.generator.entity.message.RegisterMeg;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public interface IUserService extends IService<User> {

    Object login(User user);


    //注册--涉及增加
    String register(RegisterMeg registerMeg);

    String changePassword(ChangePasswordMeg changePasswordMeg);

    Map<String, Object> getUserInfo(String token);

    Integer getUserIdByToken(String token);

    User getUserByToken(String token);

    String userDelete(String phone);

    User getUserByPhone(String phone);

    User getUserByID(Integer id);

    String updateUser(Integer userId, String avatarUrl, String email, String name);
    List<UserDetails> getUserDetailsList(String name, String phone);

    Integer addUserspunishment(Integer postuserid);
}
