/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import com.example.dao.DoctorDAO;




/**
 *
 * @author Rithik
 */
public class Appointment {
     private String appointmentId; //A unique identifier for the appointment 
    private Doctor doctor; // The doctor that belongs to the appointment 
    private Patient patient; // The patient that belongs to the appointment 
    private String appointmentDate; //The date of the appointment 
    private String appointmentTime; //The time of the appointment 
    private String doctorId; //The ID of the doctor in the appointment 


    

        public Appointment(String doctorId, Patient patient, String appointmentDate, String appointmentTime) {
        this.appointmentId = UuidGenerator.generateUuidWithPrefix("appointment"); //A unique ID generated with appointment as the prefix
        this.doctorId = doctorId;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.doctor = DoctorDAO.getDoctorByIdUsingClassName(doctorId); //This method will return the doctor if available or else it will return null 
    }
  
    public Appointment(){
        //default constructor when adding data using the resource class
    }
    

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    
    public String getAppointmentId() {
        return appointmentId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public void setAppointmentId(String appointmentid){
        this.appointmentId = appointmentid;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }



    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
}