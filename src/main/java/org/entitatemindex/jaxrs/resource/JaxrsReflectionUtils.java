package org.entitatemindex.jaxrs.resource;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.entitatemindex.factory.Factory;
import org.entitatemindex.type.Type;


public class JaxrsReflectionUtils {

    public static HttpMethod getHttpMethod(Method method) {

        if (method.getAnnotation(GET.class) != null) {
            return HttpMethod.GET;
        } else if (method.getAnnotation(POST.class) != null) {
            return HttpMethod.POST;
        } else if (method.getAnnotation(PUT.class) != null) {
            return HttpMethod.PUT;
        } else if (method.getAnnotation(DELETE.class) != null) {
            return HttpMethod.DELETE;
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

    public static Parameters getQueryParameters(Method method) {

        Annotation[][] paramAnnotations = method.getParameterAnnotations();

        List<Parameter> queryParameters = new ArrayList<Parameter>();

        for (int i = 0; i < paramAnnotations.length; i++) {
            for (int j = 0; j < paramAnnotations[i].length; j++) {
                if (paramAnnotations[i][j] instanceof QueryParam) {
                    String name = ((QueryParam) paramAnnotations[i][j]).value();
                    Type type = Factory.create(Type.class).with(method, i);
                    queryParameters.add(new Parameter(name, type));
                }
            }
        }

        return Factory.create(Parameters.class).with(queryParameters);
    }

    public static Parameters getPathParameters(Method method) {

        Annotation[][] paramAnnotations = method.getParameterAnnotations();

        List<Parameter> pathParameters = new ArrayList<Parameter>();

        for (int i = 0; i < paramAnnotations.length; i++) {
            for (int j = 0; j < paramAnnotations[i].length; j++) {
                if (paramAnnotations[i][j] instanceof PathParam) {
                    String name = ((PathParam) paramAnnotations[i][j]).value();
                    Type type = Factory.create(Type.class).with(method, i);
                    pathParameters.add(new Parameter(name, type));
                }
            }
        }

        return Factory.create(Parameters.class).with(pathParameters);
    }

    public static Type getPayloadParameterType(Method method) {
        Annotation[][] paramAnnotations = method.getParameterAnnotations();

        for (int i = 0; i < paramAnnotations.length; i++) {
            int numJaxrsParamAnnotations = 0;
            for (int j = 0; j < paramAnnotations[i].length; j++) {
                if (hasParameterAnnotation(paramAnnotations[i][j])) {
                    numJaxrsParamAnnotations ++;
                }
            }
            if (numJaxrsParamAnnotations ==0) {
                return Factory.create(Type.class).with(method, i);
            }
        }

        return Factory.create(Type.class).with(void.class);
    }

    private static boolean hasParameterAnnotation(Annotation annotation) {
        return annotation instanceof PathParam || annotation instanceof QueryParam || annotation instanceof MatrixParam
                || annotation instanceof HeaderParam || annotation instanceof FormParam
                || annotation instanceof CookieParam;
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
