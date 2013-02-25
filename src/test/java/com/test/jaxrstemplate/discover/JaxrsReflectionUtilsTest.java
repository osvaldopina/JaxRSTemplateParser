package com.test.jaxrstemplate.discover;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.junit.Test;

import com.test.type.TypeFactory;

public class JaxrsReflectionUtilsTest {

    @Test
    public void getHttpMethodSearchResource() throws Exception {

        assertEquals("GET", JaxrsReflectionUtils.getHttpMethod(MyServiceClass.class.getDeclaredMethod("searchResource",
                int.class, List.class)));

    }

    @Test
    public void getHttpMethodCreateResource() throws Exception {

        assertEquals("POST", JaxrsReflectionUtils.getHttpMethod(MyServiceClass.class.getDeclaredMethod("createResource",
                String.class)));

    }
    
    @Test
    public void getPathMyServiceClass() {
        assertEquals("/", JaxrsReflectionUtils.getClassPath(MyServiceClass.class));
    }


    @Test
    public void getPathSearchResource() throws Exception {
        assertEquals("/resource", JaxrsReflectionUtils.getMethodPath(MyServiceClass.class.getMethod("searchResource", int.class, List.class)));
    }

    @Test
    public void getPathGetResource() throws Exception {
        assertEquals("resource/{id}", JaxrsReflectionUtils.getMethodPath(MyServiceClass.class.getMethod("getResource", int.class)));
    }
    
    
    @Test
    public void getPathParametersSearchResource() throws Exception {
        List<Parameter> pathParameters = JaxrsReflectionUtils.getPathParameters(MyServiceClass.class.getMethod("getResource", int.class));
        
        assertEquals(1, pathParameters.size());
        
        assertEquals("id", pathParameters.get(0).getName());
        assertEquals(TypeFactory.createFromString("int"), pathParameters.get(0).getType());

    }
    
    @Test
    public void getQueryParametersSearchResource() throws Exception {
        List<Parameter> queryParameters = JaxrsReflectionUtils.getQueryParameters(MyServiceClass.class.getMethod("searchResource", int.class, List.class));
        
        assertEquals(2, queryParameters.size());
        
        assertEquals("param1", queryParameters.get(0).getName());
        assertEquals(TypeFactory.createFromString("int"), queryParameters.get(0).getType());

        assertEquals("param2", queryParameters.get(1).getName());
        assertEquals(TypeFactory.createFromString("LIST<java.lang.String>"), queryParameters.get(1).getType());
    }

    
    @Test
    public void getQueryParametersGetResource() throws Exception {
        List<Parameter> queryParameters = JaxrsReflectionUtils.getQueryParameters(MyServiceClass.class.getMethod("getResource", int.class));
        
        assertEquals(0, queryParameters.size());
    }
    
    @Test
    public void concatPathsNoSlash() throws Exception {
        assertEquals("a/b",JaxrsReflectionUtils.concatPaths("a" ,"b"));
    }

    @Test
    public void concatPathsEndWithSlash() throws Exception {
        assertEquals("a/b",JaxrsReflectionUtils.concatPaths("a/" ,"b"));
    }

    @Test
    public void concatPathsStartWithSlash() throws Exception {
        assertEquals("a/b",JaxrsReflectionUtils.concatPaths("a" ,"/b"));
    }
    
    @Test
    public void concatPathsStartAndEndWithSlash() throws Exception {
        assertEquals("a/b",JaxrsReflectionUtils.concatPaths("a/" ,"/b"));
    }

    
    @Path("/")
    public static class MyServiceClass {

        @GET
        @Path("/resource")
        public String searchResource(@QueryParam("param1") int param1, @QueryParam("param2") List<String> param2) {
            return null;
        }

        @GET
        @Path("resource/{id}")
        public String getResource(@PathParam("id") int id) {
            return null;
        }
        
        @POST
        @Path("resource")
        public void createResource(String name) {
            
        }
        
        public ArrayList<String> genericMethod(HashSet<Integer> par1) {
            return null;
        }
        
        

    }
}
