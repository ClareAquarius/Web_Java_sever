package com.example.generator.service;

import com.example.generator.entity.Admin;
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
public interface IAdminService extends IService<Admin> {

    Map<String, Object> login(Admin admin);

    Map<String, Object> getAdminInfo(String modifiedString);
}
