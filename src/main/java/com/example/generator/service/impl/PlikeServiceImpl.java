package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.generator.entity.Plike;
import com.example.generator.entity.Post;
import com.example.generator.entity.User;
import com.example.generator.entity.message.UpdateLikeMeg;
import com.example.generator.mapper.PlikeMapper;
import com.example.generator.mapper.PostMapper;
import com.example.generator.mapper.UserMapper;
import com.example.generator.service.IPlikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */

@Service
public class PlikeServiceImpl extends ServiceImpl<PlikeMapper, Plike> implements IPlikeService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;

    @Override
    public boolean updateplike(UpdateLikeMeg meg) {
        User user = getUserByPhone(meg.getUserTelephone());
        boolean if_like_before = meg.isLiked();
        Integer result;
        // 点赞--增加对应post的like，添加clike表单
        if (if_like_before==false) {
            Plike plike = new Plike();
            plike.setPtargetid(meg.getPostID());
            plike.setUserid(user.getUserid());
            result = this.baseMapper.insert(plike);
            addLikeCount(meg.getPostID());
        }
        // 取赞--减少对应post的like，删除clike表单
        else
        {
            LambdaQueryWrapper<Plike> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Plike::getUserid, user.getUserid());
            wrapper.eq(Plike::getPtargetid, meg.getPostID());
            result = this.baseMapper.delete(wrapper);
            subLikeCount(meg.getPostID());
        }
        return result > 0;
    }

    private User getUserByPhone(String phone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, phone);
        return userMapper.selectOne(wrapper);
    }

    private void addLikeCount(int postId) {
        LambdaUpdateWrapper<Post> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Post::getPostid, postId).setSql("like_num = like_num  + 1");
        postMapper.update(null, updateWrapper);
    }
    private void subLikeCount(int postId) {
        LambdaUpdateWrapper<Post> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Post::getPostid, postId).setSql("like_num = like_num  - 1");
        postMapper.update(null, updateWrapper);
    }
}
