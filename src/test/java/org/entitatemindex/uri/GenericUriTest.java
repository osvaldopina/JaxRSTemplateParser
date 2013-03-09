package org.entitatemindex.uri;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class GenericUriTest {
    
    GenericUri genericUri;
    
    @Before
    public void setUp() throws Exception {
        genericUri = new GenericUri(Arrays.asList(new UriResource("a"), new UriResource("b")));
    }

    @Test
    public void getResources() {
        assertEquals(2,genericUri.size());
        assertEquals("a", genericUri.get(0).getName());
        assertEquals("b", genericUri.get(1).getName());
    }

    @Test
    public void testToString() {
        assertEquals("/a/b", genericUri.toString());
    }

}
