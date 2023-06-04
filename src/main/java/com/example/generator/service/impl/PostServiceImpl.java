package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.generator.entity.Post;
import com.example.generator.entity.User;
import com.example.generator.entity.message.BrowseReturnMeg;
import com.example.generator.mapper.PostMapper;
import com.example.generator.service.IPlikeService;
import com.example.generator.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.generator.service.IPsaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private IPlikeService plikeService;
    @Autowired
    private IPsaveService psaveService;

    @Override
    public List<BrowseReturnMeg> broseAll(String partition, String searchinfo, User user) {
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
            modifiedPost.setLike(post.getLikeNum());
            modifiedPost.setPostTime(post.getPostTime().toString());
            modifiedPost.setTitle(post.getTitle());

            modifiedPost.setUserName(user.getName()); // 设置用户名
            modifiedPost.setUserTelephone(user.getPhone()); // 设置用户电话

            // 调用plike和psave的服务查询该用户是否点赞
            boolean like=plikeService.search(user.getUserid(),post.getPostid());
            boolean save=psaveService.search(user.getUserid(),post.getPostid());
            modifiedPost.setIsLiked(like);
            modifiedPost.setIsSaved(save);

            resultList.add(modifiedPost);
        }
        return resultList;
    }

    // 根据postID增加该帖子的like数
    @Override
    public void addlike(int i, int postID)
    {
        LambdaUpdateWrapper<Post> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Post::getPostid, postID).setSql("like_num = like_num + "+ i);
        // 下面null的作用是告诉 MyBatis-Plus 只使用更新条件而不使用实体对象的值进行更新。
        this.baseMapper.update(null, updateWrapper);
    }


}

