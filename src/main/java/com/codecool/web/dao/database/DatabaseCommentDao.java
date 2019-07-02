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
    
    public DatabaseCommentDao(Connection connection) {
        super(connection);
    }
    
    @Override
    public Post findById(int commentId) throws SQLException {
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
    
    private Comment fetchComment(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("comment_id");
        int postId = resultSet.getInt("post_id");
        int userId = resultSet.getInt("user_id");
        String commentText = resultSet.getString("comment_text");
        String commentDate = resultSet.getString("comment_date");
        return new Comment(id, postId, userId, commentText, commentDate);
    }
}
