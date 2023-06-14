package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.generator.entity.Sue;
import com.example.generator.mapper.SueMapper;
import com.example.generator.service.ISueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class SueServiceImpl extends ServiceImpl<SueMapper, Sue> implements ISueService {
    @Override
    public String submitSue(Sue sue){
        int result = this.baseMapper.insert(sue);
        if (result > 0) {
            return "举报成功";
        } else {
            return "举报失败";
        }
    }

    @Override
    public Sue getSuebyID(int sueID) {
        LambdaQueryWrapper<Sue> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Sue::getSueid,sueID);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public void manageSue(String status, int sueID) {
        LambdaUpdateWrapper<Sue> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Sue::getSueid,sueID).set(Sue::getStatus,status)
                .setSql("finish = 1");
        this.update(wrapper);
    }

    @Override
    public List<Sue> getUnreadSuelist() {
        LambdaQueryWrapper<Sue> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Sue::getFinish,0);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public String deleteSuebyUserID(Integer userID) {
        LambdaQueryWrapper<Sue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Sue::getUserid, userID);
        int result = this.baseMapper.delete(wrapper);
        if (result >= 0 ) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }


}
