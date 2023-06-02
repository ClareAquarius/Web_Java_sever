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
public class Pclike implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pclikeid;

    private Integer userid;

    private Integer pctargetid;

    public Integer getPclikeid() {
        return pclikeid;
    }

    public void setPclikeid(Integer pclikeid) {
        this.pclikeid = pclikeid;
    }
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public Integer getPctargetid() {
        return pctargetid;
    }

    public void setPctargetid(Integer pctargetid) {
        this.pctargetid = pctargetid;
    }

    @Override
    public String toString() {
        return "Pclike{" +
            "pclikeid=" + pclikeid +
            ", userid=" + userid +
            ", pctargetid=" + pctargetid +
        "}";
    }
}
