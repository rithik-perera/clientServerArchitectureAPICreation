/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;


/**
 *
 * @author Rithik
 */
public class MedicalRecord {
   private String medicalRecordId; //A unique identifier for the medical record 
    private Patient patient; //The patient in which the medical record belongs to 
    private String diagnoses;  //The diagnosis that is done 
    private String treatments;  //The treatments that is done 

    public MedicalRecord(Patient patient, String diagnoses, String treatments) {
        this.medicalRecordId = UuidGenerator.generateUuidWithPrefix("medicalRecord"); //A unique ID generated with medicalRecord as the prefix
        this.patient = patient;
        this.diagnoses = diagnoses; 
        this.treatments = treatments; 
    }

    public String getMedicalRecordId() {
        return medicalRecordId;
    }
    
    public void setMedicalRecordId(String medicalRecordId){
        this.medicalRecordId = medicalRecordId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }
}
