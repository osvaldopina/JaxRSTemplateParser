package com.test.jaxrstemplate.parser.token;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.test.jaxrstemplate.parser.Source;
import com.test.jaxrstemplate.parser.token.Tokenizer;

public class TokenizerTest {
    
    private Tokenizer tokenizer;
    private Source source;


    @Test
    public void consumeLiteral() {
        source = new Source("any thing \\+d without {");
        tokenizer = new Tokenizer(source);
        
        String literalValue  = tokenizer.consumeLiteral();
        
        assertEquals("any thing \\+d without ", literalValue);
        
        assertEquals('{', source.current().charValue());
        assertEquals(22, source.getPos());

    }
    
    @Test
    public void consumeVariableName() {
        source = new Source("variable_Name}");
        tokenizer = new Tokenizer(source);
        
        String variableName = tokenizer.consumeVariableName();
        
        assertEquals("variable_Name", variableName);
        
        assertEquals('}', source.current().charValue());
        assertEquals(13, source.getPos());

    }
    
}
