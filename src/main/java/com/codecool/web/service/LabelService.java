package com.codecool.web.service;

import com.codecool.web.model.Label;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface LabelService {
    
    List<Label> findAll() throws SQLException, ServiceException;
    
    Label addLabel(String labelContent) throws SQLException, ServiceException;
}
