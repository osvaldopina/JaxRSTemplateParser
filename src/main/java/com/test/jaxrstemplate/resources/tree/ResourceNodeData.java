package com.test.jaxrstemplate.resources.tree;

import com.test.jaxrstemplate.discover.JaxrsResource;
import com.test.jaxrstemplate.tree.Node;

public class ResourceNodeData {

    private ResourcePart resourcePart;
    
    private JaxrsResource resource;
    
    private Node<ResourceNodeData> node;
    
    public ResourceNodeData(ResourcePart resourcePart, JaxrsResource resource) {
        super();
        this.resourcePart = resourcePart;
        this.resource = resource;
    }
    
    public JaxrsResource getResource() {
        return resource;
    }
    
    public void setResource(JaxrsResource resource) {
        this.resource = resource;
    }

    public ResourcePart getResourcePart() {
        return resourcePart;
    }
    
    public void setNode(Node<ResourceNodeData> node)  {
        this.node = node;
    }
    
    public Node<ResourceNodeData> getNode() {
        return node;
    }
    
    @Override
    public String toString() {
        StringBuffer tmp = new StringBuffer();
        tmp.append(getNode().getIdentation());
        tmp.append(getNode().getData().getResourcePart().toString());
        if ( getNode().getData().getResource() != null) {
            tmp.append("  [");
            tmp.append(getNode().getData().getResource().getTemplate().toUri());
            tmp.append("]");
        }
        return tmp.toString();
    }


}
