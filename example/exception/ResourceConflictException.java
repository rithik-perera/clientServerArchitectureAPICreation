/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.exception;

/**
 *
 * @author Rithik
 */
public class ResourceConflictException extends RuntimeException {
    
    /**
     * Constructs a new ResourceConflictException with provided message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */    
    public ResourceConflictException(String message) {
        super(message); // Calls the constructor of the superclass (RuntimeException) with provided message
    }
    
}
