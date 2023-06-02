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
public class Plike implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long plikeid;

    private Integer userid;

    private Integer ptargetid;

    public Long getPlikeid() {
        return plikeid;
    }

    public void setPlikeid(Long plikeid) {
        this.plikeid = plikeid;
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
        return "Plike{" +
            "plikeid=" + plikeid +
            ", userid=" + userid +
            ", ptargetid=" + ptargetid +
        "}";
    }
}
