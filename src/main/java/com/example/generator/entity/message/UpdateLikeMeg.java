package com.example.generator.entity.message;
import com.fasterxml.jackson.annotation.JsonProperty;
public class UpdateLikeMeg {
    private boolean isLiked;
    private int postID;
    private String userTelephone;
    public boolean isLiked() {
        return isLiked;
    }
    @JsonProperty("isLiked")
    public void setLiked(boolean liked) {
        isLiked = liked;
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
