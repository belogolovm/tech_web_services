package com.maxart.service.exceptions;

import com.maxart.service.BasicResponse;
import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthMapper implements ExceptionMapper<AuthException> {

    @Override
    public Response toResponse(AuthException e) {
        return Response.status(ClientResponse.Status.BAD_REQUEST).entity(new BasicResponse(e.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}

