package com.test.jaxrstemplate.resources;

import org.entitatemindex.entity.EntityTreeData;
import org.entitatemindex.jaxrs.template.Literal;


public class RootTreeData extends EntityTreeData {
    

    public RootTreeData(String basePath) {
        super(new Literal(basePath));
    }
    
    @Override
    public String toString() {
        return "Root[" + getTemplatePart().toUri()+ "]" ;
    }


}
