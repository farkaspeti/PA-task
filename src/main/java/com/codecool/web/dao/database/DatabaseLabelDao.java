package com.codecool.web.dao.database;

import com.codecool.web.dao.LabelDao;
import com.codecool.web.model.Label;

import java.sql.*;
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
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO labels (label_content) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(2, labelContent);
            executeInsert(preparedStatement);
            int id = fetchGeneratedId(preparedStatement);
            return new Label(id, labelContent);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    private Label fetchLabel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("post_id");
        String labelContent = resultSet.getString("label_content");
        return new Label(id, labelContent);
    }
}
