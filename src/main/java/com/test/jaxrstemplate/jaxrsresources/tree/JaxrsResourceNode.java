package com.test.jaxrstemplate.jaxrsresources.tree;

import org.entitatemindex.utils.Node;

public class JaxrsResourceNode extends Node<JaxrsResourceNodeData> {

    public JaxrsResourceNode(JaxrsResourceNodeData data) {
        super(data);
        this.getData().setNode(this);
    }


}
