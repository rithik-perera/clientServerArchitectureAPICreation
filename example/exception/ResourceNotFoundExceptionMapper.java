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
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
    
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ResourceNotFoundExceptionMapper.class);

    /**
     *  method called when ResourceNotFoundException is caught
     * logs exception message and returns  HTTP 404 as response
     * response contains the message and is of type plain text.
     * @param exception The Exception that was caught
     * @return returns an HTTP response 404 along with message in the body
     */    
    @Override
    public Response toResponse(ResourceNotFoundException exception) {
        LOGGER.error("ResourceNotFoundException caught: {}", exception.getMessage());
        

        return Response.status(Response.Status.NOT_FOUND) // Setting  HTTP status code to 404 Not Found
                .entity(exception.getMessage()) // Setting the response to message
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
