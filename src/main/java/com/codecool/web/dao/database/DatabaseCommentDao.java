package com.codecool.web.dao.database;

import com.codecool.web.dao.CommentDao;
import com.codecool.web.model.Comment;
import com.codecool.web.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseCommentDao extends AbstractDao implements CommentDao {
    
    public DatabaseCommentDao(Connection connection) { super(connection); }
    
    @Override
    public Post findById(int commentId) throws SQLException {
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
    public List<Comment> findAllByUserId(int userId) throws SQLException {
        return null;
    }
    
    @Override
    public List<Comment> findAllByPostId(int postId) throws SQLException {
        return null;
    }
}
