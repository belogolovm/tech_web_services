package com.maxart.service.exceptions;

public class InvalidEntityException extends Exception {

    private static final long serialVersionUID = -6647544772732631047L;
    public static InvalidEntityException DEFAULT_INSTANCE = new
            InvalidEntityException("Invalid entity");

    InvalidEntityException(String message) {
        super(message);
    }
}
