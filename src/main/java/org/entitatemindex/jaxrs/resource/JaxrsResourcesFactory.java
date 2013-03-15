package org.entitatemindex.jaxrs.resource;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.entitatemindex.factory.Factory;

public class JaxrsResourcesFactory {

    public JaxrsResources createJaxrsResourcesFromResourceList(List<JaxrsResource> resources) {
        return new JaxrsResources(resources);
    }
    
    
    public JaxrsResources createJaxrsResourcesFromServiceClasses(Class<?>[] serviceClasses) {

        List<JaxrsResource> resources = new ArrayList<JaxrsResource>();

        for (Class<?> serviceClass : serviceClasses) {
            resources.addAll(getResourcesFromServiceClass(serviceClass));
        }

        return new JaxrsResources(resources);
    }

    public JaxrsResources createJaxrsResourcesFromResourcesString(String[] resourcesAsString) {

        List<JaxrsResource> resourceList = new ArrayList<JaxrsResource>();

        for (String resourceAsString : resourcesAsString) {
            resourceList.add(Factory.create(JaxrsResource.class).with(resourceAsString));
        }

        return new JaxrsResources(resourceList);
    }

    private Collection<JaxrsResource> getResourcesFromServiceClass(Class<?> serviceClass) {

        List<JaxrsResource> resources = new ArrayList<JaxrsResource>();

        String classPath = JaxrsReflectionUtils.getClassPath(serviceClass);
        String methodPath;

        if (classPath != null) {
            for (Method method : serviceClass.getMethods()) {

                methodPath = JaxrsReflectionUtils.getMethodPath(method);

                if (methodPath != null) {
                    resources.add(Factory.create(JaxrsResource.class).with(classPath, method));
                }
            }
        }

        return resources;
    }

}
