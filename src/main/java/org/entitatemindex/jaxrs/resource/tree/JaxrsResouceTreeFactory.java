package org.entitatemindex.jaxrs.resource.tree;

import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.jaxrs.resource.JaxrsResources;
import org.entitatemindex.utils.Node;

public class JaxrsResouceTreeFactory {

	public JaxrsResourceTree createFromJaxrsResources(
			JaxrsResources jaxrsResources) {
		JaxrsResourceTree resourceTree = new JaxrsResourceTree("/");
		for (JaxrsResource jaxrsResource : jaxrsResources) {
			addToTree(resourceTree, jaxrsResource);
		}
		return resourceTree;
	}

	private void addToTree(JaxrsResourceTree resourceTree,
			JaxrsResource resource) {
		Node<JaxrsResourceNodeData> root = resourceTree.getRoot();
		addToTree(root, resource, 0);
	}

	private void addToTree(Node<JaxrsResourceNodeData> node, JaxrsResource resource,
			int resourcePartIndex) {
		Node<JaxrsResourceNodeData> resourcePartNode = createResourcePartNode(resource, resourcePartIndex);
		Node<JaxrsResourceNodeData> currentNode;
		if (resourcePartAlreadyInTree(node, resourcePartNode)) {
			currentNode = getNodeAlreadyInTree(node, resourcePartNode);
		} else {
			currentNode = insertResourcePartNode(node, resourcePartNode);
		}
		if (lastResourcePart(resource, resourcePartIndex)) {
			stokWalkingAndAddJaxrsResource(resource, currentNode);
		} else {
			walkNextResourcePart(resource, ++ resourcePartIndex, currentNode);
		}
	}

	private Node<JaxrsResourceNodeData> getNodeAlreadyInTree(Node<JaxrsResourceNodeData> node,
			Node<JaxrsResourceNodeData> nodeToAdd) {
		return node.getChildSameData(nodeToAdd);
	}

	private Node<JaxrsResourceNodeData> insertResourcePartNode(Node<JaxrsResourceNodeData> node,
			Node<JaxrsResourceNodeData> nodeToAdd) {
		return node.addChild(nodeToAdd);
	}

	private boolean resourcePartAlreadyInTree(Node<JaxrsResourceNodeData> node,
			Node<JaxrsResourceNodeData> nodeToAdd) {
		return node.hasChildSameData(nodeToAdd);
	}

	private void stokWalkingAndAddJaxrsResource(JaxrsResource resource,
			Node<JaxrsResourceNodeData> currentNode) {
		currentNode.getData().addJaxrsResource(resource);
	}

	private void walkNextResourcePart(JaxrsResource resource, int partIndex,
			Node<JaxrsResourceNodeData> currentNode) {
		addToTree(currentNode, resource, partIndex);
	}

	private Node<JaxrsResourceNodeData> createResourcePartNode(JaxrsResource resource,
			int partIndex) {
		return new Node<JaxrsResourceNodeData>(new JaxrsResourceNodeData(resource
				.getTemplate().toUri().get(partIndex)));
	}

	private boolean lastResourcePart(JaxrsResource resource,
			int partIndex) {
		return partIndex == resource.getTemplate().toUri().size() - 1;
	}

}
