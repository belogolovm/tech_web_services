package com.maxart.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Path("/pictures")
@Produces({MediaType.APPLICATION_JSON})
public class PictureResource {

    @Resource(lookup = "jdbc/studs")
    private DataSource dataSource;

    @GET
    public List<Picture> find(@Context UriInfo info) {
        String id = info.getQueryParameters().getFirst("id");
        String name = info.getQueryParameters().getFirst("name");
        String author = info.getQueryParameters().getFirst("author");
        String year = info.getQueryParameters().getFirst("year");
        String material = info.getQueryParameters().getFirst("material");
        String height = info.getQueryParameters().getFirst("height");
        String width = info.getQueryParameters().getFirst("width");
        return new PostgreSQLDAO(getConnection()).findPictures(id, name, author, year, material, height, width);
    }

    @GET
    @Path("/{id}")
    public List<Picture> getOne(@PathParam("id") int id) {
        return new PostgreSQLDAO(getConnection()).findOne(id);
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PictureResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
