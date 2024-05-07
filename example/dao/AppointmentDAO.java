/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.model.Appointment;
import com.example.model.Doctor;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Rithik
 */
public class AppointmentDAO {
       private static final ArrayList<Appointment> appointmentDatabase = new ArrayList<>(); //The list in which all the Appointments will be stored in 
    private static final Logger LOGGER = Logger.getLogger(AppointmentDAO.class.getName());


    //Sample data that will be used for testing purposes with postman 
        static {
        appointmentDatabase.add(new Appointment(
                "doc111",
                new Patient("Michael Johnson", "5551234567", 45, "Rajagiriya", "Diabetes", "Under treatment"), 
                "01/01/2022 T 14:00",
                "14:00"
        ));

        appointmentDatabase.add(new Appointment(
                "doc111",
                new Patient("Jane Smith", "9876543210", 25, "Rajagiriya", "Allergic reaction", "Recovering"), 
                "02/15/2022 T 10:30",
                "10:30"
        ));

        appointmentDatabase.add(new Appointment(
               "doc111",
                new Patient("John Doe", "1234567890", 30, "Rajagiriya", "Chronic condition", "Stable"), 
                "03/20/2022 T 09:45",
                "09:45"
        ));

        appointmentDatabase.add(new Appointment(
              "doc111",
                new Patient("Emily Davis", "9871234560", 35, "Rajagiriya", "Hypertension", "Stable"), 
                "04/10/2022 T 16:15",
                "16:15"
        ));

        appointmentDatabase.add(new Appointment(
                "doc111",
                new Patient("Olivia", "2345678", 42, "Rajagiriya", "nothing", "fever"), 
                "05/05/2022 T 11:00",
                "11:00"
        ));
    }
    


    /**
     * @return Returns the array list of all the Appointments (In the Appointments database) */ 
    public ArrayList<Appointment> getAllAppointments() {
        return appointmentDatabase;
    } 

   /**
     * @param id The idea of the patient 
     * @return an array list of appointments that are under the same patient */
    public ArrayList<Appointment> getAppointmentsByPatientId(String id) {
        ArrayList<Appointment> patientAppointments = new ArrayList<>(); //Create an empty array list to store the appointments 
        for (Appointment appointment : appointmentDatabase) {
            if (appointment.getPatient().getId().equals(id)) {
                patientAppointments.add(appointment); //Add to the patientAppointments If the patient id is found in the appointmentDatabase
            }
        }
        if (patientAppointments.isEmpty()) {
            LOGGER.log(Level.INFO, "No appointments found for patient with ID: {0}", id); //If patient ID not found, provide log 
            return null; 
        } else {
            return patientAppointments; //If successful returns the patientAppointments array list 
        }
    }

    /**
     * The appointment to the appointment database 
     * @param appointment appointment of type Appointment  */
    public void addAppointment(Appointment appointment) {
        appointmentDatabase.add(appointment);
        LOGGER.log(Level.INFO, "New appointment added successfully");
    }

     /**
     * @param appointmentId  The ID of the Appointments 
     * @return  Returns the Appointment if available in the Appointment database, else it will return null */
    public Appointment getAppointmentById(String appointmentId) {
        for (Appointment appointment : appointmentDatabase) { // Iterates through all the elements in the Appointment database using for each loop 
            if (appointment.getAppointmentId().equals(appointmentId)) {
                LOGGER.log(Level.INFO, "Successfully found an appointment with the provider ID: {0} ", appointmentId);
                return appointment;  // Appointment id matches, It will return the prescription as an object 
            }
        }
        LOGGER.log(Level.WARNING, "Could not find appointment with the provided ID: {0}", appointmentId);
        return null;
    }

        /**
     * takes in the Appointment and updates it according to the user 
     * @param updatedAppointment The Appointment with the updated details
     */
    public void updateAppointment(Appointment updatedAppointment) {
        for (int i = 0; i < appointmentDatabase.size(); i++) { //Iterate through all the Appointments 
            Appointment appointment = appointmentDatabase.get(i); //gets the Appointment with the i value
            if (appointment.getAppointmentId().equals(updatedAppointment.getAppointmentId())) {
                appointmentDatabase.set(i, updatedAppointment); //If Appointment is found, Updates the Appointment 
                LOGGER.log(Level.INFO, "Appointment successfully updated with ID: {0}", updatedAppointment.getAppointmentId());
                return;
            }
        }
        LOGGER.log(Level.WARNING, "Unable to update appointment. Could not find appointment with the provided ID: {0}", updatedAppointment.getAppointmentId());
    }

       /**
     * @param appointmentId The ID of the Appointment 
     * Deletes the Appointment from the Appointment database array list 
     */
public void deleteAppointment(String appointmentId) {
    appointmentDatabase.removeIf(appointment -> { //Remove the Appointment from the Appointment database only if there is a match found 
        if (appointment.getAppointmentId().equals(appointmentId)) {
            LOGGER.log(Level.INFO, "Appointment with the provided ID ({0}) was successfully removed ", appointmentId);
            return true;
        }
        return false;
    });
    LOGGER.log(Level.WARNING, "No existing appointment found with the provided ID : {0}", appointmentId);//Logs That the Appointment with the provided ID was not found 
}

}
