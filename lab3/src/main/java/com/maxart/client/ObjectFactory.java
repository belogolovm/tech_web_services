
package com.maxart.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.maxart.client package. 
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
    private final static QName _IllegalQException_QNAME = new QName("http://service.maxart.com/", "IllegalQException");
    private final static QName _InvalidCreatingParametersException_QNAME = new QName("http://service.maxart.com/", "InvalidCreatingParametersException");
    private final static QName _CreatePictureResponse_QNAME = new QName("http://service.maxart.com/", "createPictureResponse");
    private final static QName _DeletePictureResponse_QNAME = new QName("http://service.maxart.com/", "deletePictureResponse");
    private final static QName _UpdatePictureResponse_QNAME = new QName("http://service.maxart.com/", "updatePictureResponse");
    private final static QName _FindPictures_QNAME = new QName("http://service.maxart.com/", "findPictures");
    private final static QName _CreatePicture_QNAME = new QName("http://service.maxart.com/", "createPicture");
    private final static QName _UpdatePicture_QNAME = new QName("http://service.maxart.com/", "updatePicture");
    private final static QName _InvalidEntityException_QNAME = new QName("http://service.maxart.com/", "InvalidEntityException");
    private final static QName _GetAllPictures_QNAME = new QName("http://service.maxart.com/", "getAllPictures");
    private final static QName _AuthException_QNAME = new QName("http://service.maxart.com/", "AuthException");
    private final static QName _DeletePicture_QNAME = new QName("http://service.maxart.com/", "deletePicture");
    private final static QName _IllegalIdException_QNAME = new QName("http://service.maxart.com/", "IllegalIdException");
    private final static QName _InsertingException_QNAME = new QName("http://service.maxart.com/", "InsertingException");
    private final static QName _MyRequest_QNAME = new QName("http://service.maxart.com", "MyRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.maxart.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PictureServiceFault }
     * 
     */
    public PictureServiceFault createPictureServiceFault() {
        return new PictureServiceFault();
    }

    /**
     * Create an instance of {@link DeletePicture }
     * 
     */
    public DeletePicture createDeletePicture() {
        return new DeletePicture();
    }

    /**
     * Create an instance of {@link GetAllPictures }
     * 
     */
    public GetAllPictures createGetAllPictures() {
        return new GetAllPictures();
    }

    /**
     * Create an instance of {@link UpdatePicture }
     * 
     */
    public UpdatePicture createUpdatePicture() {
        return new UpdatePicture();
    }

    /**
     * Create an instance of {@link CreatePicture }
     * 
     */
    public CreatePicture createCreatePicture() {
        return new CreatePicture();
    }

    /**
     * Create an instance of {@link CreatePictureResponse }
     * 
     */
    public CreatePictureResponse createCreatePictureResponse() {
        return new CreatePictureResponse();
    }

    /**
     * Create an instance of {@link DeletePictureResponse }
     * 
     */
    public DeletePictureResponse createDeletePictureResponse() {
        return new DeletePictureResponse();
    }

    /**
     * Create an instance of {@link UpdatePictureResponse }
     * 
     */
    public UpdatePictureResponse createUpdatePictureResponse() {
        return new UpdatePictureResponse();
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
     * Create an instance of {@link FindPicturesResponse }
     * 
     */
    public FindPicturesResponse createFindPicturesResponse() {
        return new FindPicturesResponse();
    }

    /**
     * Create an instance of {@link Picture }
     * 
     */
    public Picture createPicture() {
        return new Picture();
    }

    /**
     * Create an instance of {@link MyRequest }
     * 
     */
    public MyRequest createMyRequest() {
        return new MyRequest();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link PictureServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "IllegalQException")
    public JAXBElement<PictureServiceFault> createIllegalQException(PictureServiceFault value) {
        return new JAXBElement<PictureServiceFault>(_IllegalQException_QNAME, PictureServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PictureServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "InvalidCreatingParametersException")
    public JAXBElement<PictureServiceFault> createInvalidCreatingParametersException(PictureServiceFault value) {
        return new JAXBElement<PictureServiceFault>(_InvalidCreatingParametersException_QNAME, PictureServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePictureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "createPictureResponse")
    public JAXBElement<CreatePictureResponse> createCreatePictureResponse(CreatePictureResponse value) {
        return new JAXBElement<CreatePictureResponse>(_CreatePictureResponse_QNAME, CreatePictureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePictureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "deletePictureResponse")
    public JAXBElement<DeletePictureResponse> createDeletePictureResponse(DeletePictureResponse value) {
        return new JAXBElement<DeletePictureResponse>(_DeletePictureResponse_QNAME, DeletePictureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePictureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "updatePictureResponse")
    public JAXBElement<UpdatePictureResponse> createUpdatePictureResponse(UpdatePictureResponse value) {
        return new JAXBElement<UpdatePictureResponse>(_UpdatePictureResponse_QNAME, UpdatePictureResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePicture }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "createPicture")
    public JAXBElement<CreatePicture> createCreatePicture(CreatePicture value) {
        return new JAXBElement<CreatePicture>(_CreatePicture_QNAME, CreatePicture.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePicture }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "updatePicture")
    public JAXBElement<UpdatePicture> createUpdatePicture(UpdatePicture value) {
        return new JAXBElement<UpdatePicture>(_UpdatePicture_QNAME, UpdatePicture.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PictureServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "InvalidEntityException")
    public JAXBElement<PictureServiceFault> createInvalidEntityException(PictureServiceFault value) {
        return new JAXBElement<PictureServiceFault>(_InvalidEntityException_QNAME, PictureServiceFault.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link PictureServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "AuthException")
    public JAXBElement<PictureServiceFault> createAuthException(PictureServiceFault value) {
        return new JAXBElement<PictureServiceFault>(_AuthException_QNAME, PictureServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePicture }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "deletePicture")
    public JAXBElement<DeletePicture> createDeletePicture(DeletePicture value) {
        return new JAXBElement<DeletePicture>(_DeletePicture_QNAME, DeletePicture.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PictureServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "IllegalIdException")
    public JAXBElement<PictureServiceFault> createIllegalIdException(PictureServiceFault value) {
        return new JAXBElement<PictureServiceFault>(_IllegalIdException_QNAME, PictureServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PictureServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.maxart.com/", name = "InsertingException")
    public JAXBElement<PictureServiceFault> createInsertingException(PictureServiceFault value) {
        return new JAXBElement<PictureServiceFault>(_InsertingException_QNAME, PictureServiceFault.class, null, value);
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
