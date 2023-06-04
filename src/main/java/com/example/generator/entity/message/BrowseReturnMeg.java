package com.example.generator.entity.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrowseReturnMeg {
    private int PostID;
    private int Comment;
    private String Content;
    private boolean IsLiked;
    private boolean IsSaved;
    private int Like;
    private String PostTime;
    private String Title;
    private String UserName;
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

