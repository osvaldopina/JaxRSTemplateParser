package com.test.jaxrstemplate.resources.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.test.jaxrstemplate.discover.JaxrsResource;
import com.test.jaxrstemplate.tree.Node;

public class ResourceNodeData {

	private ResourcePart resourcePart;

	private List<JaxrsResource> resources = new ArrayList<JaxrsResource>();

	private Node<ResourceNodeData> node;

	public ResourceNodeData(ResourcePart resourcePart) {
		super();
		this.resourcePart = resourcePart;
	}

	public List<JaxrsResource> getResources() {
		return Collections.unmodifiableList(resources);
	}

	public void addResource(JaxrsResource resource) {
		resources.add(resource);
	}

	public ResourcePart getResourcePart() {
		return resourcePart;
	}

	public void setNode(Node<ResourceNodeData> node) {
		this.node = node;
	}

	public Node<ResourceNodeData> getNode() {
		return node;
	}

	@Override
	public String toString() {
		StringBuffer tmp = new StringBuffer();
		tmp.append(getNode().getIdentation());
		tmp.append(getNode().getData().getResourcePart().toString());
		if (resources.size() > 0) {
			boolean first = true;
			tmp.append("  [");
			for (JaxrsResource resource : resources) {
				if (first) {
					first = false;
				}
				else {
					tmp.append(", ");
				}
				tmp.append(resource.getHttpMethod());
				tmp.append(" ");
				tmp.append(resource.getTemplate().toUri());
			}
			tmp.append("]");
		}
		return tmp.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((resourcePart == null) ? 0 : resourcePart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceNodeData other = (ResourceNodeData) obj;
		if (resourcePart == null) {
			if (other.resourcePart != null)
				return false;
		} else if (!resourcePart.equals(other.resourcePart))
			return false;
		return true;
	}

}
