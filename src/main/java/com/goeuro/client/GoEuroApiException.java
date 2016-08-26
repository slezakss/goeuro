package com.goeuro.client;


public class GoEuroApiException extends RuntimeException {
    public GoEuroApiException() {
    }

    public GoEuroApiException(String message) {
        super(message);
    }

    public GoEuroApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
