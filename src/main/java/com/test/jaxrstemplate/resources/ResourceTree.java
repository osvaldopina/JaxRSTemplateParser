package com.test.jaxrstemplate.resources;

import org.entitatemindex.entity.EntityTreeData;
import org.entitatemindex.utils.Tree;

public class ResourceTree extends Tree<EntityTreeData> {
	

	public ResourceTree(String basePath) {
	    super(new RootTreeData(basePath));
	}

}
