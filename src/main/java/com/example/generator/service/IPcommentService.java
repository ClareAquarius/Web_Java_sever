package com.example.generator.service;

import com.example.generator.entity.Pcomment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.generator.entity.message.PostPcommentMsg;
import com.example.generator.entity.message.PostPcommentReturnMsg;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public interface IPcommentService extends IService<Pcomment> {

    PostPcommentReturnMsg addpcomment(PostPcommentMsg msg);
}
