package com.codecool.web.dao;

import com.codecool.web.model.Label;

import java.sql.SQLException;
import java.util.List;

public interface LabelDao {
    
    List<Label> findAll() throws SQLException;
    
    Label add(String labelContent) throws SQLException;
    
}
