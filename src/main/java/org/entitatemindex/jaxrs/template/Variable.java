package org.entitatemindex.jaxrs.template;

public class Variable implements TemplatePart {
    
    private String name;
    
    public Variable(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return "{" +name +"}";
    }

    @Override
    public String toString() {
        return "Variable [name=" + name + "]";
    }
}
