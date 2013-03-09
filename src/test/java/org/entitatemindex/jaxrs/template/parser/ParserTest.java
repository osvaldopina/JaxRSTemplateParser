package org.entitatemindex.jaxrs.template.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.entitatemindex.jaxrs.template.Literal;
import org.entitatemindex.jaxrs.template.Variable;
import org.entitatemindex.jaxrs.template.parser.Parser;
import org.entitatemindex.jaxrs.template.parser.Source;
import org.entitatemindex.jaxrs.template.parser.token.Tokenizer;
import org.junit.Test;


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
