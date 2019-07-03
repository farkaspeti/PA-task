package com.codecool.web.dao.database;

import com.codecool.web.dao.LabelDao;
import com.codecool.web.model.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseLabelDao extends AbstractDao implements LabelDao {
    
    public DatabaseLabelDao(Connection connection) { super(connection); }
    
    @Override
    public List<Label> findAll() throws SQLException {
        List<Label> labelList = new ArrayList<>();
        String sql = "SELECT * FROM labels ORDER BY label_id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                labelList.add(fetchLabel(resultSet));
            }
        
        }
        return labelList;
    }
    
    @Override
    public Label add(String labelContent) throws SQLException {
        return null;
    }
}
