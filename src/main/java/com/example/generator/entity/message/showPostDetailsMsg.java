package com.example.generator.entity.message;

import com.example.generator.entity.Post;
import com.example.generator.entity.User;

public class showPostDetailsMsg {
    private int postID;
    private String userTelephone;


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
