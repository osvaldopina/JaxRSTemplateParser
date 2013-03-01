package com.test.jaxrstemplate.resources.tree;

import com.test.jaxrstemplate.tree.Node;

public class ResourceNode extends Node<ResourceNodeData> {

    public ResourceNode(ResourceNodeData data) {
        super(data);
        this.getData().setNode(this);
    }


}
