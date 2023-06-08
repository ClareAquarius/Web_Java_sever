package com.example.generator.entity.message;

public class PostMeg {

    public String getUserTelephone() {
        return userTelephone;
    }


    public String getPartition() {
        return partition;
    }


    public String getContent() {
        return content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }

    private String title;
    private String userTelephone;
    private String partition;
    private String content;

}
