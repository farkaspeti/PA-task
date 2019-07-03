package com.codecool.web.service.simple;

import com.codecool.web.dao.LabelDao;
import com.codecool.web.model.Label;
import com.codecool.web.service.LabelService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimpleLabelService implements LabelService {
    
    private final LabelDao labelDao;
    
    public SimpleLabelService(LabelDao labelDao) { this.labelDao = labelDao; }
    
    @Override
    public List<Label> findAll() throws SQLException, ServiceException {
        try {
            return labelDao.findAll();
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public Label addLabel(String labelContent) throws SQLException, ServiceException {
        try{
           return labelDao.add(labelContent);
        }catch (IllegalArgumentException ex ){
            throw new ServiceException(ex.getMessage());
        }
    }
}
