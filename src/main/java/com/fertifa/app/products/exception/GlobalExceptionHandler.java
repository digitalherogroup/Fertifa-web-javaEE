package com.fertifa.app.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> responseIdNullException(IdNotFoundException responseConverterException) {
        return new ResponseEntity<>(responseConverterException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNullException.class)
    public ResponseEntity<String> responseObjectNullException(ObjectNullException objectNullException) {
        return new ResponseEntity<>(objectNullException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageNullException.class)
    public ResponseEntity<String> responseImageNullException(ImageNullException imageNullException) {
        return new ResponseEntity<>(imageNullException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
