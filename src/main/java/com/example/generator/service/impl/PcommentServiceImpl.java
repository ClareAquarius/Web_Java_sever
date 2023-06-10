package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.Pcomment;
import com.example.generator.mapper.PcommentMapper;
import com.example.generator.service.IPcommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}

