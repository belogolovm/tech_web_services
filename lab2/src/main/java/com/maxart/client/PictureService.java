
package com.maxart.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PictureService", targetNamespace = "http://service.maxart.com/", wsdlLocation = "http://localhost:8080/PictureService?wsdl")
public class PictureService
    extends Service
{

    private final static URL PICTURESERVICE_WSDL_LOCATION;
    private final static WebServiceException PICTURESERVICE_EXCEPTION;
    private final static QName PICTURESERVICE_QNAME = new QName("http://service.maxart.com/", "PictureService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/PictureService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PICTURESERVICE_WSDL_LOCATION = url;
        PICTURESERVICE_EXCEPTION = e;
    }

    public PictureService() {
        super(__getWsdlLocation(), PICTURESERVICE_QNAME);
    }

    public PictureService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PICTURESERVICE_QNAME, features);
    }

    public PictureService(URL wsdlLocation) {
        super(wsdlLocation, PICTURESERVICE_QNAME);
    }

    public PictureService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PICTURESERVICE_QNAME, features);
    }

    public PictureService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PictureService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PictureWebService
     */
    @WebEndpoint(name = "PictureWebServicePort")
    public PictureWebService getPictureWebServicePort() {
        return super.getPort(new QName("http://service.maxart.com/", "PictureWebServicePort"), PictureWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PictureWebService
     */
    @WebEndpoint(name = "PictureWebServicePort")
    public PictureWebService getPictureWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.maxart.com/", "PictureWebServicePort"), PictureWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PICTURESERVICE_EXCEPTION!= null) {
            throw PICTURESERVICE_EXCEPTION;
        }
        return PICTURESERVICE_WSDL_LOCATION;
    }

}
