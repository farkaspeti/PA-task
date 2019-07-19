package com.codecool.web.service;

import com.codecool.web.model.Post;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface PostService {
    
    Post addPost(int userId, String firstName, String lastName, String content) throws SQLException, ServiceException;
    
    List<Post> findAllByUserId(int userId) throws SQLException, ServiceException;
    
    void updatePost(int postId, String content) throws SQLException,ServiceException;
    
    void deletePost(int postId) throws SQLException, ServiceException;
    
    void addLabelToPost(int postId, int labelId) throws SQLException, ServiceException;
    
    Post findById(int postId) throws SQLException,ServiceException;
    
    List<Post> findAll() throws SQLException,ServiceException;
    
    List<Integer> findPostIdByLabelId(int labelId) throws SQLException,ServiceException;
    
}
