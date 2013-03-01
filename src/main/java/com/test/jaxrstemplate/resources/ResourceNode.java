package com.test.jaxrstemplate.resources;

import com.test.jaxrstemplate.tree.Node;

public class ResourceNode extends Node<JaxRsResourceTreeData>{

    public ResourceNode(JaxRsResourceTreeData data) {
        super(data);
        data.setNode(this);
    }

}
