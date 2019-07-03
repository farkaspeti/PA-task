package com.codecool.web.dao.database;

import com.codecool.web.dao.LabelDao;
import com.codecool.web.model.Label;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseLabelDao extends AbstractDao implements LabelDao {
    
    public DatabaseLabelDao(Connection connection) { super(connection); }
    
    @Override
    public List<Label> findAll() throws SQLException {
        return null;
    }
    
    @Override
    public Label add(String labelContent) throws SQLException {
        return null;
    }
}
