/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;



import com.example.model.MedicalRecord;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rithik
 */
public class MedicalRecordDAO {
    private static final ArrayList<MedicalRecord> medicalRecordsDatabase = new ArrayList<>(); //The list in which all the medical records will be stored in 
    private static final Logger LOGGER = Logger.getLogger(MedicalRecordDAO.class.getName());
    
    //Sample data that will be used for testing purposes with postman 
    static {
        Patient patient1 = new Patient("John Doe", "1234567890", 30, "123 Main St, Cityville, USA", "Chronic condition", "Stable");
        medicalRecordsDatabase.add(new MedicalRecord(patient1, "Diabetes", "Insulin treatment"));

        Patient patient2 = new Patient("Jane Smith", "0987654321", 25, "456 Elm St, Townsville, USA", "Acute condition", "Improving");
        medicalRecordsDatabase.add(new MedicalRecord(patient2, "Hypertension", "Blood pressure medication"));

        Patient patient3 = new Patient("Alice Johnson", "9876543210", 40, "789 Oak St, Villagetown, USA", "Terminal condition", "Critical");
        medicalRecordsDatabase.add(new MedicalRecord(patient3, "Cancer", "Chemotherapy"));

        Patient patient4 = new Patient("Bob Brown", "5678901234", 35, "321 Pine St, Countryside, USA", "Temporary condition", "Stable");
        medicalRecordsDatabase.add(new MedicalRecord(patient4, "Fracture", "Physical therapy"));

        Patient patient5 = new Patient("Emily Davis", "3456789012", 28, "567 Maple St, Mountainville, USA", "Chronic condition", "Improving");
        medicalRecordsDatabase.add(new MedicalRecord(patient5, "Asthma", "Inhalers"));

    }

     /**
     * @param medicalRecord The medical records of type MedicalRecord 
     This medical record will be added to the medical records database
     */
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordsDatabase.add(medicalRecord);
        LOGGER.log(Level.INFO, "New medical record added successfully");
    }

        /**
     * @return Returns the array list of all the medical records (In the medical records database)
     */
    public ArrayList<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordsDatabase;
    }

        /**
     * @param medicalRecordId The ID of the medical record 
     * @return Returns the medical record if available in the medical records database, else it will return null
     */
    public MedicalRecord getMedicalRecordById(String medicalRecordId) {
        for (MedicalRecord medicalRecord : medicalRecordsDatabase) { // Iterates through all the elements in the medical records database using for each loop 
            if (medicalRecord.getMedicalRecordId().equals(medicalRecordId)) {
                return medicalRecord; // medical record id matches, It will return the medical records as an object 
            }
        }
        LOGGER.log(Level.WARNING, "Could not find medical record with the provided ID: {0}", medicalRecordId);
        return null; //Returns null if the medical record was not found in the medical records database 
    }

        /**
     * takes in the medical record and updates it according to the user 
     * @param updatedMedicalRecord The medical record with the updated details
     */
    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        for (int i = 0; i < medicalRecordsDatabase.size(); i++) { //Iterate through all the medical records 
            MedicalRecord medicalRecord = medicalRecordsDatabase.get(i); //gets the medical records with the i value
            if (medicalRecord.getMedicalRecordId().equals(updatedMedicalRecord.getMedicalRecordId())) {
                medicalRecordsDatabase.set(i, updatedMedicalRecord); //If medical record is found, Updates the medical record
                LOGGER.log(Level.INFO, "Medical record with the provided ID ({0}) has been successfully updated", updatedMedicalRecord.getMedicalRecordId());
                return;
            }
        }
        LOGGER.log(Level.WARNING, "Failed to update medical record .Could not find medical record with the provided ID: {0}", updatedMedicalRecord.getMedicalRecordId());
    }
    /**
     * @param medicalRecordId The ID of the medical record 
     * Deletes the medical record from the medical record database array list 
     */
    public void deleteMedicalRecord(String medicalRecordId) {
        medicalRecordsDatabase.removeIf(medicalRecord -> {//Remove the medical record from the medical records database only if there is a match found
            if (medicalRecord.getMedicalRecordId().equals(medicalRecordId)) {
                LOGGER.log(Level.INFO, "Medical record with the provided ID ({0}) was successfully removed", medicalRecordId);
                return true;
            }
            return false;
        });
        LOGGER.log(Level.WARNING, "No existing medical record found with the provided ID: {0}", medicalRecordId); //Logs That the medical record with the provided ID was not found 
    }
}
