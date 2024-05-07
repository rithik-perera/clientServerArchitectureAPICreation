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
public class Prescription {
    private String PrescriptionId; //Unique identifier to identify the prescription 
    private Patient patient; //The patient in which the prescription belongs to 
    private Doctor doctor; //The doctor that has assigned the prescription 
     private String doctorId; // The idea of the doctor that has assigned the prescription 
    private String medicationName; //The name of the medication that is provided in the prescription 
    private String dosage; //The dosage in which the medication should be taken 
    private String instructions; //Instructions as to how the medication should be taken 
    private String duration; //The duration of the prescription 


    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    

    public Prescription(Patient patient, String doctorid, String medicationName, String dosage, String instructions, String duration) {
        this.PrescriptionId = UuidGenerator.generateUuidWithPrefix("prescription"); //A unique ID generated with prescription as the prefix 
        this.patient = patient;
        this.doctorId = doctorid;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.instructions = instructions;
        this.duration = duration;
        this.doctor = DoctorDAO.getDoctorByIdUsingClassName(doctorid);
    }
    
    public Prescription(){
    
    }
    
    public void setPrescriptionId(String PrescriptionId){
        this.PrescriptionId = PrescriptionId;
    }

    public String getPrescriptionId() {
        return PrescriptionId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
