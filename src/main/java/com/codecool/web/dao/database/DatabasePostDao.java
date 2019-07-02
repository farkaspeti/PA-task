package com.codecool.web.dao.database;

import com.codecool.web.dao.PostDao;
import com.codecool.web.model.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabasePostDao extends AbstractDao implements PostDao {
    
    public DatabasePostDao(Connection connection) { super(connection); }
    
    @Override
    public List<Post> findAll() throws SQLException {
        List<Post> postList = new ArrayList<>();
        String sql = "SELECT * FROM posts order by post_id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                postList.add(fetchPost(resultSet));
            }
        
        }
        return postList;
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
    
    private Post fetchPost(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("post_id");
        int userId = resultSet.getInt("user_id");
        String content = resultSet.getString("content");
        String postDate = resultSet.getString("post_date");
        return new Post(id, userId, content, postDate);
    }
}
