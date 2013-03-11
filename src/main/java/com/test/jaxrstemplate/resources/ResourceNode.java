package com.test.jaxrstemplate.resources;

import org.entitatemindex.entity.EntityTreeData;
import org.entitatemindex.utils.Node;

public class ResourceNode extends Node<EntityTreeData>{

    public ResourceNode(EntityTreeData data) {
        super(data);
        data.setNode(this);
    }

}
