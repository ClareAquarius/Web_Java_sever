package com.example.generator.entity.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrowseReturnMeg {
    @JsonProperty("PostID")
    private int PostID;
    @JsonProperty("Comment")
    private int Comment;
    @JsonProperty("Content")
    private String Content;
    @JsonProperty("IsLiked")
    private boolean IsLiked;
    @JsonProperty("IsSaved")
    private boolean IsSaved;
    @JsonProperty("Like")
    private int Like;
    @JsonProperty("PostTim")
    private String PostTime;
    @JsonProperty("Title")
    private String Title;
    @JsonProperty("UserName")
    private String UserName;
    @JsonProperty("UserTelephone")
    private String UserTelephone;
    public int getPostID() {
        return PostID;
    }

    @JsonProperty("PostID")
    public void setPostID(int postID) {
        this.PostID = postID;
    }

    public int getComment() {
        return Comment;
    }

    @JsonProperty("Comment")
    public void setComment(int comment) {
        this.Comment = comment;
    }

    public String getContent() {
        return Content;
    }

    @JsonProperty("Content")
    public void setContent(String content) {
        this.Content = content;
    }

    public boolean getIsLiked() {
        return IsLiked;
    }

    @JsonProperty("IsLiked")
    public void setIsLiked(boolean isLiked) {
        this.IsLiked = isLiked;
    }

    public boolean getIsSaved() {
        return IsSaved;
    }

    @JsonProperty("IsSaved")
    public void setIsSaved(boolean isSaved) {
        this.IsSaved = isSaved;
    }

    public int getLike() {
        return Like;
    }

    @JsonProperty("Like")
    public void setLike(int like) {
        this.Like = like;
    }

    public String getPostTime() {
        return PostTime;
    }

    @JsonProperty("PostTime")
    public void setPostTime(String postTime) {
        this.PostTime = postTime;
    }

    public String getTitle() {
        return Title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.Title = title;
    }

    public String getUserName() {
        return UserName;
    }

    @JsonProperty("UserName")
    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getUserTelephone() {
        return UserTelephone;
    }

    @JsonProperty("UserTelephone")
    public void setUserTelephone(String userTelephone) {
        this.UserTelephone = userTelephone;
    }
}

