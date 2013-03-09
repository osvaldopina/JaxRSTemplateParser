package org.entitatemindex.jaxrs.resource.tree;

import org.entitatemindex.uri.UriResource;
import org.entitatemindex.utils.Node;
import org.entitatemindex.utils.Tree;

public class JaxrsResourceTree extends Tree<NodeData> {

    public JaxrsResourceTree(String path) {
        super(new Node<NodeData>(new NodeData(new UriResource(path))));
    }

}
