package com.codecool.web.service.simple;

import com.codecool.web.model.Label;
import com.codecool.web.service.LabelService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimpleLabelService implements LabelService {
    @Override
    public List<Label> findAll() throws SQLException, ServiceException {
        return null;
    }
    
    @Override
    public Label addLabel(String labelContent) throws SQLException, ServiceException {
        return null;
    }
}
