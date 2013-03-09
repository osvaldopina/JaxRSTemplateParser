package org.entitatemindex.uri;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ResourceTest {
    
    UriResource resource;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createResource() {
        resource = new UriResource("resource");
        
        assertEquals("resource", resource.getName());
        assertFalse(resource.isVariable());
        
    }

    @Test
    public void createResourceVariable() {
        resource = new UriResource("{variable}");
        
        assertEquals("{variable}", resource.getName());
        assertTrue(resource.isVariable());
        assertEquals("variable", resource.getVariableName());
       
    }
}
