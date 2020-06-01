
package com.maxart.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.maxart.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllPicturesResponse_QNAME = new QName("http://service.maxart.com/", "getAllPicturesResponse");
    private final static QName _FindPicturesResponse_QNAME = new QName("http://service.maxart.com/", "findPicturesResponse");
    private final static QName _GetAllPictures_QNAME = new QName("http://service.maxart.com/", "getAllPictures");
    private final static QName _FindPictures_QNAME = new QName("http://service.maxart.com/", "findPictures");
    private final static QName _MyRequest_QNAME = new QName("http://service.maxart.com", "MyRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.maxart.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindPictures }
     * 
     */
    public FindPictures createFindPictures() {
        return new FindPictures();
    }

    /**
     * Create an instance of {@link GetAllPicturesResponse }
     * 
     */
    public GetAllPicturesResponse createGetAllPicturesResponse() {
        return new GetAllPicturesResponse();
    }

    /**
     * Create an instance of {@link GetAllPictures }
     * 
     */
    public GetAllPictures createGetAllPictures() {
        return new GetAllPictures();
    }

    /**
     * Create an instance of {@link FindPicturesResponse }
     * 
     */
    public FindPicturesResponse createFindPicturesResponse() {
        return new FindPicturesResponse();
    }

    /**
     * Create an instance of {@link MyRequest }
     * 
     */
    public MyRequest createMyRequest() {
        return new MyRequest();
    }

    /**
     * Create an instance of {@link Picture }
     * 
     */
    public Picture createPicture() {
        return new Picture();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllPicturesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "getAllPicturesResponse")
    public JAXBElement<GetAllPicturesResponse> createGetAllPicturesResponse(GetAllPicturesResponse value) {
        return new JAXBElement<GetAllPicturesResponse>(_GetAllPicturesResponse_QNAME, GetAllPicturesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindPicturesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "findPicturesResponse")
    public JAXBElement<FindPicturesResponse> createFindPicturesResponse(FindPicturesResponse value) {
        return new JAXBElement<FindPicturesResponse>(_FindPicturesResponse_QNAME, FindPicturesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllPictures }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "getAllPictures")
    public JAXBElement<GetAllPictures> createGetAllPictures(GetAllPictures value) {
        return new JAXBElement<GetAllPictures>(_GetAllPictures_QNAME, GetAllPictures.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindPictures }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "findPictures")
    public JAXBElement<FindPictures> createFindPictures(FindPictures value) {
        return new JAXBElement<FindPictures>(_FindPictures_QNAME, FindPictures.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MyRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com", name = "MyRequest")
    public JAXBElement<MyRequest> createMyRequest(MyRequest value) {
        return new JAXBElement<MyRequest>(_MyRequest_QNAME, MyRequest.class, null, value);
    }

}
