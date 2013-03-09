package org.entitatemindex.jaxrs.resource.tree;

import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.jaxrs.resource.JaxrsResources;
import org.entitatemindex.utils.Node;

public class JaxrsResouceTreeFactory {
    
    public JaxrsResourceTree createFromJaxrsResources(JaxrsResources jaxrsResources) {
        JaxrsResourceTree resourceTree = new JaxrsResourceTree("/");
        for(JaxrsResource jaxrsResource:jaxrsResources) {
            addToTree(resourceTree, jaxrsResource);
        }
        return resourceTree;
    }

    private static void addToTree(JaxrsResourceTree resourceTree, JaxrsResource resource) {
        Node<NodeData> root = resourceTree.getRoot();
        addToTree(root,resource,0);
    }

    private static void addToTree(Node<NodeData> node, JaxrsResource resource, int partIndex) {
        Node<NodeData> nodeToAdd = new Node<NodeData>(new NodeData(resource.getTemplate().toUri().get(partIndex)));
        Node<NodeData> currentNode; 
        if (node.getChild(nodeToAdd) == null) {
            node.addChild(nodeToAdd);
            currentNode = nodeToAdd;
        }
        else {
            currentNode = node.getChild(nodeToAdd);
        }
        if (partIndex == resource.getTemplate().toUri().size() -1) {
            currentNode.getData().addJaxrsResource(resource);
        }
        if (partIndex < resource.getTemplate().toUri().size() -1) {
            partIndex ++;
            addToTree(currentNode,resource,partIndex);
        }
    }
    

}
