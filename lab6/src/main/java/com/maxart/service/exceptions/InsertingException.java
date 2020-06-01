package com.maxart.service.exceptions;

public class InsertingException extends Exception {

    private static final long serialVersionUID = -6647544772732631047L;
    public static InsertingException DEFAULT_INSTANCE = new
            InsertingException("Error During creation entity");

    InsertingException(String message) {
        super(message);
    }
}