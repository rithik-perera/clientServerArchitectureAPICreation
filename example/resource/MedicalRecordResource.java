/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

import com.example.dao.MedicalRecordDAO;
import com.example.exception.ResourceConflictException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.MedicalRecord;
import com.example.model.UuidGenerator;

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
@Path("/medicalRecords")
public class MedicalRecordResource {

    private final MedicalRecordDAO medicalRecordDAO;
    private static final Logger LOG = Logger.getLogger(MedicalRecordResource.class.getName());
    

    public MedicalRecordResource() {
        medicalRecordDAO = new MedicalRecordDAO();
    }

        /**
     * Endpoint for adding new medical record record
     * If no medical record ID is provided, UUID is generated
     *
     * @param newMedicalRecord The new medical record object to add
     * @return Response indicating success or failure Which will appear in postman
     */
@POST
@Consumes(MediaType.APPLICATION_JSON)
public Response addMedicalRecord(MedicalRecord newMedicalRecord) {    
    if (newMedicalRecord.getMedicalRecordId() == null) {       
        newMedicalRecord.setMedicalRecordId(UuidGenerator.generateUuidWithPrefix("medicalRecord")); // If the user does not provide the ID, one will be generated automatically using UuidGenerator
    } else {       
        MedicalRecord existingMedicalRecord = medicalRecordDAO.getMedicalRecordById(newMedicalRecord.getMedicalRecordId());
        if (existingMedicalRecord != null) {   //Check if the provided ID is already existing, if so we can't allow          
            LOG.log(Level.INFO, "A custom medical record ID has been provided: {0}", newMedicalRecord.getMedicalRecordId());
             throw new ResourceConflictException("Medical Record with the provided ID already exists");
        }
    }   
    if (newMedicalRecord.getPatient() == null || //Check if all the data are provided in the  JSON 
        newMedicalRecord.getDiagnoses() == null ||
        newMedicalRecord.getTreatments() == null) {         
        throw new ResourceNotFoundException("All values are required in the JSON body");
    }
    medicalRecordDAO.addMedicalRecord(newMedicalRecord);  //Add new mdeical record to database 
    return Response.status(Response.Status.CREATED).build();
}

    /**
     * Endpoint for retrieving all medical records.
     *
     * @return Response containing list of all medical records.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicalRecords() {
        ArrayList<MedicalRecord> medicalRecords = medicalRecordDAO.getAllMedicalRecords(); // Retrieve all medical records from database 
        return Response.ok(medicalRecords).build();
    }

        /**
     * Endpoint for getting medical record by ID.
     *
     * @param medicalRecordId The ID of  medical record to retrieve.
     * @return Response containing the medical record if found, or error if not found.
     */
    @GET
    @Path("/{medicalRecordId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findMedicalRecord(@PathParam("medicalRecordId") String medicalRecordId) {
        MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordById(medicalRecordId);  //Use the get method to retrieve medical record 
        if (medicalRecord != null) { //Will return null if medical record doesn't exist in database medical records Database Array list
            return Response.ok(medicalRecord).build();
        } else {
            throw new ResourceNotFoundException("Could not find medical record with the provided ID: " + medicalRecordId); //Throw exception not found which in turn will provide a response to postman 
        }
    }
    
    
     /**
     * Endpoint for updating medical record by ID.
     *
     * @param medicalRecordId     The ID of the medical record to update.
     * @param updatedMedicalRecord The updated medical record object.
     * @return Response indicating success or failure.
     */
    @PUT
    @Path("/{medicalRecordId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("medicalRecordId") String medicalRecordId, MedicalRecord updatedMedicalRecord) {
        // Check if all values are set in the JSON
        if (updatedMedicalRecord.getPatient() == null ||
        updatedMedicalRecord.getDiagnoses() == null ||
        updatedMedicalRecord.getTreatments() == null) {
         throw new ResourceNotFoundException("All values are required in the JSON body");
    } 
        updatedMedicalRecord.setMedicalRecordId(medicalRecordId); // ID is replaced with parameter value 
        MedicalRecord existingMedicalRecord = medicalRecordDAO.getMedicalRecordById(medicalRecordId);  //Check if the ID already exists in the list of medical records
        if (existingMedicalRecord != null) {
             //If medical record ID exists in arraylist, ID is replaced with parameter value And updated medical record is inserted 
            try {
                medicalRecordDAO.updateMedicalRecord(updatedMedicalRecord);  //If medical record ID exists in arraylist,updated medical record is inserted 
                return Response.ok(updatedMedicalRecord).build();
            } catch (IllegalArgumentException e) {
                return Response.serverError().entity("Failed to update Medical Record").build();
            }
        } else {
            throw new ResourceNotFoundException("Could not find medical record with the provided ID: " + medicalRecordId); //Throw exception if medical record not found in array list 
        }
    }
    
    
    /**
     * Endpoint for deleting medical record by ID.
     *
     * @param medicalRecordId The ID of medical record.
     * @return Response saying success or failure of deleting medical record.
     */ 
    @DELETE
    @Path("/{medicalRecordId}")
    public Response deleteMedicalRecord(@PathParam("medicalRecordId") String medicalRecordId) {
        MedicalRecord existingMedicalRecord = medicalRecordDAO.getMedicalRecordById(medicalRecordId); //Check if medical record ID exists in array list 
        if (existingMedicalRecord != null) {
            medicalRecordDAO.deleteMedicalRecord(medicalRecordId); //If in array list, removal medical record
            return Response.ok("Medical Record with ID (" + medicalRecordId + ") successfully removed").build();
        } else {
            throw new ResourceNotFoundException("Could not find medical record with the provided ID: " + medicalRecordId); //Throw exception if not found in arraylist
        }
    }
}
