package com.example.generator.service;

import com.example.generator.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.generator.entity.message.BrowseMeg;
import com.example.generator.entity.message.BrowseReturnMeg;

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
public interface IPostService extends IService<Post> {
    List<BrowseReturnMeg> broseAll(String partition, String searchinfo, String phone);
}
