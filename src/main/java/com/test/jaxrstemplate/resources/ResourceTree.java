package com.test.jaxrstemplate.resources;

import com.test.jaxrstemplate.Literal;

public class ResourceTree {
	
	private ResourceNode root;


	public ResourceTree() {
		root = new ResourceNode(new Literal("/"));
	}

	
	public ResourceNode getRoot() {
		return root;
	}



	
	
	
	
	
	
	
	
	

}
