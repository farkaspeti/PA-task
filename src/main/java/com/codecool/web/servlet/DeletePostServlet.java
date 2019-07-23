package com.codecool.web.servlet;

import com.codecool.web.dao.PostDao;
import com.codecool.web.dao.database.DatabasePostDao;
import com.codecool.web.service.PostService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimplePostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/delete_post")
public class DeletePostServlet extends AbstractServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (Connection connection = getConnection(req.getServletContext())) {
            PostDao postDao = new DatabasePostDao(connection);
            PostService postService = new SimplePostService(postDao);
            String postId = req.getParameter("id");
            postService.deletePost(Integer.valueOf(postId));
            System.out.println(postId);
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
