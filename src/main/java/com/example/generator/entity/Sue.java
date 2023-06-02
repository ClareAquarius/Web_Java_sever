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
public class Sue implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer sueid;

    private String targettype;

    private Integer ptargetid;

    private Integer userid;

    private String reason;

    private LocalDateTime sueTime;

    private String status;

    private Boolean finish;

    public Integer getSueid() {
        return sueid;
    }

    public void setSueid(Integer sueid) {
        this.sueid = sueid;
    }
    public String getTargettype() {
        return targettype;
    }

    public void setTargettype(String targettype) {
        this.targettype = targettype;
    }
    public Integer getPtargetid() {
        return ptargetid;
    }

    public void setPtargetid(Integer ptargetid) {
        this.ptargetid = ptargetid;
    }
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public LocalDateTime getSueTime() {
        return sueTime;
    }

    public void setSueTime(LocalDateTime sueTime) {
        this.sueTime = sueTime;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "Sue{" +
            "sueid=" + sueid +
            ", targettype=" + targettype +
            ", ptargetid=" + ptargetid +
            ", userid=" + userid +
            ", reason=" + reason +
            ", sueTime=" + sueTime +
            ", status=" + status +
            ", finish=" + finish +
        "}";
    }
}
