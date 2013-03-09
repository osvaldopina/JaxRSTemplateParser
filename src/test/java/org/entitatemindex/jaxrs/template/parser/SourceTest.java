package org.entitatemindex.jaxrs.template.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.entitatemindex.jaxrs.template.parser.ParserError;
import org.entitatemindex.jaxrs.template.parser.Source;
import org.junit.Test;


public class SourceTest {
    
    private Source source;


    @Test
    public void sourceConstructor() {
        source = new Source("source");
        
        assertEquals(0,source.getPos());
        assertEquals('s',source.current().charValue());
    }
    
    @Test
    public void consumeTrue() {
        source = new Source("source");
        
        assertTrue(source.consume('s'));
        assertEquals('o',source.current().charValue());
    }
    
    @Test
    public void consumeFalse() {
        source = new Source("source");
        
        assertFalse(source.consume('0'));
    }
    
    @Test()
    public void advance() {
        source = new Source("source");
        
        assertEquals('s', source.current().charValue());
        source.consume();
        assertEquals('o', source.current().charValue());
    }
    
    @Test(expected=ParserError.class)
    public void advanceAfterEnd() {
        source = new Source("s");
        
        source.consume();
        source.consume();
    }

    
}
