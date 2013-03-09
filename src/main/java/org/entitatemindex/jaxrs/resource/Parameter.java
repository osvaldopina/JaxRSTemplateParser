package org.entitatemindex.jaxrs.resource;

import org.entitatemindex.type.Type;

public class Parameter {
    
     private String name;
     
     private Type type;
     

    protected Parameter(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
    
    public String toString() {
    	return name +":" + type.toString();
    }
    
}
