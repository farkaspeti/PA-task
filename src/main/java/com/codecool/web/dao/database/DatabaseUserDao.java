package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.model.enums.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseUserDao extends AbstractDao implements UserDao {
    public DatabaseUserDao(Connection connection) {
        super(connection);
    }
    
    @Override
    public User findByEmail(String email) throws SQLException {
        return null;
    }
    
    @Override
    public void add(String email, String password, String firstName, String lastName) throws SQLException {
    
    }
    
    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }
    
    private User fetchUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("user_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        UserType userType = UserType.valueOf(resultSet.getString("user_type"));
        return new User(id, email, password, firstName, lastName,  userType);
    }
}
