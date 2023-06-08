package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.Pcomment;
import com.example.generator.entity.User;
import com.example.generator.entity.message.PostPcommentMsg;
import com.example.generator.entity.message.PostPcommentReturnMsg;
import com.example.generator.mapper.PcommentMapper;
import com.example.generator.mapper.UserMapper;
import com.example.generator.service.IPcommentService;
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
public class PcommentServiceImpl extends ServiceImpl<PcommentMapper, Pcomment> implements IPcommentService {
    @Autowired
    private UserMapper u

    }
}

