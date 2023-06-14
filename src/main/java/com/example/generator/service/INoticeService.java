package com.example.generator.service;

import com.example.generator.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public interface INoticeService extends IService<Notice> {

    void addNotice(Integer sendid, int recrid, int postid, String type, String content, int target);

    List<Notice> getNoticeListByUserID(Integer userid);

    boolean read_notice(int id);

    String deleteNoticebySender(Integer userID);

    String deleteNoticebyReceiver(Integer userID);
}
