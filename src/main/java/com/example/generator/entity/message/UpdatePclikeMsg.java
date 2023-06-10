package com.example.generator.entity.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePclikeMsg {
    private boolean isLiked;
    private int pcommentID;
    private String userTelephone;
    public boolean isLiked() {
        return isLiked;
    }

    @JsonProperty("isLiked")
    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int getPcommentID() {
        return pcommentID;
    }

    public void setPcommentID(int pcommentID) {
        this.pcommentID = pcommentID;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

}
