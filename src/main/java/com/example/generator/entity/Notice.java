package com.example.generator.entity;

import org.apache.ibatis.annotations.Options;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer noticeid;

    private Integer receiver;

    private Integer sender;

    private String type;

    private String ntext;

    private int isRead;

    private int postid;

    private int target;

    public Notice(Integer senderid, int recrid,int postid, String type, String content, int target) {
        receiver=recrid;
        sender=senderid;
        this.type=type;
        ntext=content;
        isRead=0;
        this.postid=postid;
        this.target=target;
    }

    public int getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(Integer noticeid) {
        this.noticeid = noticeid;
    }
    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }
    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getNtext() {
        return ntext;
    }

    public void setNtext(String ntext) {
        this.ntext = ntext;
    }
    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }
    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Notice{" +
            "noticeid=" + noticeid +
            ", receiver=" + receiver +
            ", sender=" + sender +
            ", type=" + type +
            ", ntext=" + ntext +
            ", isRead=" + isRead +
        "}";
    }
}
