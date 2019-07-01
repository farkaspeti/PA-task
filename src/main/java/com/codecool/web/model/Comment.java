package com.codecool.web.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment extends AbstractModel {
    private final int postId;
    private final int userId;
    private final String commentText;
    private final String commentDate;
    
    public Comment(int id, int postId, int userId, String commentText, String commentDate) {
        super(id);
        this.postId = postId;
        this.userId = userId;
        this.commentText = commentText;
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
        String finalDate = dateObj.format(dateForm);
        this.commentDate = finalDate;
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
    
    public String getCommentDate() {
        return commentDate;
    }
}
