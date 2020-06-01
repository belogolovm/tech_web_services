package com.maxart.service;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/pictures")
@Produces({MediaType.APPLICATION_JSON})
public class PictureResource {

    @GET
    public List<Picture> find(@Context UriInfo info) {
        String id = info.getQueryParameters().getFirst("id");
        String name = info.getQueryParameters().getFirst("name");
        String author = info.getQueryParameters().getFirst("author");
        String year = info.getQueryParameters().getFirst("year");
        String material = info.getQueryParameters().getFirst("material");
        String height = info.getQueryParameters().getFirst("height");
        String width = info.getQueryParameters().getFirst("width");
        return new PostgreSQLDAO().findPictures(id, name, author, year, material, height, width);
    }

    @GET
    @Path("/{id}")
    public List<Picture> getOne(@PathParam("id") int id) {
        return new PostgreSQLDAO().findOne(id);
    }

    @POST @Consumes("application/json")
    public String create(Picture picture) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return "{\"result\":" + dao.createPicture(picture) + "}";
    }

    @PUT @Consumes("application/json")
    @Path("/{id}")
    public String update(@PathParam("id") int id, Picture picture) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return "{\"result\":" + dao.updatePicture(id, picture) + "}";
    }

    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return "{\"result\":" + dao.deletePicture(id) + "}";
    }
}