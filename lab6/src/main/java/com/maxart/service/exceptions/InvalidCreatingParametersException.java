package com.maxart.service.exceptions;

public class InvalidCreatingParametersException extends Exception {

    private static final long serialVersionUID = -6647544772732631047L;
    public static InvalidCreatingParametersException DEFAULT_INSTANCE = new
            InvalidCreatingParametersException("Invalid creating parameter");

    public InvalidCreatingParametersException(String message) {
        super(message);
    }
}
