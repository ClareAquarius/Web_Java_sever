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
public class Psave implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer psaveid;

    private Integer userid;

    private Integer ptargetid;

    public Integer getPsaveid() {
        return psaveid;
    }

    public void setPsaveid(Integer psaveid) {
        this.psaveid = psaveid;
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

    @Override
    public String toString() {
        return "Psave{" +
            "psaveid=" + psaveid +
            ", userid=" + userid +
            ", ptargetid=" + ptargetid +
        "}";
    }
}
