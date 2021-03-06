package com.codecool.web.dao;

import com.codecool.web.model.Label;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface LabelDao {
    
    List<Label> findAll() throws SQLException, ServiceException;
    
    Label add(String labelContent) throws SQLException, ServiceException;
    
}
