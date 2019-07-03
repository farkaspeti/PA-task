package com.codecool.web.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Comment extends AbstractModel {
    private final int postId;
    private final int userId;
    private final String commentText;
    private final Date commentDate;
    
    public Comment(int id, int postId, int userId, String commentText, Date commentDate) {
        super(id);
        this.postId = postId;
        this.userId = userId;
        this.commentText = commentText;
        this.commentDate = commentDate;
    }
    
    public int getPostId() {
        return postId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public String getCommentText() {
        return commentText;
    }
    
    public Date getCommentDate() {
        return commentDate;
    }
}
