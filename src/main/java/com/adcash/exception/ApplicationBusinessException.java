package com.adcash.exception;


public class ApplicationBusinessException extends Exception {
    public ApplicationBusinessException(String message, Exception exception) {
        super(message, exception);
    }
    public ApplicationBusinessException(String message) {
        super(message);
    }
    

}