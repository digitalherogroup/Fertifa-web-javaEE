package com.fertifa.app.products.exception;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(Long id) {
        super(String.format("%s not found exception",id));
    }
}
