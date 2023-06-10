package com.example.generator.entity.message;

import com.example.generator.entity.Post;
import com.example.generator.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class showPostDetailsReturnMsg {
    private int comments;
    private String content;
    private boolean isLiked;
    private boolean isSaved;
    private int like;
    private String photos;
    private int postID;
    private String postTime;
    private String title;
    private String userAvatar;
    private String userName;
    private String userTelephone;

    public showPostDetailsReturnMsg(Post post, User user, boolean like, boolean save) {
        comments=post.getCommentNum();
        content = post.getContent();
        isLiked = like;
        isSaved = save;
        this.like = post.getLikeNum();
        // photos需要修改,先暂时空着，为空
        photos = new String("");

        postID = post.getPostid();
        postTime = String.valueOf(post.getPostTime());
        title = post.getTitle();
        
        userAvatar = user.getPhone();
        userName = user.getName();
        userTelephone = user.getPhone();
    }

    @JsonProperty("Comment")
    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    @JsonProperty("Content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("IsLiked")
    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    @JsonProperty("IsSaved")
    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    @JsonProperty("Like")
    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @JsonProperty("Photos")
    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @JsonProperty("PostID")
    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    @JsonProperty("PostTime")
    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("UserAvatar")
    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    @JsonProperty("UserName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("UserTelephone")
    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }
}
