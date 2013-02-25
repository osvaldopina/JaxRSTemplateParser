package com.test.jaxrstemplate;

public class Variable implements TemplatePart {
    
    private String name;
    
    public Variable(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public String toUri() {
        return "{" +name +"}";
    }

    @Override
    public String toString() {
        return "Variable [name=" + name + "]";
    }
}
