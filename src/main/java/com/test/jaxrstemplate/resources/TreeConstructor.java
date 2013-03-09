package com.test.jaxrstemplate.resources;

import java.util.List;

import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.utils.Node;

import com.test.jaxrstemplate.jaxrsresources.tree.JaxrsResourceNodeData;
import com.test.jaxrstemplate.jaxrsresources.tree.JaxrsResourceTree;

public class TreeConstructor {
	
	public static ResourceTree createResourceTree(JaxrsResourceTree jaxrsResourceTree) {
	    ResourceTree resourceTree = new ResourceTree("/");
	    createTree(resourceTree.getRoot(),jaxrsResourceTree.getRoot());
	    
		
		return resourceTree;
		
	}
	
	private static void createTree(Node<ResourceTreeData> resourceNode, Node<JaxrsResourceNodeData> jaxrsResourceNode) {
//	    for(Node<JaxrsResourceNodeData> child:jaxrsResourceNode.getChildren()) {
//	        if (child.getData().getResources().isEmpty()) {
//                ResourceTreeData resourceTreeData;
//	            if (child.getData().getResourcePart() instanceof JaxrsResource) {
//	                String resourceValue = ((JaxrsResource) child.getData().getResourcePart()).get
//	                resourceTreeData = new ResourceTreeData(new Literal());
//	                
//	            }
//	            ResourceTreeData resourceTreeData = new ResourceTreeData( child.getData().getResourcePart());
//	            if (child.getData().getResourcePart())
//	        }
// 	          resourceNode.addChild(new Node<ResourceTreeData>()));
//	    }
    }

    public static String getCommonTemplateStart(List<JaxrsResource> resouces) {
	    
//	    if (resouces.size() ==0) {
//	        return "/";
//	    }
//	    
//	//    String[] templateResources = resouces.get(0).getTemplate().getSubTemplateUntilFirstVariable().getUriResources();
//	    String currentResources = "";
//	    String previousResources = "";
//	    
//	    for(int i=0; i<templateResources.length; i++) {
//	        previousResources = currentResources;
//	        currentResources= currentResources + "/" + templateResources[i]; 
//	        for(JaxrsResource resource:resouces) {
//	            String uri = regularizeUri(resource.getTemplate().toUri());
//	            if (! uri.startsWith(currentResources)) {
//	              return previousResources;
//	            }
//	        }
//	    }
//	    return currentResources.equals("")?"/":currentResources;
        return null;
	}
	
	public static String regularizeUri(String uri)  {
	    if (uri.startsWith("/")) {
	        return uri;
	    }
	    else {
	        return "/" + uri;
	    }
	}
	
	
	
}
