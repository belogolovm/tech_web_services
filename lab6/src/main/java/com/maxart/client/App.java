package com.maxart.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import org.postgresql.util.Base64;

import javax.ws.rs.core.MediaType;
import java.util.*;

public class App {
    private static final String URL = "http://localhost:8080/pictures";

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {
        String authToken = "Basic " + Base64.encodeBytes((USERNAME + ":" + PASSWORD).getBytes());

        Client client = Client.create();
        System.out.println("Simple hard code client for service");
        status(client);

        System.out.println("Query with auth: /pictures, \nMethod: POST, \nData: name=Богатыри, author=Виктор Михайлович Васнецов");
        String json = "{\"name\":\"Богатыри\", \"author\":\"Виктор Михайлович Васнецов\"}";
        System.out.println("Result: " + sendRequest(client, URL, authToken, "POST", json));
        System.out.println();
        status(client);

        System.out.println("Query without auth: /pictures, \nMethod: POST, \nData: name=Богатыри, author=Виктор Михайлович Васнецов, " +
                "year=1881, material=Маслянные краски, height=295.3, width=446");
        json = "{\"name\":\"Богатыри\"," +
                "\"author\":\"Виктор Михайлович Васнецов\"," +
                "\"year\":1881, " +
                "\"material\":\"Маслянные краски\"," +
                "\"height\":295.3, " +
                "\"width\":446}";
        System.out.println("Result: " + sendRequest(client, URL, "", "POST", json));
        System.out.println();
        status(client);

        System.out.println("Query with auth: /pictures, \nMethod: POST, \nData: name=Богатыри, author=Виктор Михайлович Васнецов, " +
                "year=1881, material=Маслянные краски, height=295.3, width=446");
        System.out.println("Result: " + sendRequest(client, URL, authToken, "POST", json));
        System.out.println();
        status(client);

        System.out.println("Query with auth: /pictures/10, \nMethod: PUT, \nData: none");
        System.out.println("Result: " + sendRequest(client, URL + "/10", authToken, "PUT", "{}"));
        System.out.println();
        status(client);

        System.out.println("Query with auth: /pictures/111, \nMethod: PUT, \nData: name=My own picture, author=ITMO, year=2018");
        json = "{\"name\":\"My own picture\"," +
                "\"author\":\"ITMO\"," +
                "\"year\":2018}";
        System.out.println("Result: " + sendRequest(client, URL + "/111", authToken, "PUT", json));
        System.out.println();
        status(client);

        System.out.println("Query without auth: /pictures/10, \nMethod: PUT, \nData: name=My own picture, author=ITMO, year=2018");
        System.out.println("Result: " + sendRequest(client, URL + "/10", "", "PUT", json));
        System.out.println();
        status(client);

        System.out.println("Query with auth: /pictures/10, \nMethod: PUT, \nData: name=My own picture, author=ITMO, year=2018");
        System.out.println("Result: " + sendRequest(client, URL + "/10", authToken, "PUT", json));
        System.out.println();
        status(client);

        System.out.println("Query with auth: /pictures/111, \nMethod: DELETE");
        System.out.println("Result: " + sendRequest(client, URL + "/111", authToken, "DELETE", ""));
        System.out.println();
        status(client);

        System.out.println("Query without auth: /pictures/10, \nMethod: DELETE");
        System.out.println("Result: " + sendRequest(client, URL + "/10", "", "DELETE", ""));
        System.out.println();
        status(client);

        System.out.println("Query with auth: /pictures/10, \nMethod: DELETE");
        System.out.println("Result: " + sendRequest(client, URL + "/10", authToken, "DELETE", ""));
        System.out.println();
        status(client);
    }

    private static void status(Client client) {
        System.out.println("Query: /pictures, \nMethod: GET");
        display(findPictures(client, URL, ""));
        System.out.println();
    }

    private static String sendRequest(Client client, String url, String auth, String method, String json) {
        WebResource webResource = client.resource(url);

        ClientResponse response = null;
        if (method.equals("POST"))
            response = webResource.type(MediaType.APPLICATION_JSON).header("Authorization", auth).post(ClientResponse.class, json);
        if (method.equals("PUT"))
            response = webResource.type(MediaType.APPLICATION_JSON).header("Authorization", auth).put(ClientResponse.class, json);
        if (method.equals("DELETE"))
            response = webResource.type(MediaType.APPLICATION_JSON).header("Authorization", auth).delete(ClientResponse.class);

        if (response != null) {
            if ((response.getStatus() != ClientResponse.Status.OK.getStatusCode()) &&
                    response.getStatus() != ClientResponse.Status.BAD_REQUEST.getStatusCode()) {
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
