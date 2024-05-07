/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;



import com.example.model.Doctor;
import com.example.model.Patient;
import com.example.model.Prescription;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rithik 
 */
public class PrescriptionDAO {
    
    private static final ArrayList<Prescription> prescriptionDatabase  = new ArrayList<>(); //The list in which all the prescriptions will be stored in 
    private static final Logger LOGGER = Logger.getLogger(PrescriptionDAO.class.getName());


    
    //Sample data that will be used for testing purposes with postman 
    static {
        Patient patient1 = new Patient("John Doe", "1234567890", 30, "123 Main St, Cityville, USA", "Chronic condition", "Stable");      
        prescriptionDatabase.add(new Prescription(patient1, "doc111", "Aspirin", "100mg", "Take once daily", "1 month"));

        Patient patient2 = new Patient("Jane Smith", "0987654321", 25, "789 Oak St, Villagetown, USA", "Acute condition", "Improving");
        prescriptionDatabase.add(new Prescription(patient2, "doc222", "Paracetamol", "250mg", "Take as needed for pain", "2 weeks"));

        Patient patient3 = new Patient("Alice Johnson", "345678901", 50, "789 Maple St, Villagetown, USA", "Chronic condition", "Stable");
        prescriptionDatabase.add(new Prescription(patient3, "doc333", "Ibuprofen", "200mg", "Take with food twice daily", "3 weeks"));

        Patient patient4 = new Patient("Robert Green", "678901234", 60, "901 Cedar St, Hilltown, USA", "Terminal condition", "Critical");
        prescriptionDatabase.add(new Prescription(patient4, "doc111", "Morphine", "10mg", "Take as needed for pain", "Until symptoms improve"));

        Patient patient5 = new Patient("David Wilson", "123450987", 70, "234 Elm St, Countryside, USA", "Temporary condition", "Stable");
        prescriptionDatabase.add(new Prescription(patient5, "doc222", "Antibiotic Cream", "Apply topically twice daily", "Apply for 1 week", "1 week"));
        
    }

    /**
     * @param prescription The prescription of type prescription 
     This prescription will be added to the prescription database
     */
    public void addPrescription(Prescription prescription) {
        prescriptionDatabase.add(prescription);
        LOGGER.info("Prescription has been successfully added");
    }
    
    /**
     * @return Returns the array list of all the prescriptions (In the prescription database)
     */
    public ArrayList<Prescription> getAllPrescriptions() {
        return prescriptionDatabase;
    }
      
      

    /**
     * @param prescriptionId The ID of the prescription 
     * @return Returns the prescription if available in the prescription database, else it will return null
     */
    public Prescription getPrescriptionById(String prescriptionId) {
        for (Prescription prescription : prescriptionDatabase) { // Iterates through all the elements in the prescription database using for each loop 
            if (prescription.getPrescriptionId().equals(prescriptionId)) {
                return prescription; // Prescription id matches, It will return the prescription as an object 
            }
        }
        LOGGER.log(Level.WARNING, "Could not find prescription with the provided ID: {0}", prescriptionId); //Logs that the prescription was not found 
        return null; //Returns null if the prescription was not found in the Prescription database 
    }

    /**
     * takes in the prescription and updates it according to the user 
     * @param updatedPrescription The prescription with the updated details
     */
    public void updatePrescription(Prescription updatedPrescription) {
        for (int i = 0; i < prescriptionDatabase.size(); i++) { //Iterate through all the prescriptions 
            Prescription prescription = prescriptionDatabase.get(i); //gets the prescription with the i value
            if (prescription.getPrescriptionId().equals(updatedPrescription.getPrescriptionId())) {
                prescriptionDatabase.set(i, updatedPrescription); //If prescription is found, Updates the prescription 
                LOGGER.log(Level.INFO, "Prescription with ID ({0}) successfully updated", updatedPrescription.getPrescriptionId()); 
                return;
            }
        }
        LOGGER.log(Level.WARNING, "Failed to update prescription");
    }

    /**
     * @param prescriptionId The ID of the prescription 
     * Deletes the prescription from the prescription database array list 
     */
    public void deletePrescription(String prescriptionId) {
        prescriptionDatabase.removeIf(prescription -> { //Remove the prescription from the prescription database only if there is a match phone 
            if (prescription.getPrescriptionId().equals(prescriptionId)) {
                LOGGER.log(Level.INFO, "Prescription with ID ({0}) successfully removed", prescriptionId);
                return true;
            }
            return false;
        });
        LOGGER.log(Level.WARNING, "No existing prescription found with ID: {0}", prescriptionId); //Logs That the prescription with the provided ID was not found 
    }

}
