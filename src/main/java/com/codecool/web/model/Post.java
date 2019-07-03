package com.codecool.web.model;

import java.sql.Date;

public final class Post extends AbstractModel {
    
    private final int userId;
    private final String content;
    private final Date postDate;
    
    
    public Post(int id, int userId, String content) {
        super(id);
        this.userId = userId;
        this.content = content;
        java.util.Date date = new java.util.Date();
        postDate = new Date(date.getTime());
    }
    
    public int getUserId() {
        return userId;
    }
    
    public String getContent() {
        return content;
    }
    
    public Date getPostDate() {
        return postDate;
    }
}
