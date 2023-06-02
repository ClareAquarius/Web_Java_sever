package com.example.generator.entity;

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
public class Ccomment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer ccommentid;

    private Integer userid;

    private Integer ctargetid;

    private Long likeNum;

    private String cctext;

    private LocalDateTime time;

    private Integer usertargetid;

    public Integer getCcommentid() {
        return ccommentid;
    }

    public void setCcommentid(Integer ccommentid) {
        this.ccommentid = ccommentid;
    }
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public Integer getCtargetid() {
        return ctargetid;
    }

    public void setCtargetid(Integer ctargetid) {
        this.ctargetid = ctargetid;
    }
    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }
    public String getCctext() {
        return cctext;
    }

    public void setCctext(String cctext) {
        this.cctext = cctext;
    }
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public Integer getUsertargetid() {
        return usertargetid;
    }

    public void setUsertargetid(Integer usertargetid) {
        this.usertargetid = usertargetid;
    }

    @Override
    public String toString() {
        return "Ccomment{" +
            "ccommentid=" + ccommentid +
            ", userid=" + userid +
            ", ctargetid=" + ctargetid +
            ", likeNum=" + likeNum +
            ", cctext=" + cctext +
            ", time=" + time +
            ", usertargetid=" + usertargetid +
        "}";
    }
}
