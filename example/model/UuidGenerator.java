/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import java.util.UUID;

/**
 *
 * @author Rithik
 */
public class UuidGenerator {
    /** 
     * @param prefix The string value that will be displayed at the beginning of each ID 
     * @return A unique ID With the given prefix in the beginning of the ID 
     */
     public static String generateUuidWithPrefix(String prefix) {
        UUID uuid = UUID.randomUUID();
        return prefix + "-" + uuid.toString(); //Concatenating the prefix to the unique UUID 
    }
}
