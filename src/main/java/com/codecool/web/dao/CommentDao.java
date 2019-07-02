package com.codecool.web.dao;

import com.codecool.web.model.Comment;
import com.codecool.web.model.Post;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
    
    Post findById(int postId) throws SQLException;
    
    Post add(int userId, String content, String postDate) throws SQLException;
    
    void update(int postId, String content) throws SQLException;
    
    void delete(int postId) throws SQLException;
    
    List<Comment> findAllByUserId(int userId) throws SQLException;
    
    List<Comment> findAllByPostId(int postId) throws SQLException;
}