package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.generator.entity.Notice;
import com.example.generator.mapper.NoticeMapper;
import com.example.generator.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {
    @Resource
    private NoticeMapper noticeMapper;
    @Override
    public void addNotice(Integer senderid, int recrid, int postid ,String type, String content,int target) {
        Notice notice=new Notice(senderid,recrid,postid,type,content,target);
        noticeMapper.insert(notice);
    }

    @Override
    public List<Notice> getNoticeListByUserID(Integer userid) {
        LambdaQueryWrapper<Notice>wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Notice::getReceiver,userid);
        List<Notice> list= this.baseMapper.selectList(wrapper);
        return list;
    }
}
