package io.github.guiboava.tibiajesterapi.exception;

public class OperationNotPermittedException extends RuntimeException {
    public OperationNotPermittedException(String message) {
        super(message);
    }
}
