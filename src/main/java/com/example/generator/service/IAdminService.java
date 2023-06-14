package com.example.generator.service;

import com.example.generator.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.generator.entity.message.*;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public interface IAdminService extends IService<Admin> {

    Map<String, Object> login(Admin admin);

    Map<String, Object> getAdminInfo(String modifiedString);

    boolean searchAdminByAccount(String account);

    void changePasswordByAccount(String account, String password1);

    void deleteAdminByAccount(String account);

    void addAdmin(String account, String password1);
}
