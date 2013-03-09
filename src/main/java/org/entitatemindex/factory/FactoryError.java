package org.entitatemindex.factory;

public class FactoryError extends RuntimeException {

    public FactoryError() {
    }

    public FactoryError(String message) {
        super(message);
    }

    public FactoryError(Throwable cause) {
        super(cause);
    }

    public FactoryError(String message, Throwable cause) {
        super(message, cause);
    }

}
