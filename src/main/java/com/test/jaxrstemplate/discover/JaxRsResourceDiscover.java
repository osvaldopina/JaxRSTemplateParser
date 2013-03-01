package com.test.jaxrstemplate.discover;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.test.jaxrstemplate.JaxRsTemplate;
import com.test.type.Type;
import com.test.type.TypeFactory;

public class JaxRsResourceDiscover {
    
    public static List<JaxrsResource> getResourcesFromServiceClasses(Class<?>...serviceClasses) {
        
        List<JaxrsResource> resources = new ArrayList<JaxrsResource>();
        
        for(Class<?> serviceClass:serviceClasses) {
          resources.addAll(getResourcesFromServiceClass(serviceClass));
        }
        
        return resources;
    }

    private static Collection<JaxrsResource> getResourcesFromServiceClass(Class<?> serviceClass) {
        
        List<JaxrsResource> resources = new ArrayList<JaxrsResource>();

        String classPath = JaxrsReflectionUtils.getClassPath(serviceClass);
        String methodPath;
        
        if (classPath != null) {
           for(Method method:serviceClass.getMethods()) {
        	 
               methodPath = JaxrsReflectionUtils.getMethodPath(method);
               
               if (methodPath != null) {
                   resources.add(getJaxRsResouceFromMethod(classPath, method));
               }
           }
        }
        
        return resources;
    }

    private static JaxrsResource getJaxRsResouceFromMethod(String classPath, Method method) {
        
        Type type =  TypeFactory.createFromMethodReturn(method);
        
        Type payloadType = JaxrsReflectionUtils.getPayloadParameterType(method);
        
         String httpMethod = JaxrsReflectionUtils.getHttpMethod(method);

         List<Parameter> queryParameters = JaxrsReflectionUtils.getQueryParameters(method);

         List<Parameter> pathParameters = JaxrsReflectionUtils.getPathParameters(method);

         JaxRsTemplate template = JaxRsTemplate.parse(JaxrsReflectionUtils.concatPaths(classPath, JaxrsReflectionUtils.getMethodPath(method)));
         
         return new JaxrsResource(type, payloadType, httpMethod, queryParameters, pathParameters, template);

    }

}
