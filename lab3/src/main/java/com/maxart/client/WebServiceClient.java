package com.maxart.client;

import org.postgresql.util.Base64;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebServiceClient {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private static void getStatus(PictureWebService pictureWebService) {
        System.out.println("Pictures Status");
        List<Picture> pictures = pictureWebService.getAllPictures();
        for (Picture picture : pictures) {
            System.out.println(picture.toString());
        }

        System.out.println("Total pictures: " + pictures.size());
        System.out.println();
    }

    public static void main(String[] args) throws MalformedURLException {
        String authToken = "Basic " + Base64.encodeBytes((USERNAME + ":" + PASSWORD).getBytes());

        URL url = new URL("http://localhost:8080/PictureService?wsdl");
        PictureService pictureService = new PictureService(url);
        PictureWebService pictureWebService = pictureService.getPictureWebServicePort();

        Map<String, List<String>> credentials = new HashMap<>();
        credentials.put("Authorization", Collections.singletonList(authToken));
        BindingProvider bindingProvider = (BindingProvider) pictureWebService;
        bindingProvider.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, credentials);
        System.out.println("Simple hard code client for service");
        getStatus(pictureWebService);


        System.out.println("Query with auth: createPicture?name=Богатыри&author=Виктор Михайлович Васнецов");
        int creatingPictureId = 0;
        try {
            creatingPictureId = pictureWebService.createPicture(
                    "Богатыри",
                    "Виктор Михайлович Васнецов",
                    0,
                    "",
                    0,
                    0);
        } catch (InsertingException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidCreatingParametersException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (AuthException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }
        System.out.println("Response: " + creatingPictureId);
        System.out.println();


        credentials.remove("Authorization");
        System.out.println("Query without auth: createPicture?name=Богатыри&author=Виктор Михайлович Васнецов&" +
                "year=1881&material=Маслянные краски&height=295.3&width=446");
        int id = 0;
        try {
            id = pictureWebService.createPicture(
                    "Богатыри",
                    "Виктор Михайлович Васнецов",
                    1881,
                    "Маслянные краски",
                    295.3f,
                    446);
        } catch (InsertingException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidCreatingParametersException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (AuthException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }
        System.out.println("Response: " + id);
        System.out.println();
        getStatus(pictureWebService);


        credentials.put("Authorization", Collections.singletonList(authToken));
        System.out.println("Query with auth: createPicture?name=Богатыри&author=Виктор Михайлович Васнецов&" +
                "year=1881&material=Маслянные краски&height=295.3&width=446");
        id = 0;
        try {
            id = pictureWebService.createPicture(
                    "Богатыри",
                    "Виктор Михайлович Васнецов",
                    1881,
                    "Маслянные краски",
                    295.3f,
                    446);
        } catch (InsertingException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidCreatingParametersException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (AuthException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }
        System.out.println("Response: " + id);
        System.out.println();
        getStatus(pictureWebService);


        System.out.println("Query with auth: updatePicture?id=10");
        MyRequest myRequest = new MyRequest();
        try {
            creatingPictureId = pictureWebService.updatePicture(id, myRequest);
        } catch (IllegalIdException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (IllegalQException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidEntityException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (AuthException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }
        System.out.println("Response: " + creatingPictureId);
        System.out.println();


        System.out.println("Query with auth: updatePicture?id=111&name=My own picture&author=ITMO&year=2018");
        myRequest.init();
        myRequest.setName("My own picture");
        myRequest.setAuthor("ITMO");
        myRequest.setYear(2018);
        try {
            creatingPictureId = pictureWebService.updatePicture(111, myRequest);
        } catch (IllegalIdException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (IllegalQException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidEntityException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (AuthException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }
        System.out.println("Response: " + creatingPictureId);
        System.out.println();


        credentials.remove("Authorization");
        System.out.println("Query without auth: updatePicture?id=10&name=My own picture&author=ITMO&year=2018");
        try {
            creatingPictureId = pictureWebService.updatePicture(10, myRequest);
        } catch (IllegalIdException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (IllegalQException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidEntityException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (AuthException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }
        System.out.println("Response: " + creatingPictureId);
        System.out.println();
        getStatus(pictureWebService);


        credentials.put("Authorization", Collections.singletonList(authToken));
        System.out.println("Query with auth: updatePicture?id=10&name=My own picture&author=ITMO&year=2018");
        try {
            creatingPictureId = pictureWebService.updatePicture(10, myRequest);
        } catch (IllegalIdException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (IllegalQException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidEntityException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (AuthException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }
        System.out.println("Response: " + creatingPictureId);
        System.out.println();
        getStatus(pictureWebService);


        System.out.println("Query with auth: deletePicture?id=111");
        try {
            creatingPictureId = pictureWebService.deletePicture(111);
        } catch (IllegalIdException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidEntityException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (AuthException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }
        System.out.println("Response: " + creatingPictureId);
        System.out.println();


        credentials.remove("Authorization");
        System.out.println("Query without auth: deletePicture?id=" + id);
        try {
            creatingPictureId = pictureWebService.deletePicture(id);
        } catch (IllegalIdException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidEntityException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (AuthException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }
        System.out.println("Response: " + creatingPictureId);
        System.out.println();
        getStatus(pictureWebService);


        credentials.put("Authorization", Collections.singletonList(authToken));
        System.out.println("Query with auth: deletePicture?id=" + id);
        try {
            creatingPictureId = pictureWebService.deletePicture(id);
        } catch (IllegalIdException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (InvalidEntityException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        } catch (AuthException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
        }
        System.out.println("Response: " + creatingPictureId);
        System.out.println();
        getStatus(pictureWebService);
    }
}