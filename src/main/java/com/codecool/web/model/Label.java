package com.codecool.web.model;

public final class Label extends AbstractModel {
    
    private final String labelContent;
    
    public Label(int id, String labelContent) {
        super(id);
        this.labelContent = labelContent;
    }
    
    public String getLabelContent() {
        return labelContent;
    }
}
