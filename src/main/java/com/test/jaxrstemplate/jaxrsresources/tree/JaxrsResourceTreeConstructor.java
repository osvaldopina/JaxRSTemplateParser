package com.test.jaxrstemplate.jaxrsresources.tree;

import java.util.List;

import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.utils.Node;


public class JaxrsResourceTreeConstructor {
    
    public static JaxrsResourceTree create(List<JaxrsResource> resources) {
        JaxrsResourceTree resourceTree = new JaxrsResourceTree("/");
        for(JaxrsResource resource:resources) {
            addToTree(resourceTree, resource);
        }
        return resourceTree;
    }

    private static void addToTree(JaxrsResourceTree resourceTree, JaxrsResource resource) {
        Node<JaxrsResourceNodeData> root = resourceTree.getRoot();
        addToTree(root,resource,0);
    }

    private static void addToTree(Node<JaxrsResourceNodeData> node, JaxrsResource resource, int partIndex) {
        Node<JaxrsResourceNodeData> nodeToAdd = new Node<JaxrsResourceNodeData>(new JaxrsResourceNodeData(resource.getResourceParts().get(partIndex)));
        nodeToAdd.getData().setNode(nodeToAdd);
        Node<JaxrsResourceNodeData> currentNode; 
        if (node.hasChildSameData(nodeToAdd) == null) {
            node.addChild(nodeToAdd);
            currentNode = nodeToAdd;
        }
        else {
            currentNode = node.hasChildSameData(nodeToAdd);
        }
        if (partIndex == resource.getResourceParts().size() -1) {
            currentNode.getData().addResource(resource);
        }
        if (partIndex < resource.getResourceParts().size() -1) {
            partIndex ++;
            addToTree(currentNode,resource,partIndex);
        }
    }

}
