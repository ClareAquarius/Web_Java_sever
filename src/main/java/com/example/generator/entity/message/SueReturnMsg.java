package com.example.generator.entity.message;

import com.example.generator.entity.Sue;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SueReturnMsg {

    private String Reason;
    private Integer SueID;
    private String Targetdetail;
    private String Targettitle;
    private String Targettype;
    private String Time;

    public SueReturnMsg(Sue sue, String title, String content) {
        Reason=sue.getReason();
        SueID=sue.getSueid();
        Targetdetail=content;
        Targettitle=title;
        Targettype=sue.getTargettype();
        Time=sue.getSueTime().toString();
    }

    public String getReason() {
        return Reason;
    }
    @JsonProperty("Reason")
    public void setReason(String reason) {
        Reason = reason;
    }

    public Integer getSueID() {
        return SueID;
    }
    @JsonProperty("SueID")
    public void setSueID(Integer sueID) {
        SueID = sueID;
    }

    public String getTargetdetail() {
        return Targetdetail;
    }
    @JsonProperty("Targetdetail")
    public void setTargetdetail(String targetdetail) {
        Targetdetail = targetdetail;
    }

    public String getTargettitle() {
        return Targettitle;
    }
    @JsonProperty("Targettitle")
    public void setTargettitle(String targettitle) {
        Targettitle = targettitle;
    }

    public String getTargettype() {
        return Targettype;
    }
    @JsonProperty("Targettype")
    public void setTargettype(String targettype) {
        Targettype = targettype;
    }

    public String getTime() {
        return Time;
    }
    @JsonProperty("Time")
    public void setTime(String time) {
        Time = time;
    }


}
