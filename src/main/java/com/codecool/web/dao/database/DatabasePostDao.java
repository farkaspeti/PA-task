package com.codecool.web.dao.database;

import com.codecool.web.dao.PostDao;
import com.codecool.web.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasePostDao extends AbstractDao implements PostDao {
    
    public DatabasePostDao(Connection connection) {
        super(connection);
    }
    
    @Override
    public List<Post> findAll() throws SQLException {
        List<Post> postList = new ArrayList<>();
        String sql = "SELECT * FROM posts ORDER BY post_id";
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
        String sql = "SELECT * FROM posts WHERE post_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, postId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchPost(resultSet);
                }
            }
        }
        return null;
    }
    
    @Override
    public Post add(int userId, String content, String postDate) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO posts (user_id, content, post_date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, postDate);
            executeInsert(preparedStatement);
            int id = fetchGeneratedId(preparedStatement);
            return new Post(id, userId, content, postDate);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    @Override
    public void update(int postId, String content) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE posts SET  content=? WHERE post_id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, content);
            preparedStatement.setInt(2, postId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
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
