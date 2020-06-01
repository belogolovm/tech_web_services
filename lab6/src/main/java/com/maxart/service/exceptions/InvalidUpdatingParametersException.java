package com.maxart.service.exceptions;

public class InvalidUpdatingParametersException extends Exception {

    private static final long serialVersionUID = -6647544772732631047L;
    public static InvalidUpdatingParametersException DEFAULT_INSTANCE = new
            InvalidUpdatingParametersException("Invalid updating parameters");

    InvalidUpdatingParametersException(String message) {
        super(message);
    }
}
