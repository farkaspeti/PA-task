package com.codecool.web.servlet;

import com.codecool.web.dao.PostDao;
import com.codecool.web.dao.database.DatabasePostDao;
import com.codecool.web.model.Post;
import com.codecool.web.service.PostService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimplePostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/posts")
public class PostsServlet extends AbstractServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (Connection connection = getConnection(req.getServletContext())) {
            PostDao postDao = new DatabasePostDao(connection);
            PostService postService = new SimplePostService(postDao);
            List<Post> postsList = postService.findAll();
            sendMessage(resp, HttpServletResponse.SC_OK, postsList);
        } catch (SQLException e) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            e.printStackTrace();
        } catch (ServiceException e) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            e.printStackTrace();
        }
    }
}
