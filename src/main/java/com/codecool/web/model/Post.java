package com.codecool.web.model;

import java.time.LocalDate;

public final class Post extends AbstractModel {
    
    private final int userId;
    private final String content;
    
    
    public Post(int id, int userId, String content) {
        super(id);
        this.userId = userId;
        this.content = content;
        
    }
}
