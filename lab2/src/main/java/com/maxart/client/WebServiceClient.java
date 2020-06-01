package com.maxart.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {

    public static void getStatus(PictureWebService pictureWebService) {
        System.out.println("Pictures Status");
        List<Picture> pictures = pictureWebService.getAllPictures();
        for (Picture picture : pictures) {
            System.out.println(picture.toString());
        }

        System.out.println("Total pictures: " + pictures.size());
        System.out.println();
    }

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/PictureService?wsdl");
        PictureService pictureService = new PictureService(url);
        PictureWebService pictureWebService = pictureService.getPictureWebServicePort();

        System.out.println("Simple hard code client for service");
        getStatus(pictureWebService);

        System.out.println("Query: createPicture?name=Богатыри&author=Виктор Михайлович Васнецов&" +
                "year=1881&material=Маслянные краски&height=295.3&width=446");
        int creatingPictureId = pictureWebService.createPicture(
                "Богатыри",
                "Виктор Михайлович Васнецов",
                1881,
                "Маслянные краски",
                295.3f,
                446);

        System.out.println("Inserting id: " + creatingPictureId);
        System.out.println();

        getStatus(pictureWebService);

        System.out.println("Query: createPicture?name=picture&author=author&" +
                "year=2018&material=Акварель&height=30&width=40");
        creatingPictureId = pictureWebService.createPicture(
                "picture",
                "author",
                2018,
                "Акварель",
                30,
                40);

        System.out.println("Inserting id: " + creatingPictureId);
        System.out.println();

        getStatus(pictureWebService);

        System.out.println("Query: updatePicture?id=11&name=My own picture&author=ITMO&year=2018");
        MyRequest myRequest = new MyRequest();
        myRequest.setName("My own picture");
        myRequest.setAuthor("ITMO");
        myRequest.setYear(2018);
        creatingPictureId = pictureWebService.updatePicture(11, myRequest);

        System.out.println("Updating status: " + creatingPictureId);
        System.out.println();

        getStatus(pictureWebService);

        System.out.println("Query: updatePicture?id=22&name=My own picture&author=ITMO&year=2018");
        creatingPictureId = pictureWebService.updatePicture(22, myRequest);

        System.out.println("Updating status: " + creatingPictureId);
        System.out.println();

        getStatus(pictureWebService);

        System.out.println("Query: deletePicture?id=11");
        creatingPictureId = pictureWebService.deletePicture(11);

        System.out.println("Delete status: " + creatingPictureId);
        System.out.println();

        getStatus(pictureWebService);
    }
}