package org.entitatemindex.jaxrs.template;

import org.entitatemindex.jaxrs.template.parser.Parser;
import org.entitatemindex.jaxrs.template.parser.Source;
import org.entitatemindex.jaxrs.template.parser.token.Tokenizer;

public class JaxrsTemplateFactory {

    public JaxrsTemplate create(String text) {
        Source source = new Source(text);
        Tokenizer tokenizer = new Tokenizer(source);
        Parser parser = new Parser(tokenizer);
        return parser.parseTemplate();
    }

}
