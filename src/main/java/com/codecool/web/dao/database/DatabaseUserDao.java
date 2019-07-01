package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.model.enums.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUserDao extends AbstractDao implements UserDao {
    public DatabaseUserDao(Connection connection) {
        super(connection);
    }
    
    @Override
    public User findByEmail(String email) throws SQLException {
        if (email == null || "".equals(email)) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String sql = "SELECT first_name, last_name, password, email, user_type FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }
    
    @Override
    public void add(String email, String password, String firstName, String lastName) throws SQLException {
        if (email == null || "".equals(email)) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        } else if (password == null || "".equals(password)) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        } else if (firstName == null || "".equals(firstName)) {
            throw new IllegalArgumentException("First Name cannot be null or empty");
        } else if (lastName == null || "".equals(lastName)) {
            throw new IllegalArgumentException("Last Name cannot be null or empty");
        }
        String sql = "INSERT INTO users (first_name, last_name, password, email, user_type) VALUES (?, ?, ?, ?, 'USER')";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, password);
            statement.setString(4, email);
            executeInsert(statement);
        }
    }
    
    @Override
    public List<User> findAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users order by user_id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                userList.add(fetchUser(resultSet));
            }
        }
        return userList;
    }
    
    private User fetchUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("user_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        UserType userType = UserType.valueOf(resultSet.getString("user_type"));
        return new User(id, email, password, firstName, lastName, userType);
    }
}
