package com.product.management.product.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L; // for serialization

    public ProductNotFoundException(String message) {
        super(message); 
    }
}
