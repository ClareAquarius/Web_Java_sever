package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.Plike;
import com.example.generator.entity.User;
import com.example.generator.entity.message.UpdateLikeMeg;
import com.example.generator.mapper.PlikeMapper;
import com.example.generator.service.IPlikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.generator.service.IPostService;
import com.example.generator.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    private IUserService userService;

    @Autowired
    private IPostService postService;
    // 根据消息更新plike
    @Override
    public boolean updateplike(UpdateLikeMeg meg)
    {
        // 调用自定义的userService方法phone获得userid
        User user=userService.getUserbyPhone(meg.getUserTelephone());
        boolean if_like_before= meg.isLiked();
        Integer result;
        // 点赞--增加plike表项,并增加该post的like数
        if(!if_like_before)
        {
            Plike plike=new Plike();
            plike.setPtargetid(meg.getPostID());
            plike.setUserid(user.getUserid());
            result=this.baseMapper.insert(plike);
            postService.addlike(1,meg.getPostID());
        }
        //取消赞--删除plike表项,并减少该post的like数
        else
        {
            LambdaQueryWrapper<Plike> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(Plike::getUserid,user.getUserid());
            wrapper.eq(Plike::getPtargetid,meg.getPostID());
            result=this.baseMapper.delete(wrapper);
            postService.addlike(-1,meg.getPostID());
        }

        if(result>0)
        {
            return true;
        }
        return  false;
    }

    // 根据userid和postid查询用户是否点赞
    @Override
    public boolean search(Integer userid, Integer postid) {
        LambdaQueryWrapper<Plike> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Plike::getUserid,userid);
        wrapper.eq(Plike::getPtargetid,postid);
        Plike plike=this.baseMapper.selectOne(wrapper);
        // 找到了点赞信息,就返回true
        if(plike!=null)
        {
            return true;
        }
        return false;
    }
}
