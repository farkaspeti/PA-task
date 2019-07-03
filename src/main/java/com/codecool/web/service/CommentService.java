package com.codecool.web.service;

import com.codecool.web.model.Comment;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    
    Comment findById(int postId) throws SQLException, ServiceException;
    
    Comment addComment(int postId,int userId, String commentText) throws SQLException, ServiceException;
    
    void updateComment(int commentId, String commentText) throws SQLException, ServiceException;
    
    void deleteComment(int commentId) throws SQLException, ServiceException;
    
    List<Comment> findAllByUserId(int userId) throws SQLException, ServiceException;
    
    List<Comment> findAllByPostId(int postId) throws SQLException, ServiceException;
}
