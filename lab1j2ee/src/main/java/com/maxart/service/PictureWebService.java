package com.maxart.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.sql.DataSource;

@WebService(serviceName = "PictureService")
public class PictureWebService {
    @Resource(lookup = "jdbc/studs")
    private DataSource dataSource;

    @WebMethod(operationName = "getAllPictures")
    public List<Picture> getAllPictures() {
        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        return dao.getAllPictures();
    }

    @WebMethod(operationName = "findPictures")
    public List<Picture> findPictures(@WebParam(name = "q") MyRequest request) {
        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        return dao.findPictures(request);
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PictureWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}