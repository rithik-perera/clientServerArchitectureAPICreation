/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.exception;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Rithik
 */
@Provider
public class ResourceBadRequestExceptionMapper implements ExceptionMapper<ResourceBadRequestException> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceBadRequestExceptionMapper.class);

    /**
     * called when  ResourceBadRequestException occurs.
     * logs the exception message and returns 400 response.
     * response has exception message and has plain text.
     * @param exception The Exception that was caught
     * @return Response returns a HTTP response 400 with the message 
     */    
    @Override
    public Response toResponse(ResourceBadRequestException exception) {
        LOGGER.error("ResourceBadRequestException caught: {}", exception.getMessage());
        
        return Response.status(Response.Status.BAD_REQUEST) // Setting the HTTP status to 400 Bad Request
                .entity(exception.getMessage()) // Setting the response body to  message
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}