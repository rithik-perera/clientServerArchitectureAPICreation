/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;


/**
 *
 * @author Rithik
 */
public class Doctor extends Person {
     private String specialization; //The field in which the doctor is specialized in 
     
     public Doctor(){
         //default constructor
     }

    public Doctor(String name, String phoneNumber, int age, String address, String specialization) {
        //A prefix (Dr. ) is added to the doctor 
        super("Dr. " + name, phoneNumber, age, address); 
        setRandomId("doctor"); //A unique ID generated with doctor as the prefix
        this.specialization = specialization;
    }
    
        public Doctor(String id, String name, String phoneNumber, int age, String address, String specialization) {
        super(id, "Dr. " + name, phoneNumber, age, address);
        this.specialization = specialization;
    }
    
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    
}
