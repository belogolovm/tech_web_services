package com.maxart.service;

import com.maxart.service.exceptions.*;
import org.postgresql.util.Base64;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@WebService(serviceName = "PictureService")
public class PictureWebService {

    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String AUTH_HEADER_PREFIX = "Basic ";

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    @Resource
    private
    WebServiceContext webServiceContext;

    @WebMethod(operationName = "getAllPictures")
    public List<Picture> getAllPictures() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getAllPictures();
    }

    @WebMethod(operationName = "findPictures")
    public List<Picture> findPictures(@WebParam(name = "q") MyRequest request) throws IllegalQException {
        if (request == null || request.isNull()) {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            throw new IllegalQException("Parameter q cannot be null or empty", fault);
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.findPictures(request);
    }

    @WebMethod(operationName = "createPicture")
    public int createPicture(@WebParam(name = "name") String name,
                             @WebParam(name = "author") String author,
                             @WebParam(name = "year") int year,
                             @WebParam(name = "material") String material,
                             @WebParam(name = "height") float height,
                             @WebParam(name = "width") float width) throws InsertingException,
            InvalidCreatingParametersException, AuthException {

        PictureServiceFault fault = PictureServiceFault.defaultInstance();

        MessageContext messageContext = webServiceContext.getMessageContext();
        if (!isAuth(messageContext))
        {
            fault.setMessage("Authentication error");
            throw new AuthException("Invalid login-password", fault);
        }

        fault.setMessage("Invalid creating parameter");

        if (name == null || name.trim().isEmpty()) {
            fault.setMessage("Parameter name cannot be null or empty");
            throw new InvalidCreatingParametersException("Invalid creating parameter", fault);
        }

        if (author == null || author.trim().isEmpty()) {
            fault.setMessage("Parameter author cannot be null or empty");
            throw new InvalidCreatingParametersException("Invalid creating parameter", fault);
        }

        if (year <= 0) {
            fault.setMessage("Parameter year cannot be null or empty and less or equal to 0");
            throw new InvalidCreatingParametersException("Invalid creating parameter", fault);
        }

        if (material == null || material.trim().isEmpty()) {
            fault.setMessage("Parameter material cannot be null or empty");
            throw new InvalidCreatingParametersException("Invalid creating parameter", fault);
        }

        if (height <= 0) {
            fault.setMessage("Parameter height cannot be null or empty and less or equal to 0");
            throw new InvalidCreatingParametersException("Invalid creating parameter", fault);
        }

        if (width <= 0) {
            fault.setMessage("Parameter width cannot be null or empty and less or equal to 0");
            throw new InvalidCreatingParametersException("Invalid creating parameter", fault);
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.createPicture(name, author, year, material, height, width);
    }

    @WebMethod(operationName = "updatePicture")
    public int updatePicture(@WebParam(name = "id") int id,
                             @WebParam(name = "q") MyRequest request) throws IllegalQException, IllegalIdException, InvalidEntityException, AuthException {
        MessageContext messageContext = webServiceContext.getMessageContext();
        if (!isAuth(messageContext))
        {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            fault.setMessage("Authentication error");
            throw new AuthException("Invalid login-password", fault);
        }

        if (id == 0) {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            fault.setMessage("Parameter id cannot be null or empty");
            throw new IllegalIdException("Parameter id cannot be null or empty", fault);
        }

        if (request == null || request.isNull()) {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            throw new IllegalQException("Parameter q cannot be null or empty", fault);
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.updatePicture(id, request);
    }

    @WebMethod(operationName = "deletePicture")
    public int deletePicture(@WebParam(name = "id") int id) throws InvalidEntityException, IllegalIdException, AuthException {
        MessageContext messageContext = webServiceContext.getMessageContext();
        if (!isAuth(messageContext))
        {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            fault.setMessage("Authentication error");
            throw new AuthException("Invalid login-password", fault);
        }

        if (id == 0) {
            PictureServiceFault fault = PictureServiceFault.defaultInstance();
            fault.setMessage("Parameter id cannot be null or empty");
            throw new IllegalIdException("Parameter id cannot be null or empty", fault);
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.deletePicture(id);
    }

    private boolean isAuth(MessageContext ctx) {
        Map headers = (Map) ctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        if (!headers.containsKey(AUTH_HEADER_KEY)) {
            return false;
        }

        List<String> authHeader = (List<String>) headers.get(AUTH_HEADER_KEY);
        String authToken = authHeader.get(0);
        if (authToken.isEmpty()) {
            return false;
        }

        authToken = authToken.replaceFirst(AUTH_HEADER_PREFIX, "");
        String decodedString = new String(Base64.decode(authToken));
        StringTokenizer stringTokenizer = new StringTokenizer(decodedString, ":");
        String username = stringTokenizer.nextToken();
        String password = stringTokenizer.nextToken();

        return username.equals(USERNAME) && password.equals(PASSWORD);
    }
}
