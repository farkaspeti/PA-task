package com.codecool.web.service.simple;

import com.codecool.web.dao.CommentDao;
import com.codecool.web.model.Comment;
import com.codecool.web.service.CommentService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimpleCommentService implements CommentService {
    
    private final CommentDao commentDao;
    
    public SimpleCommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    
    @Override
    public Comment findById(int postId) throws SQLException, ServiceException {
        try{
            return commentDao.findById(postId);
        }catch (IllegalArgumentException ex){
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public Comment addComment(int postId, int userId, String commentText) throws SQLException, ServiceException {
        try {
            return commentDao.add(postId, userId, commentText);
        }catch (IllegalArgumentException ex ){
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public void updateComment(int commentId, String commentText) throws SQLException, ServiceException {
        try{
            commentDao.update(commentId,commentText);
        }catch (IllegalArgumentException ex ){
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public void deleteComment(int commentId) throws SQLException, ServiceException {
    
    }
    
    @Override
    public List<Comment> findAllByUserId(int userId) throws SQLException, ServiceException {
        return null;
    }
    
    @Override
    public List<Comment> findAllByPostId(int postId) throws SQLException, ServiceException {
        return null;
    }
}
