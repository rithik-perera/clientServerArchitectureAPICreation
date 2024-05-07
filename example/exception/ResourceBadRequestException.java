/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.exception;

/**
 *
 * @author Rithik
 */
public class ResourceBadRequestException  extends RuntimeException{
    
    /**
     * Constructs ResourceBadRequestException with provided message.
     * @param message The  message 
     */    
     public ResourceBadRequestException(String message) {
        super(message); // Calls the constructor of the superclass (RuntimeException) with  message
    }
}
