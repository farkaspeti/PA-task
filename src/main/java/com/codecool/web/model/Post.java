package com.codecool.web.model;

import java.sql.Date;

public final class Post extends AbstractModel {
    
    private final int userId;
    private final String firstName;
    private final String lastName;
    private final String content;
    private final Date postDate;
    
    
    public Post(int id, int userId,String firstName,String lastName, String content,Date postDate) {
        super(id);
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.content = content;
        this.postDate = postDate;
    }
    
    public String getFirstName() { return firstName; }
    
    public String getLastName() { return lastName; }
    
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
