package com.product.management.product.exception;

public class CategoryNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L; //for serialization

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
