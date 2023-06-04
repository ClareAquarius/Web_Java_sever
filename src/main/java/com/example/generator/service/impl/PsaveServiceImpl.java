package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.Plike;
import com.example.generator.entity.Psave;
import com.example.generator.mapper.PsaveMapper;
import com.example.generator.service.IPsaveService;
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
public class PsaveServiceImpl extends ServiceImpl<PsaveMapper, Psave> implements IPsaveService {

    @Override
    public boolean search(Integer userid, Integer postid) {
        LambdaQueryWrapper<Psave> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Psave::getUserid,userid);
        wrapper.eq(Psave::getPtargetid,postid);
        Psave psave=this.baseMapper.selectOne(wrapper);
        // 找到了收藏信息,就返回true
        if(psave!=null)
        {
            return true;
        }
        return false;
    }
}
