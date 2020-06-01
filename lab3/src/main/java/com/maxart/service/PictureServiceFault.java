package com.maxart.service;

public class PictureServiceFault {
    private static final String DEFAULT_MESSAGE = "Parameter q cannot be null or empty";
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static PictureServiceFault defaultInstance() {
        PictureServiceFault fault = new PictureServiceFault();
        fault.message = DEFAULT_MESSAGE;
        return fault;
    }
}