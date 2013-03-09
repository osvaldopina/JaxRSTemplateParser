package com.test.jaxrstemplate.resources;

import org.entitatemindex.jaxrs.template.Literal;


public class RootTreeData extends ResourceTreeData {
    

    public RootTreeData(String basePath) {
        super(new Literal(basePath));
    }
    
    @Override
    public String toString() {
        return "Root[" + getTemplatePart().toUri()+ "]" ;
    }


}
