package com.stevemerollis.whereabouts.data.exception;

/**
 * Exception throw by the application when a there is a network connection exception.
 */
public class GoogleApiException extends Exception {

    public GoogleApiException() {
        super();
    }

    public GoogleApiException(String message) { super(message); }

    public GoogleApiException(final Throwable cause) {
        super(cause);
    }
}