package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.generator.entity.Pcomment;
import com.example.generator.entity.Plike;
import com.example.generator.entity.Post;
import com.example.generator.mapper.PcommentMapper;
import com.example.generator.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
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
public class PcommentServiceImpl extends ServiceImpl<PcommentMapper, Pcomment> implements IPcommentService {
    @Autowired
    private IPostService postService;
    @Resource
    private PcommentMapper pcommentMapper;
    @Override
    public List<Pcomment> searchPcommentsListByPostID(int postID) {
        LambdaQueryWrapper<Pcomment> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Pcomment::getPtargetid,postID);
        List<Pcomment> pcomments = this.baseMapper.selectList(wrapper);
        return  pcomments;
    }

    @Override
    public int addPcomment(Pcomment pcomment) {
        Pcomment p=new Pcomment();
        p.setPtargetid(pcomment.getPtargetid());
        p.setUserid(pcomment.getUserid());
        p.setPctext(pcomment.getPctext());
        pcommentMapper.insertPcomment(p);
        return p.getPcommentid();
    }

    @Override
    public Pcomment getPcommentById(int target) {
        LambdaQueryWrapper<Pcomment> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Pcomment::getPcommentid,target);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public void pcAddLikeCount(int pcommentID) {
        LambdaUpdateWrapper<Pcomment> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Pcomment::getPcommentid, pcommentID).setSql("like_num = like_num + 1");
        this.update(null, updateWrapper);
    }

    @Override
    public void pcSubLikeCount(int pcommentID) {
        LambdaUpdateWrapper<Pcomment> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Pcomment::getPcommentid, pcommentID).setSql("like_num = like_num - 1");
        this.update(null, updateWrapper);
    }
    @Override
    public String deleteCommentByUserID(Integer userID) {
        LambdaQueryWrapper<Pcomment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Pcomment::getUserid, userID);
        List<Pcomment> commentsToDelete = this.baseMapper.selectList(wrapper); // 查询被删除的评论数据
        int result = this.baseMapper.delete(wrapper);
        if (result >= 0) {
            if (result > 0) {
                for (Pcomment comment : commentsToDelete) {
                    LambdaUpdateWrapper<Post> updateWrapper = new LambdaUpdateWrapper<>();
                    updateWrapper.eq(Post::getPostid, comment.getPtargetid()).setSql("comment_num = comment_num - 1");
                    this.postService.update(updateWrapper);
                }
            }
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}

