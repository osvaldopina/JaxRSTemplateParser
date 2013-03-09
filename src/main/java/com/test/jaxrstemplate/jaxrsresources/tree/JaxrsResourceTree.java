package com.test.jaxrstemplate.jaxrsresources.tree;

import org.entitatemindex.utils.Tree;

public class JaxrsResourceTree extends Tree<JaxrsResourceNodeData> {

    public JaxrsResourceTree(String basePath) {
        super(new JaxrsResourceNodeData(new JaxrsResourcePart(basePath)));
        getRoot().getData().setNode(getRoot());
    }

}
