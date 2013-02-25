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
                .contains("java.lang.String GET /service/name/{id} path-params[id:int] query-params[]"));
        assertTrue(jaxrsResourcesAsString
                .contains("LIST<java.lang.String> GET /service/name path-params[] query-params[partial-name:java.lang.String]"));
        assertTrue(jaxrsResourcesAsString.contains("void POST /service/name path-params[] query-params[]"));
        assertTrue(jaxrsResourcesAsString.contains("void PUT /service/name/{id} path-params[id:int] query-params[]"));

    }

    @Test
    public void getResourcesFromString() {

        assertEquals(
                JaxrsResource.fromString("java.lang.String GET /service/name/{id} path-params[id:int] query-params[]")
                        .toString(), "java.lang.String GET /service/name/{id} path-params[id:int] query-params[]");

        assertEquals(
                JaxrsResource
                        .fromString(
                                "LIST<java.lang.String> GET /service/name path-params[] query-params[partial-name:java.lang.String]")
                        .toString(),
                "LIST<java.lang.String> GET /service/name path-params[] query-params[partial-name:java.lang.String]");

        assertEquals(JaxrsResource.fromString("void POST /service/name path-params[] query-params[]").toString(),
                "void POST /service/name path-params[] query-params[]");

        assertEquals(JaxrsResource.fromString("void PUT /service/name/{id} path-params[id:int] query-params[]")
                .toString(), "void PUT /service/name/{id} path-params[id:int] query-params[]");

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
        public void updateResource(@PathParam("id") int id) {
        }

    }
}
