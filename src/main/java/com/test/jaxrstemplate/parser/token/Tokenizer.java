package com.test.jaxrstemplate.parser.token;

import com.test.jaxrstemplate.parser.Source;

public class Tokenizer {

    private Source source;

    public Tokenizer(Source source) {
        this.source = source;
    }

    public boolean consume(char character) {
        return source.consume(character);
    }

    public String consumeLiteral() {
        int startPos = source.getPos();
        while ((!source.endReached()) && source.current() != '{' ) {
            source.consume();
        }
        return getCosumedText(startPos,source.getPos());
    }

    public String consumeVariableName() {
        int startPos = source.getPos();
        while((!source.endReached()) && (! source.current().equals('}'))) {
            source.consume();
        }
        return getCosumedText(startPos,source.getPos());
    }

    private String getCosumedText(int start,int end) {
        if (start == end) {
            return null;
        } else {
            return source.getSubString(start,end);
        }
    }

}
