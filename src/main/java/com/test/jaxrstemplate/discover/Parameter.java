package com.test.jaxrstemplate.discover;

import com.test.type.Type;
import com.test.type.TypeFactory;

public class Parameter {
    
     private String name;
     
     private Type type;
     

    public Parameter(String name, Type type) {
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
    
    public static Parameter fromString(String parameterAsString) {
        String parameterParts[] = parameterAsString.split(":");
        return new Parameter(parameterParts[0], TypeFactory.createFromString(parameterParts[1]));
    }
    
    

}
