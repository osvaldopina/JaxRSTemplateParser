package com.test.jaxrstemplate.resources;

import java.util.List;

import com.test.jaxrstemplate.discover.JaxrsResource;

public class TreeConstructor {
	
	public static ResourceTree createResourceTree(List<JaxrsResource> jaxrsResources) {
	    String basePath = getCommonTemplateStart(jaxrsResources);
	    ResourceTree resourceTree = new ResourceTree(basePath);
		
		return resourceTree;
		
	}
	
	public static String getCommonTemplateStart(List<JaxrsResource> resouces) {
	    
	    if (resouces.size() ==0) {
	        return "/";
	    }
	    
	    String[] templateResources = resouces.get(0).getTemplate().getSubTemplateUntilFirstVariable().getUriResources();
	    String currentResources = "";
	    String previousResources = "";
	    
	    for(int i=0; i<templateResources.length; i++) {
	        previousResources = currentResources;
	        currentResources= currentResources + "/" + templateResources[i]; 
	        for(JaxrsResource resource:resouces) {
	            String uri = regularizeUri(resource.getTemplate().toUri());
	            if (! uri.startsWith(currentResources)) {
	              return previousResources;
	            }
	        }
	    }
	    return currentResources.equals("")?"/":currentResources;
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
