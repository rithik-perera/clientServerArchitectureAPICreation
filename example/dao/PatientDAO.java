/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.model.Patient;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rithik
 */
public class PatientDAO {
     private static final ArrayList<Patient> patientDatabase  = new ArrayList<>(); //The list in which all the patients will be stored in 
    private static final Logger LOGGER = Logger.getLogger(PatientDAO.class.getName());

    //Sample data that will be used for testing purposes with postman 
   static {
    patientDatabase.add(new Patient("John Doe", "1234567890", 30, "123 Main St, Cityville, USA", "Chronic condition", "Stable")) ;
    patientDatabase.add(new Patient("Jane Smith", "0987654321", 25, "456 Elm St, Townsville, USA", "Acute condition", "Improving")) ;
    patientDatabase.add(new Patient("Alice Johnson", "9876543210", 40, "789 Oak St, Villagetown, USA", "Terminal condition", "Critical")) ;
    patientDatabase.add(new Patient("Bob Brown", "5678901234", 35, "321 Pine St, Countryside, USA", "Temporary condition", "Stable")) ;
    patientDatabase.add(new Patient("Emily Davis", "3456789012", 28, "567 Maple St, Mountainville, USA", "Chronic condition", "Improving")) ;
}
        /**
     * @return Returns the array list of all the patients (In the patients database)
     */
    public ArrayList<Patient> getAllPatients(){
        return patientDatabase;
    }

    /**
     * @param patient The patients of type Patient 
     This patient will be added to the patients database
     */
    public void addPatient(Patient patient) {
        patientDatabase.add(patient);
        LOGGER.info("New patient added successfully");
    }
    
        /**
     * @param id The ID of the patient 
     * @return Returns the patient if available in the patients database, else it will return null
     */
    public Patient getPatientById(String id) {
        for (Patient patient : patientDatabase) { // Iterates through all the elements in the patients database using for each loop 
            if (patient.getId().equals(id)) {
                return patient; // patient id matches, It will return the patient as an object 
            }
        }
        LOGGER.log(Level.WARNING, "Could not find patient with the provided ID: {0}", id);
        return null; // Return null if patient with specified ID is not found
    }

        /**
     * takes in the patient and updates it according to the user 
     * @param updatedPatient The patient with the updated details
     */
    public void updatePatient(Patient updatedPatient) {
        for (int i = 0; i < patientDatabase.size(); i++) { //Iterate through all the patients 
            Patient patient = patientDatabase.get(i); //gets the patient with the i value
            if (patient.getId().equals(updatedPatient.getId())) {
                patientDatabase.set(i, updatedPatient); //If patient is found, Updates the patient
                LOGGER.log(Level.INFO, "no existing patient found with the provided patient ID: {0}", updatedPatient.getId());
                return;
            }
        }
        LOGGER.log(Level.WARNING, "was not able to update the patient");
    }

        /**
     * @param id The ID of the patient 
     * Deletes the prescription from the patients database array list 
     */
    public void deletePatient(String id) {
        patientDatabase.removeIf(patient -> {//Remove the patient from the patients database only if there is a match found
            if (patient.getId().equals(id)) {
                LOGGER.log(Level.INFO, "Patient with the provided ID ({0}) was successfully removed", id);
                return true;
            }
            return false;
        });
        LOGGER.log(Level.WARNING, "No existing patient found with the provided ID: {0}", id);//Logs That the patient with the provided ID was not found 
    }
    
}
