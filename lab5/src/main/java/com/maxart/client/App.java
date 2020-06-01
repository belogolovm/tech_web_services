package com.maxart.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {
    private static final String URL = "http://localhost:8080/pictures";

    public static void main(String[] args) {
        Client client = Client.create();
        System.out.println("Simple hard code client for service");
        status(client);

        System.out.println("Query: /pictures?author=Леонардо да Винчи, \nMethod: GET");
        display(findPictures(client, URL, "author=Леонардо да Винчи"));
        System.out.println();

        System.out.println("Query: /pictures?author=Леонардо да Винчи&year=1495, \nMethod: GET");
        display(findPictures(client, URL, "author=Леонардо да Винчи&year=1495"));
        System.out.println();

        System.out.println("Query: /pictures?id=7, \nMethod: GET");
        display(findPictures(client, URL, "id=7"));
        System.out.println();

        System.out.println("Query: /pictures/7, \nMethod: GET");
        display(findPictures(client, URL + "/7", ""));
        System.out.println();

        System.out.println("Query: /pictures, \nMethod: POST, \nData: name=Богатыри, author=Виктор Михайлович Васнецов, " +
                "year=1881, material=Маслянные краски, height=295.3, width=446");
        String json = "{\"name\":\"Богатыри\"," +
                "\"author\":\"Виктор Михайлович Васнецов\"," +
                "\"year\":1881, " +
                "\"material\":\"Маслянные краски\"," +
                "\"height\":295.3, " +
                "\"width\":446}";

        System.out.println("Result: " + sendRequest(client, URL, "POST", json));
        System.out.println();
        status(client);

        System.out.println("Query: /pictures, \nMethod: POST, \nData: name=picture, author=author, " +
                "year=2018, material=Акварель, height=30, width=40");
        json = "{\"name\":\"picture\"," +
                "\"author\":\"author\"," +
                "\"year\":2018, " +
                "\"material\":\"Акварель\"," +
                "\"height\":30, " +
                "\"width\":40}";
        System.out.println("Result: " + sendRequest(client, URL, "POST", json));
        System.out.println();
        status(client);

        System.out.println("Query: /pictures/11, \nMethod: PUT, \nData: name=My own picture, author=ITMO, year=2018");
        json = "{\"name\":\"My own picture\"," +
                "\"author\":\"ITMO\"," +
                "\"year\":2018}";
        System.out.println("Result: " + sendRequest(client, URL + "/11", "PUT", json));
        System.out.println();
        status(client);

        System.out.println("Query: /pictures/22, \nMethod: PUT, \nData: name=My own picture, author=ITMO, year=2018");
        System.out.println("Result: " + sendRequest(client, URL + "/22", "PUT", json));
        System.out.println();
        status(client);

        System.out.println("Query: /pictures/11, \nMethod: DELETE");
        System.out.println("Result: " + sendRequest(client, URL + "/11", "DELETE", ""));
        System.out.println();
        status(client);
    }

    private static void status(Client client) {
        System.out.println("Query: /pictures, \nMethod: GET");
        display(findPictures(client, URL, ""));
        System.out.println();
    }

    private static String sendRequest(Client client, String url, String method, String json) {
        WebResource webResource = client.resource(url);

        ClientResponse response = null;
        if (method.equals("POST"))
            response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
        if (method.equals("PUT"))
            response = webResource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, json);
        if (method.equals("DELETE"))
            response = webResource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);

        if (response != null) {
            if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
                throw new IllegalStateException("Request failed");
            }

            return response.getEntity(String.class);
        }

        return "Please specify method type (POST, PUT, DELETE)";
    }

    private static List<Picture> findPictures(Client client, String url, String query) {
        WebResource webResource = client.resource(url);
        if (!query.isEmpty()) {
            Map<String, String> map = getQueryMap(query);

            Set<String> keys = map.keySet();
            for (String key : keys) {
                webResource = webResource.queryParam(key, map.get(key));
            }
        }

        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }

        GenericType<List<Picture>> type = new GenericType<List<Picture>>() {
        };

        return response.getEntity(type);
    }

    private static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    private static void display(List<Picture> pictures) {
        for (Picture picture : pictures) {
            System.out.println(picture);
        }
    }
}
