package org.entitatemindex.jaxrs.resource;

import java.util.Collection;

import org.entitatemindex.utils.UnmodifiableList;

public class JaxrsResources extends UnmodifiableList<JaxrsResource> {

    protected JaxrsResources(Collection<JaxrsResource> resources) {
        super(resources);
    }

}
