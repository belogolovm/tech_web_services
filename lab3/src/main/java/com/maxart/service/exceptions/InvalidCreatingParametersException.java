package com.maxart.service.exceptions;

import com.maxart.service.PictureServiceFault;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.maxart.service.PictureServiceFault")
public class InvalidCreatingParametersException extends Exception {
    private static final long serialVersionUID = -6647544772732631047L;
    private final PictureServiceFault fault;

    public InvalidCreatingParametersException(String message, PictureServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public InvalidCreatingParametersException(String message, PictureServiceFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public PictureServiceFault getFaultInfo() {
        return fault;
    }
}

