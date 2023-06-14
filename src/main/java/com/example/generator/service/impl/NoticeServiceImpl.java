package com.example.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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

    @Override
    public boolean read_notice(int id) {
        LambdaUpdateWrapper<Notice>wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Notice::getNoticeid,id).setSql("is_read = 1");
        // 执行更新操作
        int affectedRows = noticeMapper.update(null, wrapper);
        // 根据更新结果判断是否更新成功
        return affectedRows > 0;
    }

    @Override
    public String deleteNoticebySender(Integer userID) {
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notice::getSender, userID);
        int result = this.baseMapper.delete(wrapper);
        if (result >= 0 ) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @Override
    public String deleteNoticebyReceiver(Integer userID) {
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notice::getReceiver, userID);
        int result = this.baseMapper.delete(wrapper);
        if (result >= 0 ) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}
