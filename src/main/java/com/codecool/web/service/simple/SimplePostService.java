package com.codecool.web.service.simple;

import com.codecool.web.model.Post;
import com.codecool.web.service.PostService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimplePostService implements PostService {
    @Override
    public Post addPost(int userId, String content) throws SQLException, ServiceException {
        return null;
    }
    
    @Override
    public List<Post> findAllByUserId(int userId) throws SQLException, ServiceException {
        return null;
    }
    
    @Override
    public void updatePost(int postId, String content) throws SQLException, ServiceException {
    
    }
    
    @Override
    public void deletePost(int postId) throws SQLException, ServiceException {
    
    }
    
    @Override
    public void addLabelToPost(int postId, int labelId) throws SQLException, ServiceException {
    
    }
    
    @Override
    public Post findById(int postId) throws SQLException, ServiceException {
        return null;
    }
    
    @Override
    public List<Post> findAll() throws SQLException, ServiceException {
        return null;
    }
    
    @Override
    public List<Integer> findPostIdByLabelId(int labelId) throws SQLException, ServiceException {
        return null;
    }
}