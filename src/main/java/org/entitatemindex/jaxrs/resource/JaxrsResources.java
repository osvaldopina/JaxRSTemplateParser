package org.entitatemindex.jaxrs.resource;

import java.util.Collection;

import org.entitatemindex.EntitatemIndexError;
import org.entitatemindex.type.Type;
import org.entitatemindex.utils.UnmodifiableList;

public class JaxrsResources extends UnmodifiableList<JaxrsResource> {

    private Type type;

    // TODO refator and test this!!!!
    // TODO type.equals for class verification (maybe someting like compatibles...)
    protected JaxrsResources(Collection<JaxrsResource> resources) {
        super(resources);
        for (JaxrsResource resource : resources) {
            if (type == null) {
                type = getType(resource);
            } else {
                if (getType(resource) != null && (!type.getJavaClass().equals(getType(resource).getJavaClass()))) {
                    throw new EntitatemIndexError("Cound not determine type for resource list " + toString());
                }
            }
        }
    }
    
    public Type getType() {
        return type;
    }

    private Type getType(JaxrsResource resource) {
        if (resource.getHttpMethod() == HttpMethod.GET) {
            return resource.getType();
        } else if (resource.getHttpMethod() == HttpMethod.DELETE) {
            return null;
        } else if (resource.getHttpMethod() == HttpMethod.POST) {
            return resource.getPayLoadType();
        } else if (resource.getHttpMethod() == HttpMethod.PUT) {
            return resource.getPayLoadType();
        } else {
            throw new EntitatemIndexError("Undetermined http method " + resource.getHttpMethod());
        }

    }

}
