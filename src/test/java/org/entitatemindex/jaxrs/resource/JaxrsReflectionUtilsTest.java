package org.entitatemindex.jaxrs.resource;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.entitatemindex.factory.Factory;
import org.entitatemindex.type.CollectionType;
import org.entitatemindex.type.CollectionTypeFactory;
import org.entitatemindex.type.Type;
import org.entitatemindex.type.TypeFactory;
import org.junit.Before;
import org.junit.Test;



public class JaxrsReflectionUtilsTest {
    
    
    
    @Before
    public void setUp() {
        Factory.addCreator(CollectionType.class, new CollectionTypeFactory());
        Factory.addCreator(Type.class, new TypeFactory());
        Factory.addCreator(Parameters.class,new ParametersFactory());
    }
    

    @Test
    public void getHttpMethodSearchResource() throws Exception {

        assertEquals(HttpMethod.GET, JaxrsReflectionUtils.getHttpMethod(MyServiceClass.class.getDeclaredMethod("searchResource",
                int.class, List.class)));

    }

    @Test
    public void getHttpMethodCreateResource() throws Exception {

        assertEquals(HttpMethod.POST, JaxrsReflectionUtils.getHttpMethod(MyServiceClass.class.getDeclaredMethod("createResource",
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
        assertEquals(Factory.create(Type.class).with("int"), pathParameters.get(0).getType());

    }
    
    @Test
    public void getQueryParametersSearchResource() throws Exception {
        List<Parameter> queryParameters = JaxrsReflectionUtils.getQueryParameters(MyServiceClass.class.getMethod("searchResource", int.class, List.class));
        
        assertEquals(2, queryParameters.size());
        
        assertEquals("param1", queryParameters.get(0).getName());
        assertEquals(Factory.create(Type.class).with("int"), queryParameters.get(0).getType());

        assertEquals("param2", queryParameters.get(1).getName());
        assertEquals(Factory.create(Type.class).with("LIST<java.lang.String>"), queryParameters.get(1).getType());
    }

    
    @Test
    public void getQueryParametersGetResource() throws Exception {
        List<Parameter> queryParameters = JaxrsReflectionUtils.getQueryParameters(MyServiceClass.class.getMethod("getResource", int.class));
        
        assertEquals(0, queryParameters.size());
    }

    @Test
    public void getPayloadParameterTypeCreateResouce() throws Exception {
        Type type = JaxrsReflectionUtils.getPayloadParameterType(MyServiceClass.class.getMethod("createResource", String.class));
        
        assertEquals(CollectionType.NONE, type.getCollectionType());
        assertEquals(String.class , type.getJavaClass());
    }

    @Test
    public void getPayloadParameterTypeGetResorce() throws Exception {
        Type type = JaxrsReflectionUtils.getPayloadParameterType(MyServiceClass.class.getMethod("getResource", int.class));
        
        assertEquals(Factory.create(Type.class).with(void.class), type);
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
