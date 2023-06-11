package com.example.generator.service;

import com.example.generator.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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

    Map<String, Object> login(User user);

    String register(RegisterMeg registerMeg);

    String changePassword(ChangePasswordMeg changePasswordMeg);

    Map<String, Object> getUserInfo(String token);

    User getUserByToken(String token);

    Integer getUser(String token);

    User getUserByPhone(String phone);

    User getUserByID(Integer id);

    String updateUser(Integer userId, String avatarUrl, String email, String name);
}
