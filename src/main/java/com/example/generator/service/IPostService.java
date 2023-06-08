package com.example.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.generator.entity.Post;
import com.example.generator.entity.message.BrowseReturnMeg;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public interface IPostService extends IService<Post> {

    List<BrowseReturnMeg> broseAll(String partition, String searchinfo, String userTelephone);

    Integer addPost(Post post);

    String deletePost(Integer postId);

    void postAddLikeCount(int postID);

    void postsubLikeCount(int postID);

    Post getPostByPostID(int postID);


    void addPostCommit(int postID);

    int getUseridByPostid(int postID);
}
