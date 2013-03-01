package com.test.jaxrstemplate.resources;

import com.test.jaxrstemplate.tree.Tree;

public class ResourceTree extends Tree<JaxRsResourceTreeData> {
	

	public ResourceTree(String basePath) {
	    super(new RootTreeData(basePath));
	}

}
