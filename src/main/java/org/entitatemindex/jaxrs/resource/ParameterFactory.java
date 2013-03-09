package org.entitatemindex.jaxrs.resource;

import org.entitatemindex.factory.Factory;
import org.entitatemindex.type.Type;

public class ParameterFactory {
    
    
    public Parameter createFromString(String parameterAsString) {
        String parameterParts[] = parameterAsString.split(":");
        return new Parameter(parameterParts[0], Factory.create(Type.class).with(parameterParts[1]));
    }
    
    public Parameter create(String name, Type type) {
        return new Parameter(name, type);
    }

}
