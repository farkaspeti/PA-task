package com.codecool.web.service.simple;

import com.codecool.web.dao.PostDao;
import com.codecool.web.model.Post;
import com.codecool.web.service.PostService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimplePostService implements PostService {
    
    private final PostDao postDao;
    
    public SimplePostService(PostDao postDao) {
        this.postDao = postDao;
    }
    
    @Override
    public Post addPost(int userId, String content) throws SQLException, ServiceException {
        try {
            return postDao.add(userId, content);
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public List<Post> findAllByUserId(int userId) throws SQLException, ServiceException {
        try{
            return postDao.findAllByUserId(userId);
        }catch (IllegalArgumentException ex ){
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public void updatePost(int postId, String content) throws SQLException, ServiceException {
        try{
            postDao.update(postId,content);
        }catch (IllegalArgumentException ex ){
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public void deletePost(int postId) throws SQLException, ServiceException {
        try{
            postDao.delete(postId);
        }catch (IllegalArgumentException ex ){
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public void addLabelToPost(int postId, int labelId) throws SQLException, ServiceException {
        try{
            postDao.addLabelToPost(postId,labelId);
        }catch (IllegalArgumentException ex ){
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public Post findById(int postId) throws SQLException, ServiceException {
        try{
            return postDao.findById(postId);
        }catch (IllegalArgumentException ex ){
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public List<Post> findAll() throws SQLException, ServiceException {
        try{
            return postDao.findAll();
        }catch (IllegalArgumentException ex ){
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public List<Integer> findPostIdByLabelId(int labelId) throws SQLException, ServiceException {
        return null;
    }
}
