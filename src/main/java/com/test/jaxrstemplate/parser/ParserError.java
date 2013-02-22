package com.test.jaxrstemplate.parser;

public class ParserError extends RuntimeException {

    public ParserError() {
    }

    public ParserError(String message) {
        super(message);
    }

    public ParserError(Throwable cause) {
        super(cause);
    }

    public ParserError(String message, Throwable cause) {
        super(message, cause);
    }

}
