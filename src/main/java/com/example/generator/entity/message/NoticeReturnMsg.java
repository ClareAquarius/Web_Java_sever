package com.example.generator.entity.message;

import com.example.generator.entity.Notice;
import com.example.generator.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NoticeReturnMsg {
    private String content;
    private int noticeID;
    private int postID;
    private boolean read;
    private String receiverName;
    private String senderName;
    private int target;
    private String time;
    private String type;

    public NoticeReturnMsg(Notice notice, User user, User sender, String time) {
        noticeID=notice.getNoticeid();
        content=notice.getNtext();
        postID=notice.getPostid();
        if(notice.getIsRead()==0)
        {
            read=false;
        }
        else {
            read=true;
        }
        receiverName=user.getName();
        senderName=sender.getName();
        target=notice.getTarget();
        this.time=time;
        this.type=notice.getType();
    }

    public NoticeReturnMsg(Notice notice, User user) {
        content=notice.getNtext();
        noticeID=notice.getNoticeid();
        postID=notice.getPostid();
        if(notice.getIsRead()==0)
        {
            read=false;
        }
        else {
            read=true;
        }
        receiverName=user.getName();
        senderName="系统";
        target=notice.getTarget();
        this.type=notice.getType();
        LocalDateTime dateTime = LocalDateTime.now();
        time=dateTime.toString();
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNoticeID() {
        return noticeID;
    }

    public void setNoticeID(int noticeID) {
        this.noticeID = noticeID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
