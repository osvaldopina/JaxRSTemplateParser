package com.test.jaxrstemplate;

public class Literal implements TemplatePart {
    
    private String value;
    
    public Literal(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "Literal [value=" + value + "]";
    }
    
    @Override
    public String toUri() {
        return getValue();
    }
    
    
    
}
