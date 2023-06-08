package com.example.generator.service.impl;

import com.example.generator.entity.Notice;
import com.example.generator.mapper.NoticeMapper;
import com.example.generator.service.INoticeService;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Override
    public void addNotice(Integer senderid, int recrid, String type, String content) {
        Notice notice=new Notice(senderid,recrid,type,content);
        this.baseMapper.insert(notice);
    }
}
