/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.resource;
import com.example.dao.PatientDAO;
import com.example.exception.ResourceConflictException;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.ResourceNotFoundExceptionMapper;
import com.example.model.Patient;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rithik
 */
@Path("/patients")
public class PatientResource {

  
    private final PatientDAO patientDAO;
    private static final Logger LOG = Logger.getLogger(PatientResource.class.getName());
    
    
    public PatientResource() {
        patientDAO = new PatientDAO();
    }



    /**
     * Endpoint for adding new patient
     * If no patient ID is provided, ID is generated
     *
     * @param newPatient The new patient object to add
     * @return Response indicating success or failure Which will appear in postman
     */    
@POST
@Consumes(MediaType.APPLICATION_JSON)
public Response addPatient(Patient newPatient) {
    if (newPatient.getId() == null) {
        newPatient.setRandomId("patient"); // If the user does not provide the ID, one will be generated automatically 
    }
    else {
        LOG.log(Level.INFO, "A custom ID has been provided: {0}", newPatient.getId());
        Patient existingPatient = patientDAO.getPatientById(newPatient.getId());
        if (existingPatient != null) {  //Check if the provided ID is already existing, if so we can't allow           
             throw new ResourceConflictException("Patient with the provided ID already exists");
        }
    }
   
    if (newPatient.getName() == null || //Check if all the data are provided in the  JSON 
        newPatient.getPhoneNumber() == null ||
        newPatient.getAge() == 0 || 
        newPatient.getAddress() == null ||
        newPatient.getMedicalHistory() == null ||
        newPatient.getCurrentHealthStatus() == null) {
        
        throw new ResourceNotFoundException("All values are required in the JSON body");
    }

    patientDAO.addPatient(newPatient);  //Add new patient to database 


    return Response.status(Response.Status.CREATED).build();

}

    /**
     * Endpoint for retrieving all patients.
     *
     * @return Response containing list of all patients.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Patient> getAllPatients() {  
        return patientDAO.getAllPatients(); // Retrieve all patients records from database 
    }

        /**
     * Endpoint for getting patient by ID.
     *
     * @param patientId The ID of  patient to retrieve.
     * @return Response containing the patient if found, or error if not found.
     */
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient findPatient(@PathParam("patientId") String patientId) {
        Patient patient = patientDAO.getPatientById(patientId);  //Use the get method to retrieve patient 
        if (patient != null) { //Will return null if patient doesn't exist in database patients Database Array list
            return patient;
        } else {
            throw new ResourceNotFoundException("Could not find patient with the provided ID: " + patientId); //Throw exception if I did not found which in turn will provide a response to postman 
        }
    }
    
     /**
     * Endpoint for updating patient by ID.
     *
     * @param patientId     The ID of the patient to update.
     * @param updatedPatient The updated patient object.
     * @return Response indicating success or failure.
     */
    @PUT
    @Path("/{patientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("patientId") String patientId, Patient updatedPatient) {
            // Check if all values are set in the JSON
    if (updatedPatient.getName() == null ||
        updatedPatient.getPhoneNumber() == null ||
        updatedPatient.getAge() == 0 ||
        updatedPatient.getAddress() == null ||
        updatedPatient.getMedicalHistory() == null ||
        updatedPatient.getCurrentHealthStatus() == null) {
         throw new ResourceNotFoundException("All values are required in the JSON body");
    }

        updatedPatient.setId(patientId); // ID is replaced with parameter value 
        Patient existingPatient = patientDAO.getPatientById(patientId);  //Check if the ID already exists in the list of patients
        if (existingPatient != null) {
             //If billing ID exists in arraylist, ID is replaced with parameter value 
            patientDAO.updatePatient(updatedPatient);
            return Response.ok(updatedPatient).build();
        } else {
            throw new ResourceNotFoundException("Could not find patient with the provided ID: " + patientId); //Throw exception if patient not found in array list 
        }
    }
    
    
    /**
     * Endpoint for deleting patient by ID.
     *
     * @param patientId The ID of patient.
     * @return Response saying success or failure of deleting patient.
     */ 
    @DELETE
    @Path("/{patientId}")
    public Response deletePatient(@PathParam("patientId") String patientId) {
        Patient existingPatient = patientDAO.getPatientById(patientId); //Check if patient ID exists in array list 
        if (existingPatient != null) {
            patientDAO.deletePatient(patientId); //If in array list, remove patient
            return Response.ok("Patient with ID (" + patientId + ") has been successfully removed").build();
        } else {
            throw new ResourceNotFoundException("Could not find patient with the provided ID: " + patientId); //Throw exception if not found in arraylist
        }
    }
}