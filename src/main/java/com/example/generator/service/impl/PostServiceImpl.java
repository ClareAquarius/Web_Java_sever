package com.example.generator.service.impl;

import com.example.generator.entity.Post;
import com.example.generator.entity.message.BrowseReturnMeg;
import com.example.generator.mapper.PostMapper;
import com.example.generator.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    @Override
    public List<BrowseReturnMeg> broseAll(String partition, String searchinfo, String phone) {
        // 获得帖子Post数据
        List<Post> data = this.list();
        // 开始对返回信息进行格式设置

        List<BrowseReturnMeg> resultList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            Post post = data.get(i);
            BrowseReturnMeg modifiedPost = new BrowseReturnMeg();
            modifiedPost.setPostID(post.getPostid());
            modifiedPost.setComment(post.getCommentNum());
            modifiedPost.setContent(post.getContent());
            modifiedPost.setIsLiked(false);
            modifiedPost.setIsSaved(false);
            modifiedPost.setLike(post.getLikeNum());
            modifiedPost.setPostTime(post.getPostTime().toString());
            modifiedPost.setTitle(post.getTitle());
            modifiedPost.setUserName("aaa"); // 设置用户名
            modifiedPost.setUserTelephone("18872137337"); // 设置用户电话
            resultList.add(modifiedPost);
        }
        return resultList;
    }
}

