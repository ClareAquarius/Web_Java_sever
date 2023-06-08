package com.example.generator.entity.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateSaveMeg {

    private boolean isSaved;
    private int postID;
    private String userTelephone;
    public boolean isSaved() {
        return isSaved;
    }
    @JsonProperty("isSaved")
    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

}
