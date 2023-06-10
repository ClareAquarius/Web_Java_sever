package com.example.generator.entity;

import org.apache.ibatis.annotations.Options;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2023-06-02
 */
public class Pcomment implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer pcommentid;

    private Integer userid;

    private Integer ptargetid;

    private Integer like_num;

    private String pctext;

    private LocalDateTime time;

    public Pcomment(int userid, int ptargetid, String pctext) {
        this.userid=userid;
        this.ptargetid=ptargetid;
        this.pctext=pctext;
    }

    public Pcomment() {

    }

    public Integer getPcommentid() {
        return pcommentid;
    }

    public void setPcommentid(Integer pcommentid) {
        this.pcommentid = pcommentid;
    }
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public Integer getPtargetid() {
        return ptargetid;
    }

    public void setPtargetid(Integer ptargetid) {
        this.ptargetid = ptargetid;
    }
    public Integer getLikeNum() {
        return like_num;
    }

    public void setLikeNum(Integer likeNum) {
        this.like_num = likeNum;
    }
    public String getPctext() {
        return pctext;
    }

    public void setPctext(String pctext) {
        this.pctext = pctext;
    }
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Pcomment{" +
            "pcommentid=" + pcommentid +
            ", userid=" + userid +
            ", ptargetid=" + ptargetid +
            ", likeNum=" + like_num +
            ", pctext=" + pctext +
            ", time=" + time +
        "}";
    }
}
