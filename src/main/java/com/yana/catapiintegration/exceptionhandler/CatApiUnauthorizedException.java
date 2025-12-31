package com.yana.catapiintegration.exceptionhandler;

public class CatApiUnauthorizedException extends RuntimeException {
    public CatApiUnauthorizedException(String message) {
        super(message);
    }
}
