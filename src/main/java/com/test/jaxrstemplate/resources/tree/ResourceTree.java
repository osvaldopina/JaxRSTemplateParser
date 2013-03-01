package com.test.jaxrstemplate.resources.tree;

import com.test.jaxrstemplate.tree.Tree;

public class ResourceTree extends Tree<ResourceNodeData> {

    public ResourceTree(String basePath) {
        super(new ResourceNodeData(new ResourcePart(basePath)));
        getRoot().getData().setNode(getRoot());
    }

}
