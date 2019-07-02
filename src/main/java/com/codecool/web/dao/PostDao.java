package com.codecool.web.dao;

import com.codecool.web.model.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostDao {
    
    List<Post> findAll() throws SQLException;
    
    Post findById(int postId) throws SQLException;
    
    Post add(int userId, String content, String postDate) throws SQLException;
    
    void update(int postId, String content) throws SQLException;
    
    void delete(int postId) throws SQLException;
    
    List<Post> findAllByUserId(int userId) throws SQLException;
}
