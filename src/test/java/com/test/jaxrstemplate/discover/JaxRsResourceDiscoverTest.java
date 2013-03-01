package com.test.jaxrstemplate.discover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.junit.Test;

public class JaxRsResourceDiscoverTest {

    @Test
    public void getResourcesFromServiceClasses() {

        List<JaxrsResource> jaxrsResources = JaxRsResourceDiscover.getResourcesFromServiceClasses(SimpleService.class);

        assertEquals(4, jaxrsResources.size());

        List<String> jaxrsResourcesAsString = new ArrayList<String>();

        for (JaxrsResource jaxrsResource : jaxrsResources) {
            jaxrsResourcesAsString.add(jaxrsResource.toString());
        }

        assertTrue(jaxrsResourcesAsString
                .contains("java.lang.String void GET /service/name/{id} path-params[id:int] query-params[]"));
        assertTrue(jaxrsResourcesAsString
                .contains("LIST<java.lang.String> void GET /service/name path-params[] query-params[partial-name:java.lang.String]"));
        assertTrue(jaxrsResourcesAsString.contains("void java.lang.String POST /service/name path-params[] query-params[]"));
        assertTrue(jaxrsResourcesAsString.contains("void java.lang.String PUT /service/name/{id} path-params[id:int] query-params[]"));

    }

    @Test
    public void getResourcesFromString() {

        String resource1 = "java.lang.String void GET /service/name/{id} path-params[id:int] query-params[]";
        String resource2 =
                "LIST<java.lang.String> void GET /service/name path-params[] query-params[partial-name:java.lang.String]";
        String resource3 = "void java.lang.String POST /service/name path-params[] query-params[]";
        String resource4 = "void java.lang.String PUT /service/name/{id} path-params[id:int] query-params[]";

        assertEquals(JaxrsResource.fromString(resource1).toString(), resource1);
        assertEquals(JaxrsResource.fromString(resource2).toString(), resource2);
        assertEquals(JaxrsResource.fromString(resource3).toString(), resource3);
        assertEquals(JaxrsResource.fromString(resource4).toString(), resource4);
    }

    @Path("/service")
    public static class SimpleService {

        @GET
        @Path("/name/{id}")
        public String getName(@PathParam("id") int id) {
            return null;
        }

        @GET
        @Path("name")
        public List<String> searchResouces(@QueryParam("partial-name") String partialName) {
            return null;
        }

        @POST
        @Path("name")
        public void createResouce(String name) {
        }

        @PUT
        @Path("name/{id}")
        public void updateResource(@PathParam("id") int id, String name) {
        }

    }
}
