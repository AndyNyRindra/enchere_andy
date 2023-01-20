package com.enchere.exception;

public class TokenNotFoundException extends ResourceNotFoundException{
    public TokenNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(resourceName, fieldName, fieldValue);
    }

    public TokenNotFoundException() {
        super("Token not found");
    }
}
