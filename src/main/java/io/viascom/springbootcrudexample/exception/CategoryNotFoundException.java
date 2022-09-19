package io.viascom.springbootcrudexample.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
