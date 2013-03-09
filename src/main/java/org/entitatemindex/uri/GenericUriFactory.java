package org.entitatemindex.uri;

import java.util.ArrayList;
import java.util.List;

public class GenericUriFactory {
    
    public  GenericUriFactory() {
        
    }
    
    public GenericUri create(String uri) {
        String[] uriParts = uri.split("/");
        List<UriResource> resources = new ArrayList<UriResource>();
        
        for(String uriPart:uriParts) {
            if (!uriPart.trim().equals("")) {
                resources.add(new UriResource(uriPart));
            }
        }
        return new GenericUri(resources);
    }
    
    public GenericUri create(List<UriResource> resources) {
        return new GenericUri(resources);
    }

}
