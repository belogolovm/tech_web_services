package com.maxart.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(serviceName = "PictureService")
public class PictureWebService {

    @WebMethod(operationName = "getAllPictures")
    public List<Picture> getAllPictures() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getAllPictures();
    }

    @WebMethod(operationName = "findPictures")
    public List<Picture> findPictures(@WebParam(name = "q") MyRequest request) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.findPictures(request);
    }

    @WebMethod(operationName = "createPicture")
    public int createPicture(@WebParam(name = "name") String name,
                             @WebParam(name = "author") String author,
                             @WebParam(name = "year") int year,
                             @WebParam(name = "material") String material,
                             @WebParam(name = "height") float height,
                             @WebParam(name = "width") float width) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        try {
            return dao.createPicture(name, author, year, material, height, width);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @WebMethod(operationName = "updatePicture")
    public int updatePicture(@WebParam(name = "id") int id,
                                 @WebParam(name = "q") MyRequest request) {
        PostgreSQLDAO dao = new PostgreSQLDAO();

        try {
            return dao.updatePicture(id, request);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @WebMethod(operationName = "deletePicture")
    public int deletePicture(@WebParam(name = "id") int id) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        try {
            return dao.deletePicture(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
