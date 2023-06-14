package com.example.generator.service;

import com.example.generator.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public interface ISueService extends IService<Sue> {
    String submitSue(Sue sue);


    Sue getSuebyID(int sueID);

    void manageSue(String status, int sueID);

    List<Sue> getUnreadSuelist();
    
    String deleteSuebyUserID(Integer userID);

}
