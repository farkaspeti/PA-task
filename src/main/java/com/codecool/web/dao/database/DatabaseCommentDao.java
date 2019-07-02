package com.codecool.web.dao.database;

import com.codecool.web.dao.CommentDao;
import com.codecool.web.model.Comment;
import com.codecool.web.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCommentDao extends AbstractDao implements CommentDao {
    
    public DatabaseCommentDao(Connection connection) {
        super(connection);
    }
    
    @Override
    public Comment findById(int commentId) throws SQLException {
        String sql = "SELECT * FROM comments WHERE comment_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, commentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchComment(resultSet);
                }
            }
        }
        return null;
    }
    
    @Override
    public Comment add(int postId,int userId, String commentText, String commentDate) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO comments (post_id, user_id, comment_text, comment_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, commentText);
            preparedStatement.setString(4, commentDate);
            executeInsert(preparedStatement);
            int id = fetchGeneratedId(preparedStatement);
            return new Comment(id, postId, userId, commentText, commentDate);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    @Override
    public void update(int commentId, String commentText) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE comments SET comment_text=? WHERE comment_id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, commentText);
            preparedStatement.setInt(2, commentId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    @Override
    public void delete(int commentId) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sqlString = "DELETE FROM comments cascade WHERE comment_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {
            preparedStatement.setInt(1, commentId);
            preparedStatement.execute();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    @Override
    public List<Comment> findAllByUserId(int userId) throws SQLException {
        List<Comment> commentList = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE user_id = ? ORDER BY comment_id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                commentList.add(fetchComment(resultSet));
            }
        }
        return commentList;
    }
    
    @Override
    public List<Comment> findAllByPostId(int postId) throws SQLException {
        return null;
    }
    
    private Comment fetchComment(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("comment_id");
        int postId = resultSet.getInt("post_id");
        int userId = resultSet.getInt("user_id");
        String commentText = resultSet.getString("comment_text");
        String commentDate = resultSet.getString("comment_date");
        return new Comment(id, postId, userId, commentText, commentDate);
    }
}
