package com.maxart;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.TransportException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String args[]) {
        String wsdl = "http://localhost:8081/PictureService?wsdl";
        String businessKey = "uddi:localhost:0174084d-cbf1-44f2-af31-1b3c3ca7087d";
        String serviceName = "PictureWebService";
        String serviceDescription = "Simple service for getting information about pictures";
        String serviceKey = "";

        UDDIClient client = null;
        try {
            System.out.println("Create UDDI client using uddi.xml");
            client = new UDDIClient("META-INF/uddi.xml");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        if (client != null) {
            System.out.println("Get UDDI clerk using clerkName");
            UDDIClerk clerk = client.getClerk("maxart");
            try {

                Juddi juddi = new Juddi(client, clerk, businessKey);
                System.out.println("Publish service " + serviceName + " with access point " + wsdl);
                serviceKey = juddi.publish(serviceName, serviceDescription, wsdl);
                System.out.println("Publishing service key: " + serviceKey);
                System.out.println();

                System.out.println("Find service by service key = " + serviceKey);
                String accessPoint = juddi.findServiceByKey(serviceKey);
                System.out.println("Access point for results found: " + accessPoint);
                System.out.println();

                System.out.println("Find service by service name = " + serviceName);
                List<ServiceInformation> si = juddi.findServiceByName(serviceName);
                System.out.println("Results found");
                displayServices(si);
                System.out.println();

                System.out.println("Call service by wsdl = " + accessPoint);
                juddi.callService(new URL(accessPoint), "wsdl.xml");
                System.out.println("Results saved into wsdl.xml");
                System.out.println();

                juddi.deleteService(serviceKey);

            } catch (ConfigurationException | TransportException | ParserConfigurationException | SAXException | TransformerException | IOException e) {
                e.printStackTrace();
            }

            clerk.discardAuthToken();
        }
    }

    private static void displayServices(List<ServiceInformation> si) {
        for (ServiceInformation item : si) {
            System.out.println("Service Id: " + item.getId());
            System.out.println("Service Name: " + item.getName());
            System.out.println("Service Description: " + item.getDescription());
            System.out.println("Service Access point: " + item.getAccessPoint());
            System.out.println();
        }
    }
}