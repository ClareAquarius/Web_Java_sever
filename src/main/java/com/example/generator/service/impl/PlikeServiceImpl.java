package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.Plike;
import com.example.generator.mapper.PlikeMapper;
import com.example.generator.service.IPlikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    @Override
    public void insertLike(Plike plike) {
        this.baseMapper.insert(plike);
    }

    @Override
    public void deletetLike(Integer userid, int postID) {
        LambdaQueryWrapper<Plike> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Plike::getUserid, userid);
        wrapper.eq(Plike::getPtargetid, postID);
        this.baseMapper.delete(wrapper);
    }

    @Override
    public boolean searchIflike(Integer userid, int PostID) {
        LambdaQueryWrapper<Plike> wrapper_like = new LambdaQueryWrapper<>();
        wrapper_like.eq(Plike::getUserid, userid);
        wrapper_like.eq(Plike::getPtargetid, PostID);
        Plike plike=this.baseMapper.selectOne(wrapper_like);
        if(plike!=null)
        {
            return true;
        }
        return false;
    }
}
