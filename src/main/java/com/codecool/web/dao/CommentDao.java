package com.codecool.web.dao;

import com.codecool.web.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
    
    Comment findById(int postId) throws SQLException;
    
    Comment add(int postId,int userId, String commentText) throws SQLException;
    
    void update(int commentId, String commentText) throws SQLException;
    
    void delete(int commentId) throws SQLException;
    
    List<Comment> findAllByUserId(int userId) throws SQLException;
    
    List<Comment> findAllByPostId(int postId) throws SQLException;
}
