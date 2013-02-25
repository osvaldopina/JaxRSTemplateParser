package com.test.jaxrstemplate.discover;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.test.type.Type;
import com.test.type.TypeFactory;

public class JaxrsReflectionUtils {


     public static String getHttpMethod(Method method) {

        if (method.getAnnotation(GET.class) != null) {
            return "GET";
        } else if (method.getAnnotation(POST.class) != null) {
            return "POST";
        } else if (method.getAnnotation(PUT.class) != null) {
            return "PUT";
        } else if (method.getAnnotation(DELETE.class) != null) {
            return "DELETE";
        } else {
            return null;
        }
    }

    public static String getClassPath(Class<?> clazz) {
        Path path = clazz.getAnnotation(Path.class);
        if (path != null) {
            return path.value();
        } else {
            return null;
        }
    }

    public static String getMethodPath(Method method) {
        Path path = method.getAnnotation(Path.class);
        if (path != null) {
            return path.value();
        } else {
            return null;
        }
    }

    public static List<Parameter> getQueryParameters(Method method) {

        Annotation[][] paramAnnotations = method.getParameterAnnotations();

        List<Parameter> queryParameters = new ArrayList<Parameter>();
        
        for (int i = 0; i < paramAnnotations.length; i++) {
            for (int j = 0; j < paramAnnotations[i].length; j++) {
                if (paramAnnotations[i][j] instanceof QueryParam) {
                    String name = ((QueryParam) paramAnnotations[i][j]).value();
                    Type type = TypeFactory.createFromMethodParameter(method, i);
                    queryParameters.add(new Parameter(name, type));
                }
            }
        }

        return queryParameters;
    }
    
    public static List<Parameter> getPathParameters(Method method) {

        Annotation[][] paramAnnotations = method.getParameterAnnotations();

        List<Parameter> queryParameters = new ArrayList<Parameter>();
        
        for (int i = 0; i < paramAnnotations.length; i++) {
            for (int j = 0; j < paramAnnotations[i].length; j++) {
                if (paramAnnotations[i][j] instanceof PathParam) {
                    String name = ((PathParam) paramAnnotations[i][j]).value();
                    Type type = TypeFactory.createFromMethodParameter(method, i);
                    queryParameters.add(new Parameter(name, type));
                }
            }
        }

        return queryParameters;
    }


    public static String concatPaths(String path1, String path2) {
        if (path1.endsWith("/") && (!path2.startsWith("/"))) {
            return path1 + path2;
        } else if (!path1.endsWith("/") && (path2.startsWith("/"))) {
            return path1 + path2;
        } else if (path1.endsWith("/") && (path2.startsWith("/"))) {
        	return path1 + path2.substring(1);
        } else {
            return path1 + "/" + path2;
        }
        
        
    }


    
    


}
