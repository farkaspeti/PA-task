package com.codecool.web.servlet;

import com.codecool.web.dao.CommentDao;
import com.codecool.web.dao.PostDao;
import com.codecool.web.dao.database.DatabaseCommentDao;
import com.codecool.web.dao.database.DatabasePostDao;
import com.codecool.web.model.Comment;
import com.codecool.web.model.Post;
import com.codecool.web.service.CommentService;
import com.codecool.web.service.PostService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleCommentService;
import com.codecool.web.service.simple.SimplePostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/comments")
public class CommentsServlet extends AbstractServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (Connection connection = getConnection(req.getServletContext())) {
            CommentDao commentDao = new DatabaseCommentDao(connection);
            CommentService commentService = new SimpleCommentService(commentDao);
            int id = Integer.parseInt(req.getParameter("postId"));
            List<Comment> commentList = commentService.findAllByPostId(id);
            sendMessage(resp, HttpServletResponse.SC_OK, commentList);
        } catch (SQLException e) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            e.printStackTrace();
        } catch (ServiceException e) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            e.printStackTrace();
        }
    }
    
}
