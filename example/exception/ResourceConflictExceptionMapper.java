/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.exception;

import org.slf4j.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Rithik
 */
@Provider
public class ResourceConflictExceptionMapper implements ExceptionMapper<ResourceConflictException> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceConflictExceptionMapper.class);

    /**
     * method called when ResourceConflictException is caught.
     * logs the exception message and returns HTTP 409 Conflict response.
     * The response has the exception message and of type plain text.
     * @param exception The Exception that was caught
     * @return returns an HTTP response  409 Conflict with message in the body
     */    
    @Override
    public Response toResponse(ResourceConflictException exception) {
        LOGGER.error("ResourceConflictException caught: {}", exception.getMessage());

        return Response.status(Response.Status.CONFLICT)  // Setting HTTP status code to 409 Conflict
                .entity(exception.getMessage())  // Setting the response body to  message
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
