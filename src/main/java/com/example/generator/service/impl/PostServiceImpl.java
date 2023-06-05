package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.Plike;
import com.example.generator.entity.Post;
import com.example.generator.entity.Psave;
import com.example.generator.entity.User;
import com.example.generator.entity.message.BrowseReturnMeg;
import com.example.generator.mapper.PlikeMapper;
import com.example.generator.mapper.PostMapper;
import com.example.generator.mapper.PsaveMapper;
import com.example.generator.mapper.UserMapper;
import com.example.generator.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PlikeMapper plikeMapper;
    @Autowired
    private PsaveMapper psaveMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<BrowseReturnMeg> broseAll(String partition, String searchinfo, String userTelephone) {
        // 获得帖子全部Post数据
        List<Post> data = this.list();
        // 根据phone获得user信息
        LambdaQueryWrapper<User> wrapper_user=new LambdaQueryWrapper<>();
        wrapper_user.eq(User::getPhone,userTelephone);
        User user=userMapper.selectOne(wrapper_user);

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

            // 查询plikeMapper，查看该用户是否点赞
            LambdaQueryWrapper<Plike> wrapper_like = new LambdaQueryWrapper<>();
            wrapper_like.eq(Plike::getUserid, user.getUserid());
            wrapper_like.eq(Plike::getPtargetid, post.getPostid());
            Plike plike=plikeMapper.selectOne(wrapper_like);
            if(plike!=null)
            {
               modifiedPost.setIsLiked(true);
            }
            else
            {
                modifiedPost.setIsLiked(false);
            }

            // 查询psaveMapper，查看该用户是否收藏
            LambdaQueryWrapper<Psave> wrapper_save = new LambdaQueryWrapper<>();
            wrapper_save.eq(Psave::getUserid, user.getUserid());
            wrapper_save.eq(Psave::getPtargetid, post.getPostid());
            Psave psave=psaveMapper.selectOne(wrapper_save);
            if(psave!=null)
            {
                modifiedPost.setIsSaved(true);
            }
            else
            {
                modifiedPost.setIsSaved(false);
            }
            // 把帖子详情插入返回结果
            resultList.add(modifiedPost);
        }
        return resultList;
    }
}

