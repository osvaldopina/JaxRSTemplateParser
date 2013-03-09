package com.test.jaxrstemplate.resources;

import org.entitatemindex.utils.Node;

public class ResourceNode extends Node<ResourceTreeData>{

    public ResourceNode(ResourceTreeData data) {
        super(data);
        data.setNode(this);
    }

}
