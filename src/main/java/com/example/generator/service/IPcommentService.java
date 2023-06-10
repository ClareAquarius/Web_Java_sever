package com.example.generator.service;

import com.example.generator.entity.Pcomment;
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
public interface IPcommentService extends IService<Pcomment> {

    List<Pcomment> searchPcommentsListByPostID(int postID);

    int addPcomment(Pcomment pcomment);
}
