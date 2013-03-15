package org.entitatemindex;

public class EntitatemIndexError extends RuntimeException {

    public EntitatemIndexError() {
    }

    public EntitatemIndexError(String message, Throwable cause) {
        super(message, cause);
    }

    public EntitatemIndexError(String message) {
        super(message);
    }

    public EntitatemIndexError(Throwable cause) {
        super(cause);
    }

}
