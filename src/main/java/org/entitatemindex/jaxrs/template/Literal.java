package org.entitatemindex.jaxrs.template;

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
    
    
}
