package com.maxart.service;

import javax.xml.ws.Endpoint;

public class App {
    public static void main(String[] args) {
        //disable stacktraces in soap-message
        System.setProperty("com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStackTrace", "false");
        String url = "http://0.0.0.0:8080/PictureService";
        Endpoint.publish(url, new PictureWebService());
    }
}
