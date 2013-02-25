package com.test.jaxrstemplate.resources;

import com.test.jaxrstemplate.TemplatePart;
import com.test.jaxrstemplate.discover.JaxrsResource;

public class JaxRsResourceTreeData {
    
    private TemplatePart templatePart;

    private JaxrsResource jaxRsResource;

    
    public JaxRsResourceTreeData(TemplatePart templatePart, JaxrsResource jaxRsResource) {
        this.templatePart = templatePart;
        this.jaxRsResource = jaxRsResource;
    }

    
    public JaxrsResource getJaxRsResource() {
        return jaxRsResource;
    }

    public void setJaxRsResource(JaxrsResource jaxRsResource) {
        this.jaxRsResource = jaxRsResource;
    }

    public TemplatePart getTemplatePart() {
        return templatePart;
    }

    public void setTemplatePart(TemplatePart templatePart) {
        this.templatePart = templatePart;
    }
    
    @Override
    public String toString() {
        return templatePart + "  --  " + jaxRsResource.toString();
    }


}
