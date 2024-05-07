/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.exception;

/**
 *
 * @author Rithik
 */
public class ResourceNotFoundException extends RuntimeException {
 
    /**
     * Constructs  ResourceNotFoundException with the provided message.
     * @param message The message 
     */    
        public ResourceNotFoundException(String message) {
        super(message); // Calls the constructor of the superclass (RuntimeException) with the provided message
    }
    
}
