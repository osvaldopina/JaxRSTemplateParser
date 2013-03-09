package com.test.jaxrstemplate.resources;

import org.entitatemindex.utils.Tree;

public class ResourceTree extends Tree<ResourceTreeData> {
	

	public ResourceTree(String basePath) {
	    super(new RootTreeData(basePath));
	}

}
