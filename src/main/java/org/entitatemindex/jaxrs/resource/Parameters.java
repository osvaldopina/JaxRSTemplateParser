package org.entitatemindex.jaxrs.resource;

import java.util.Collection;

import org.entitatemindex.utils.UnmodifiableList;


public class Parameters extends UnmodifiableList<Parameter> {

    protected Parameters(Collection<Parameter> parameters) {
        super(parameters);
    }
    
    protected Parameters() {
    }
    
    

}
