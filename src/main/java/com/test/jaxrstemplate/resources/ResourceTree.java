package com.test.jaxrstemplate.resources;

import com.test.jaxrstemplate.Literal;
import com.test.jaxrstemplate.tree.Tree;

public class ResourceTree extends Tree<JaxRsResourceTreeData> {
	

	public ResourceTree() {
	    super(new JaxRsResourceTreeData(new Literal("/"),null));
	}

}
