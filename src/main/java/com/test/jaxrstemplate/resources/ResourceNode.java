package com.test.jaxrstemplate.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.test.jaxrstemplate.TemplatePart;
import com.test.jaxrstemplate.discover.JaxrsResource;

public class ResourceNode {

	private TemplatePart templatePart;

	private JaxrsResource jaxRsResource;
	
	private List<ResourceNode> children = new ArrayList<ResourceNode>();

	public ResourceNode(TemplatePart templatePart,
			JaxrsResource jaxRsResource) {
		this.templatePart = templatePart;
		this.jaxRsResource = jaxRsResource;
	}

	public ResourceNode(JaxrsResource jaxRsResource) {
		this.jaxRsResource = jaxRsResource;
	}

	public ResourceNode(TemplatePart templaPart) {
		this.templatePart = templaPart;
	}

	public TemplatePart getTemplatePart() {

		return templatePart;
	}

	public JaxrsResource getJaxRsResource() {
		return jaxRsResource;
	}
	
	public List<ResourceNode> getChildren() {
		return Collections.unmodifiableList(children);
	}
	
	public void addChild(ResourceNode resourceElement) {
		children.add(resourceElement);
	}

}
