package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.Pclike;
import com.example.generator.entity.Pcomment;
import com.example.generator.entity.Plike;
import com.example.generator.mapper.PclikeMapper;
import com.example.generator.service.IPclikeService;
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
public class PclikeServiceImpl extends ServiceImpl<PclikeMapper, Pclike> implements IPclikeService {

    @Override
    public boolean searchIflike(Integer userid, Integer pcommentid) {
        LambdaQueryWrapper<Pclike> wrapper_like = new LambdaQueryWrapper<>();
        wrapper_like.eq(Pclike::getUserid, userid);
        wrapper_like.eq(Pclike::getPctargetid, pcommentid);
        Pclike pclike=this.baseMapper.selectOne(wrapper_like);
        if(pclike!=null)
        {
            return true;
        }
        return false;
    }

    @Override
    public void insertLike(Pclike pclike) {this.baseMapper.insert(pclike);}

    @Override
    public void deletetpcLike(Integer userid, int pcommentID) {
        LambdaQueryWrapper<Pclike> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Pclike::getUserid, userid);
        wrapper.eq(Pclike::getPctargetid, pcommentID);
        this.baseMapper.delete(wrapper);
    }

}
