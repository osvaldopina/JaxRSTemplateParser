package com.test.jaxrstemplate.resources.tree;

import java.util.List;

import com.test.jaxrstemplate.discover.JaxrsResource;
import com.test.jaxrstemplate.tree.Node;

public class ResourceTreeConstructor {
    
    public static ResourceTree create(List<JaxrsResource> resources) {
        ResourceTree resourceTree = new ResourceTree("/");
        for(JaxrsResource resource:resources) {
            addToTree(resourceTree, resource);
        }
        return resourceTree;
    }

    private static void addToTree(ResourceTree resourceTree, JaxrsResource resource) {
        Node<ResourceNodeData> root = resourceTree.getRoot();
        addToTree(root,resource,0);
    }

    private static void addToTree(Node<ResourceNodeData> node, JaxrsResource resource, int partIndex) {
        Node<ResourceNodeData> nodeToAdd = new Node<ResourceNodeData>(new ResourceNodeData(resource.getResourceParts().get(partIndex)));
        nodeToAdd.getData().setNode(nodeToAdd);
        Node<ResourceNodeData> currentNode; 
        if (node.getChild(nodeToAdd) == null) {
            node.addChild(nodeToAdd);
            currentNode = nodeToAdd;
        }
        else {
            currentNode = node.getChild(nodeToAdd);
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
