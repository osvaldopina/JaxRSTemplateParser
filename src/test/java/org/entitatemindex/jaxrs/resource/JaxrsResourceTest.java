package org.entitatemindex.jaxrs.resource;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JaxrsResourceTest {

	@Test
	public void resourcePartsWith5Parts() {
	    
     JaxrsResource resource = JaxrsResource.createFromString("LIST<java.lang.String> void GET /a/b/c/{d}/e path-params[] query-params[partial-name:java.lang.String]");
	    

	    assertEquals(5, resource.getTemplate().toUri(). getResourceParts().size());
        assertEquals(new JaxrsResourcePart("a"), resource.getResourceParts().get(0));
        assertEquals(new JaxrsResourcePart("b"), resource.getResourceParts().get(1));
        assertEquals(new JaxrsResourcePart("c"), resource.getResourceParts().get(2));
        assertEquals(new JaxrsVariable("d"), resource.getResourceParts().get(3));
        assertEquals(new JaxrsResourcePart("e"), resource.getResourceParts().get(4));
	    
	}

    @Test
    public void resourcePartsWithNoStart() {
        
        JaxrsResource resource = JaxrsResource.createFromString("LIST<java.lang.String> void GET a/b/c/{d}/e path-params[] query-params[partial-name:java.lang.String]");
        

        assertEquals(5, resource.getResourceParts().size());
        assertEquals(new JaxrsResourcePart("a"), resource.getResourceParts().get(0));
        assertEquals(new JaxrsResourcePart("b"), resource.getResourceParts().get(1));
        assertEquals(new JaxrsResourcePart("c"), resource.getResourceParts().get(2));
        assertEquals(new JaxrsVariable("d"), resource.getResourceParts().get(3));
        assertEquals(new JaxrsResourcePart("e"), resource.getResourceParts().get(4));
        
    }
}
