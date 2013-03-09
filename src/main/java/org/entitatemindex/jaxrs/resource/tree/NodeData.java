package org.entitatemindex.jaxrs.resource.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.uri.UriResource;

public class NodeData {
    
    private List<JaxrsResource> jaxrsResources = new ArrayList<JaxrsResource>();

    private UriResource uriResource;

    protected NodeData(UriResource uriResource) {
        this.uriResource = uriResource;
    }

    public List<JaxrsResource> getJaxrsResources() {
        return Collections.unmodifiableList(jaxrsResources);
    }
    
    protected void addJaxrsResource(JaxrsResource jaxrsResource) {
        jaxrsResources.add(jaxrsResource);
    }

    public UriResource getUriResource() {
        return uriResource;
    }
    
    @Override
    public String toString() {
        StringBuffer tmp = new StringBuffer();
        
        tmp.append(uriResource);
        for (JaxrsResource jaxrsesource:jaxrsResources) {
            tmp.append(jaxrsesource.getHttpMethod());
            tmp.append( " ");
            tmp.append(jaxrsesource.getTemplate());
        }
        return super.toString();
    }


}
