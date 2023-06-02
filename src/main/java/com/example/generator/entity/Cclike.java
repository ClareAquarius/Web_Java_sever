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
public class Cclike implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cclikeid;

    private Integer userid;

    private Integer cctargetid;

    public Integer getCclikeid() {
        return cclikeid;
    }

    public void setCclikeid(Integer cclikeid) {
        this.cclikeid = cclikeid;
    }
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public Integer getCctargetid() {
        return cctargetid;
    }

    public void setCctargetid(Integer cctargetid) {
        this.cctargetid = cctargetid;
    }

    @Override
    public String toString() {
        return "Cclike{" +
            "cclikeid=" + cclikeid +
            ", userid=" + userid +
            ", cctargetid=" + cctargetid +
        "}";
    }
}
