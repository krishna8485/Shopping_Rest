package com.adcash.exception;


public class ApplicationServiceException extends Exception {
    public ApplicationServiceException(Exception exception, String message) {
        super(message, exception);
    }
}