package com.codecool.web.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Post extends AbstractModel {
    
    private final int userId;
    private final String content;
    private final String postDate;
    
    
    public Post(int id, int userId, String content, String postDate) {
        super(id);
        this.userId = userId;
        this.content = content;
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        String finalDate = dateObj.format(dateForm);
        this.postDate = finalDate;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getPostDate() {
        return postDate;
    }
}
