package com.test.jaxrstemplate.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.test.jaxrstemplate.Literal;
import com.test.jaxrstemplate.Variable;
import com.test.jaxrstemplate.parser.token.Tokenizer;

public class ParserTest {
    
    Parser parser;
    Source source;
    Tokenizer tokenizer;



    
    @Test
    public void literal() {
        parser = createParser("any thing here {xx");
        
        Literal literal = parser.literal();
        
        assertNotNull(literal);
        assertEquals("any thing here ", literal.getValue());
    }
    
    @Test
    public void literalNull() {
        parser = createParser("{");
        
        Literal literal = parser.literal();
        
        assertNull(literal);
    }
    
    
    @Test
    public void variable() {
        parser = createParser("{var1}");
        
        Variable variable = parser.variable();
        
        assertEquals("var1", variable.getName());
    }
    

    private Parser createParser(String text) {
        source = new Source(text);
        
        tokenizer = new Tokenizer(source);
        
        return new Parser(tokenizer);
    }

}
