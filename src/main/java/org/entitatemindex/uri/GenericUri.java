package org.entitatemindex.uri;

import java.util.List;

import org.entitatemindex.utils.StringUtils;
import org.entitatemindex.utils.UnmodifiableList;

public class GenericUri extends UnmodifiableList<UriResource>{
    
    protected GenericUri(List<UriResource> resources) {
        super(resources);
    }
    
    @Override
    public String toString() {
        StringBuffer tmp = new StringBuffer();
        tmp.append("/");
        boolean first = true;
        for(UriResource uriResource:this) {
            first = StringUtils.addIfFalse(first, tmp, "/");
            tmp.append(uriResource.toString());
        }
        return tmp.toString();
    }

    public UriResource getLastResource() {
        return get(size()-1);
    }
    

}
