package com.maxart.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/PictureService?wsdl");
        PictureService pictureService = new PictureService(url);
        PictureWebService pictureWebService = pictureService.getPictureWebServicePort();

        System.out.println("Simple hard code client for service");

        System.out.println("Query: getAllPictures");
        List<Picture> pictures = pictureWebService.getAllPictures();
        for (Picture picture : pictures) {
            System.out.println(picture.toString());
        }

        System.out.println("Total pictures: " + pictures.size());
        System.out.println();

        System.out.println("Query: findPictures?name=Леонардо да Винчи");
        MyRequest myRequest = new MyRequest();
        myRequest.setAuthor("Леонардо да Винчи");
        pictures = pictureWebService.findPictures(myRequest);
        for (Picture picture : pictures) {
            System.out.println(picture.toString());
        }

        System.out.println("Total pictures: " + pictures.size());
        System.out.println();

        System.out.println("Query: findPictures?name=Леонардо да Винчи&year=1495");
        myRequest.setYear(1495);
        pictures = pictureWebService.findPictures(myRequest);
        for (Picture picture : pictures) {
            System.out.println(picture.toString());
        }

        System.out.println("Total pictures: " + pictures.size());
        System.out.println();

        System.out.println("Query: findPictures?id=7");
        myRequest.setId(7);
        myRequest.setYear(0);
        myRequest.setAuthor("");
        pictures = pictureWebService.findPictures(myRequest);
        for (Picture picture : pictures) {
            System.out.println(picture.toString());
        }

        System.out.println("Total pictures: " + pictures.size());
        System.out.println();
    }
}