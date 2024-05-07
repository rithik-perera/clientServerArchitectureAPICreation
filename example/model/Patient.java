/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import java.util.Date;

/**
 *
 * @author Rithik
 */
public class Patient extends Person {
    private String medicalHistory; //The medical history of the patient 
    private String currentHealthStatus; //The current health of the patient 

    public Patient(String name, String phoneNumber, int age, String address,  String medicalHistory, String currentHealthStatus) {
        super(name, phoneNumber, age, address);
        setRandomId("patient"); //A unique ID generated with patient as the prefix
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
    }
    
        public Patient() {
        // Default constructor
    }


    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }
}
