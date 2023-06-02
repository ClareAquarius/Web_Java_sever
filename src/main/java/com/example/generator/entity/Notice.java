package com.example.generator.entity;

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

    private Boolean isRead;

    public Integer getNoticeid() {
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
    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
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
