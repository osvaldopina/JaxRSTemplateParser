package com.test.jaxrstemplate.parser;

import java.util.ArrayList;
import java.util.List;

import com.test.jaxrstemplate.JaxRsTemplate;
import com.test.jaxrstemplate.Literal;
import com.test.jaxrstemplate.TemplatePart;
import com.test.jaxrstemplate.Variable;
import com.test.jaxrstemplate.parser.token.Tokenizer;

public class Parser {

    private Tokenizer tokenizer;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public JaxRsTemplate parseTemplate() {
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
        return new JaxRsTemplate(templateParts);
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
