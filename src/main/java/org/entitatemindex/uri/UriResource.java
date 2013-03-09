package org.entitatemindex.uri;

public class UriResource {

    private String name;
    
    public UriResource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public boolean isVariable() {
        return (name.startsWith("{") && name.endsWith("}"));
    }
    
    public String getVariableName() {
        return name.substring(1, name.length()-1);
    }
    
    @Override
    public String toString() {
        return name;
    }

}
