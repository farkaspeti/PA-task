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
    public Post add(int userId, String content) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO posts (user_id, content) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, content);
            executeInsert(preparedStatement);
            int id = fetchGeneratedId(preparedStatement);
            return new Post(id, userId, content);
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
        String sql = "UPDATE posts SET content=? WHERE post_id =?";
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
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sqlString = "DELETE FROM posts cascade WHERE post_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {
            preparedStatement.setInt(1, postId);
            preparedStatement.execute();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    @Override
    public List<Post> findAllByUserId(int userId) throws SQLException {
        List<Post> postList = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE user_id = ? ORDER BY post_id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                postList.add(fetchPost(resultSet));
            }
        }
        return postList;
    }
    
    @Override
    public void addLabelToPost(int postId, int labelId) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE labels_posts SET label_id = ? WHERE post_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,labelId);
            preparedStatement.setInt(2,postId);
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            connection.rollback();
            throw ex;
        }finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    @Override
    public List<Integer> findPostIdByLabelId(int labelId) throws SQLException {
        List<Integer> postIdList = new ArrayList<>();
        String sqlStatement = "SELECT post_id FROM labels_posts WHERE label_id = ? order by post_id";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)){
            preparedStatement.setInt(1,labelId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    postIdList.add(fetchLabelIds(resultSet));
                }
            }
        }
    }
    
    
    private Post fetchPost(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("post_id");
        int userId = resultSet.getInt("user_id");
        String content = resultSet.getString("content");
        return new Post(id, userId, content);
    }
    
    private Integer fetchLabelIds(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("post_id");
        return id;
    }
}
