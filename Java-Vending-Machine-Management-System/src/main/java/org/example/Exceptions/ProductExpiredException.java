package org.example.Exceptions;

public class ProductExpiredException extends Exception {
    public ProductExpiredException(String message) {
        super(message);
    }
}