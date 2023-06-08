package com.example.generator.entity.message;

import com.example.generator.entity.Pcomment;
import com.example.generator.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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
    private List<Object> SubComments;

    public PostPcommentReturnMsg(Pcomment pcomment, User user, boolean like, List<Object> list) {
        Author=user.getName();
        AuthorAvatar=user.getProfile();
        CommentTime=pcomment.getTime().toString();
        Content=pcomment.getPctext();
        IsLiked=like;
        LikeNum=pcomment.getLikeNum();
        PcommentID=pcomment.getPcommentid();
        SubComments=list;
    }


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

    public List<Object> getSubComments() {
        return SubComments;
    }

    public void setSubComments(List<Object> subComments) {
        SubComments = subComments;
    }


}
