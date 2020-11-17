package com.fertifa.app.products.exception;

public class ObjectNullException extends RuntimeException {
    public ObjectNullException() {
        super("The object is null");
    }
}
