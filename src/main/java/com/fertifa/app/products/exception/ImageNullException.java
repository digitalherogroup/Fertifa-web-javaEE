package com.fertifa.app.products.exception;

public class ImageNullException extends RuntimeException {

    public ImageNullException() {
        super("image file is missing, please add image");
    }
}
