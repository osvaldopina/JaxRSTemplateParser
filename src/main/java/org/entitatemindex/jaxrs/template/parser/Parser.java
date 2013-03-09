package org.entitatemindex.jaxrs.template.parser;

import java.util.ArrayList;
import java.util.List;

import org.entitatemindex.jaxrs.template.JaxrsTemplate;
import org.entitatemindex.jaxrs.template.Literal;
import org.entitatemindex.jaxrs.template.TemplatePart;
import org.entitatemindex.jaxrs.template.Variable;
import org.entitatemindex.jaxrs.template.parser.token.Tokenizer;


public class Parser {

    private Tokenizer tokenizer;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public JaxrsTemplate parseTemplate() {
        List<TemplatePart> templateParts = new ArrayList<TemplatePart>();

        Literal literal = null;
        Variable variable = null;

        while (true) {
            literal = literal();
            if (literal != null) {
                templateParts.add(literal);
            }

            variable = variable();
            if (variable != null) {
                templateParts.add(variable);
            }

            if (literal == null && variable == null) {
                break;
            }
        }
        return new JaxrsTemplate(templateParts);
    }

    protected Variable variable() {
        Variable variable= null;

        if (tokenizer.consume('{')) {
            String variableName = tokenizer.consumeVariableName();
            if (variableName == null) {
                throw new ParserError("Was expecting a variable name!");
            }
            if (! tokenizer.consume('}')) {
                throw new ParserError("Was expecting a '}' !");
            }
            variable = new Variable(variableName);
        }
        return variable;
    }


    protected Literal literal() {

        Literal literal = null;

        String literalValue = tokenizer.consumeLiteral();

        if (literalValue != null) {
            literal = new Literal(literalValue);
        }
        return literal;
    }

}
