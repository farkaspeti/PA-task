package com.codecool.web.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {
    
    public String formatDate() {
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        String finalDate = dateObj.format(dateForm);
        return finalDate;
    }
}
