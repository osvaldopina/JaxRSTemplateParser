package com.test.jaxrstemplate.discover;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.test.jaxrstemplate.resources.tree.ResourcePart;
import com.test.jaxrstemplate.resources.tree.Variable;

public class JaxrsResourceTest {

	@Test
	public void resourcePartsWith5Parts() {
	    
	    JaxrsResource resource = JaxrsResource.fromString("LIST<java.lang.String> void GET /a/b/c/{d}/e path-params[] query-params[partial-name:java.lang.String]");
	    

	    assertEquals(5, resource.getResourceParts().size());
        assertEquals(new ResourcePart("a"), resource.getResourceParts().get(0));
        assertEquals(new ResourcePart("b"), resource.getResourceParts().get(1));
        assertEquals(new ResourcePart("c"), resource.getResourceParts().get(2));
        assertEquals(new Variable("d"), resource.getResourceParts().get(3));
        assertEquals(new ResourcePart("e"), resource.getResourceParts().get(4));
	    
	}

    @Test
    public void resourcePartsWithNoStart() {
        
        JaxrsResource resource = JaxrsResource.fromString("LIST<java.lang.String> void GET a/b/c/{d}/e path-params[] query-params[partial-name:java.lang.String]");
        

        assertEquals(5, resource.getResourceParts().size());
        assertEquals(new ResourcePart("a"), resource.getResourceParts().get(0));
        assertEquals(new ResourcePart("b"), resource.getResourceParts().get(1));
        assertEquals(new ResourcePart("c"), resource.getResourceParts().get(2));
        assertEquals(new Variable("d"), resource.getResourceParts().get(3));
        assertEquals(new ResourcePart("e"), resource.getResourceParts().get(4));
        
    }
}
