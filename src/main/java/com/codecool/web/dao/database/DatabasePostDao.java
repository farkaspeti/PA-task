package com.codecool.web.dao.database;

import com.codecool.web.dao.PostDao;
import com.codecool.web.model.Post;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabasePostDao extends AbstractDao implements PostDao {
    
    public DatabasePostDao(Connection connection) { super(connection); }
    
    @Override
    public List<Post> findAll() throws SQLException {
        return null;
    }
    
    @Override
    public Post findById(int postId) throws SQLException {
        return null;
    }
    
    @Override
    public Post add(int userId, String content, String postDate) throws SQLException {
        return null;
    }
    
    @Override
    public void update(int postId, String content) throws SQLException {
    
    }
    
    @Override
    public void delete(int postId) throws SQLException {
    
    }
    
    @Override
    public List<Post> findAllByUserId(int userId) throws SQLException {
        return null;
    }
}
