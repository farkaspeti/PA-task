package com.codecool.web.servlet;

import com.codecool.web.dao.PostDao;
import com.codecool.web.dao.database.DatabasePostDao;
import com.codecool.web.model.Post;
import com.codecool.web.model.User;
import com.codecool.web.service.PostService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimplePostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/update_posts")
public class UpdatePostServlet extends AbstractServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (Connection connection = getConnection(req.getServletContext())) {
            PostDao postDao = new DatabasePostDao(connection);
            PostService postService = new SimplePostService(postDao);
            int userId = Integer.parseInt(req.getParameter("userId"));
            List<Post> postsList = postService.findAllByUserId(userId);
            sendMessage(resp, HttpServletResponse.SC_OK, postsList);
        } catch (SQLException e) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            e.printStackTrace();
        } catch (ServiceException e) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        java.util.Date date = new java.util.Date();
        Date postDate = new Date(date.getTime());
        resp.setContentType("text/html;charset=UTF-8");
        try (Connection connection = getConnection(req.getServletContext())) {
            PostDao postDao = new DatabasePostDao(connection);
            PostService postService = new SimplePostService(postDao);
            int postId = Integer.valueOf(req.getParameter("PostId"));
            String content = req.getParameter("content");
            postService.updatePost(postId, content);
            User user = (User) req.getSession().getAttribute("user");
            int userId = user.getId();
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            new Post(postId, userId, firstName, lastName, content, postDate);
            sendMessage(resp, HttpServletResponse.SC_OK,null);
        } catch (SQLException ex) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
            ex.getMessage();
        } catch (ServiceException ex) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
            ex.getMessage();
        }
    }
}