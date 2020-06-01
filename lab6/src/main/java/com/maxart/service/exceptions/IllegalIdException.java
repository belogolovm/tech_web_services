package com.maxart.service.exceptions;

public class IllegalIdException extends Exception {

    private static final long serialVersionUID = -6647544772732631047L;
    public static IllegalIdException DEFAULT_INSTANCE = new
            IllegalIdException("Parameter id cannot be null or empty");

    IllegalIdException(String message) {
        super(message);
    }
}
