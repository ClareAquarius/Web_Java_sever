package com.example.generator.entity.message;

import com.example.generator.entity.Ccomment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostPcommentReturnMsg {
    @JsonProperty("Author")
    private String Author;

    @JsonProperty("AuthorAvatar")
    private String AuthorAvatar;

    @JsonProperty("CommentTime")
    private String CommentTime;

    @JsonProperty("Content")
    private String Content;

    @JsonProperty("IsLiked")
    private boolean IsLiked;

    @JsonProperty("LikeNum")
    private int LikeNum;

    @JsonProperty("PcommentID")
    private int PcommentID;

    @JsonProperty("SubComments")
    private Ccomment[] SubComments;

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getAuthorAvatar() {
        return AuthorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        AuthorAvatar = authorAvatar;
    }

    public String getCommentTime() {
        return CommentTime;
    }

    public void setCommentTime(String commentTime) {
        CommentTime = commentTime;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public boolean isLiked() {
        return IsLiked;
    }

    public void setLiked(boolean liked) {
        IsLiked = liked;
    }

    public int getLikeNum() {
        return LikeNum;
    }

    public void setLikeNum(int likeNum) {
        LikeNum = likeNum;
    }

    public int getPcommentID() {
        return PcommentID;
    }

    public void setPcommentID(int pcommentID) {
        PcommentID = pcommentID;
    }

    public Ccomment[] getSubComments() {
        return SubComments;
    }

    public void setSubComments(Ccomment[] subComments) {
        SubComments = subComments;
    }


}
