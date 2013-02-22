package com.test.jaxrstemplate;

import java.util.Collections;
import java.util.List;

import com.test.jaxrstemplate.parser.Parser;
import com.test.jaxrstemplate.parser.Source;
import com.test.jaxrstemplate.parser.token.Tokenizer;

public class JaxRsTemplate implements TemplatePart {
    
    public static JaxRsTemplate parse(String text) {
        Source source = new Source(text);
        Tokenizer tokenizer = new Tokenizer(source);
        Parser parser = new Parser(tokenizer);
        return parser.parseTemplate();
    }
    
    
    private List<TemplatePart> parts;
    
    public JaxRsTemplate(List<TemplatePart> parts) {
        this.parts = parts;
    }
    
    
    public List<TemplatePart> getParts() {
        return Collections.unmodifiableList(parts);
    }


    @Override
    public String toUri() {
        StringBuffer tmp = new StringBuffer();
        for(TemplatePart templatePart:parts) {
            tmp.append(templatePart.toUri());
        }
        return tmp.toString();
    }

}
