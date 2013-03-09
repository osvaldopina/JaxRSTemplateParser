package org.entitatemindex.uri;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class GenericUriFactoryTest {
    
    private GenericUriFactory genericUriFactory;
    
    @Before
    public void setUp() {
        genericUriFactory = new GenericUriFactory();
    }

    @Test
    public void createString() {
        GenericUri genericUri = genericUriFactory.create("/a/b");
        
        assertEquals(2,genericUri.size());
        assertEquals("/a/b",genericUri.toString());
    }

    @Test
    public void createListOfResource() {
        
        
        GenericUri genericUri = genericUriFactory.create(Arrays.asList(new UriResource("a"), new UriResource("b")));
        
        assertEquals(2,genericUri.size());
        assertEquals("a",genericUri.get(0).getName());
        assertEquals("b",genericUri.get(1).getName());
    }

}
