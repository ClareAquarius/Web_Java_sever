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
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer postid;

    private Integer userid;

    private String postPartition;

    private String title;

    private String content;

    private Integer commentNum;

    private Integer likeNum;

    private LocalDateTime postTime;

    private String photos;

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public String getPostPartition() {
        return postPartition;
    }

    public void setPostPartition(String postPartition) {
        this.postPartition = postPartition;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }
    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }
    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }
    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "Post{" +
            "postid=" + postid +
            ", userid=" + userid +
            ", postPartition=" + postPartition +
            ", title=" + title +
            ", content=" + content +
            ", commentNum=" + commentNum +
            ", likeNum=" + likeNum +
            ", postTime=" + postTime +
            ", photos=" + photos +
        "}";
    }
}
