package com.test.jaxrstemplate.resources;

import com.test.jaxrstemplate.Literal;


public class RootTreeData extends JaxRsResourceTreeData {
    

    public RootTreeData(String basePath) {
        super(new Literal(basePath));
    }
    
    @Override
    public String toString() {
        return "Root[" + getTemplatePart().toUri()+ "]" ;
    }


}
