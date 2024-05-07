/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.model.Doctor;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rithik
 */
public class DoctorDAO {
    
   private static final ArrayList<Doctor> doctorDatabase  = new ArrayList<>(); //The list in which all the doctors will be stored in 
    private static final Logger LOGGER = Logger.getLogger(DoctorDAO.class.getName());

    //Sample data that will be used for testing purposes with postman 
    static {
    doctorDatabase.add(new Doctor("Benjamin", "5454864", 43, "123 Oak St, Citytown, USA", "Heart surgeon"));
    doctorDatabase.add(new Doctor("Emily", "9876543", 38, "456 Elm St, Townsville, USA", "Pediatrician"));
    doctorDatabase.add(new Doctor("Michael", "1234567", 50, "789 Pine St, Countryside, USA", "Orthopedic surgeon"));
    doctorDatabase.add(new Doctor("doc111", "Sophia", "4567890", 35, "321 Maple St, Mountainville, USA", "Dermatologist"));
    doctorDatabase.add(new Doctor("doc222","William", "6543210", 48, "567 Cedar St, Villagetown, USA", "Neurologist"));
    doctorDatabase.add(new Doctor("doc333","Olivia", "2345678", 42, "890 Birch St, Riverside, USA", "Oncologist"));
}

     /**
     * @param doctor The doctor of type Doctors 
     This doctor will be added to the doctors database
     */
    public void addDoctor(Doctor doctor) {
        doctorDatabase.add(doctor);
        LOGGER.info("New doctor added successfully");
    }

        /**
     * @param id The ID of the doctor 
     * @return Returns the doctor if available in the doctors database, else it will return null
     */
    public Doctor getDoctorById(String id) {
        for (Doctor doctor : doctorDatabase) {  // Iterates through all the elements in the doctors database using for each loop 
            if (doctor.getId().equals(id)) {
                return doctor; // Return the doctor if ID matches
            }
        }
        LOGGER.log(Level.WARNING, "Could not find doctor with the provided ID: {0}", id);
        return null;
    }
    
        public static Doctor getDoctorByIdUsingClassName(String id) {
        for (Doctor doctor : doctorDatabase) {
            if (doctor.getId().equals(id)) {
                return doctor; // Return the doctor if ID matches
            }
        }
        LOGGER.log(Level.WARNING, "Could not find doctor with the provided ID: {0}", id);
        return null;
    }
    
        /**
     * @param name The name of the doctor 
     * @return Doctor of type doctor that matches with the name 
     */
        public  Doctor findDoctorByName(String name) {
        for (Doctor doctor : doctorDatabase) {
            String doctorNameWithPrefix = doctor.getName();
            if (doctorNameWithPrefix.equalsIgnoreCase(name)) {
                LOGGER.log(Level.INFO, "Found the doctor with the provided name : {0}", name);
                return doctor;
            }
        }
        LOGGER.log(Level.INFO, "Could not find doctor with the provided name : {0}, Try adding 'Dr. ' as the prefix if you already haven't ", name);
        return null; 
    }

    /**
     * @param specialization String value of the specialization of the doctor 
     * @return List of doctors with the same specialization as mentioned in the parameter
     */
    public ArrayList<Doctor> findDoctorsBySpecialization(String specialization) {
        ArrayList<Doctor> doctorsWithSpecialization = new ArrayList<>();
        for (Doctor doctor : doctorDatabase) {
            if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                doctorsWithSpecialization.add(doctor);
            }
        }
        if (!doctorsWithSpecialization.isEmpty()) {
            LOGGER.log(Level.INFO, "found the doctor with the provided specialization: {0}", specialization);
            return doctorsWithSpecialization;
        } else {
            LOGGER.log(Level.WARNING, "Could not find any doctor with the provided specialization: {0}", specialization);
            return null;
        }
    }
    

     /**
     * @return Returns the array list of all the doctors (In the doctors database)
     */
        public ArrayList<Doctor> getAllDoctors(){
        return doctorDatabase;
        }

            /**
     * takes in the doctor and updates it according to the user 
     * @param updatedDoctor The doctor with the updated details
     */
    public void updateDoctor(Doctor updatedDoctor) {
        for (int i = 0; i < doctorDatabase.size(); i++) {//Iterate through all the doctors 
            Doctor doctor = doctorDatabase.get(i); //gets the doctor with the i value
            if (doctor.getId().equals(updatedDoctor.getId())) {
                doctorDatabase.set(i, updatedDoctor); //If doctor is found, Updates the doctor
                LOGGER.log(Level.INFO, "Doctor successfully updated  with the provided ID: {0}", updatedDoctor.getId());
                return;
            }
        }
        LOGGER.log(Level.WARNING, "Unable to update doctor. Could not find doctor with the provided ID: {0}: ", updatedDoctor.getId());
    }
    /**
     * @param id The ID of the doctor 
     * Deletes the doctor from the doctors database array list 
     */
    public void deleteDoctor(String id) {
        doctorDatabase.removeIf(doctor -> { //Remove the doctor from the doctors database only if there is a match phone
            if (doctor.getId().equals(id)) {
                LOGGER.log(Level.INFO, "Doctor with the provided ID ({0}) was successfully removed ", id);
                return true;
            }
            return false;
        });
        LOGGER.log(Level.WARNING, "No existing doctor found with the provided ID: {0}", id);//Logs That the doctor with the provided ID was not found 
    }
    
}
