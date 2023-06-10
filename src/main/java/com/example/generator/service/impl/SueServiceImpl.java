package com.example.generator.service.impl;

import com.example.generator.entity.Sue;
import com.example.generator.mapper.SueMapper;
import com.example.generator.service.ISueService;
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
}
