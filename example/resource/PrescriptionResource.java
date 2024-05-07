/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;




import com.example.dao.DoctorDAO;
import com.example.dao.PrescriptionDAO;
import com.example.exception.ResourceConflictException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Doctor;
import com.example.model.Prescription;
import com.example.model.UuidGenerator;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Rithik
 */
@Path("/prescriptions")
public class PrescriptionResource {

    private final PrescriptionDAO prescriptionDAO;
    private static final Logger LOG = Logger.getLogger(PrescriptionResource.class.getName());
    private DoctorDAO doctorDAO;
    
    public PrescriptionResource() {
        prescriptionDAO = new PrescriptionDAO();
    }

        /**
     * Endpoint for adding new prescription 
     * If no prescription ID is provided, UUID is generated
     *
     * @param newPrescription The new prescription object to add
     * @return prescription object
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Prescription addPrescription(Prescription newPrescription) {
        if (newPrescription.getPrescriptionId() == null) {
            newPrescription.setPrescriptionId(UuidGenerator.generateUuidWithPrefix("prescription"));// Generate a UUID if no prescription ID  provided
        } else {
            LOG.log(Level.INFO, "A custom prescription ID has been provided: {0}", newPrescription.getPrescriptionId());
            Prescription existingPrescription = prescriptionDAO.getPrescriptionById(newPrescription.getPrescriptionId());
            if (existingPrescription != null) {  // Check if provided ID already exists
                throw new ResourceConflictException("Prescription with the provided ID already exists");
            }
        }

        if (newPrescription.getPatient() == null || // Check if all required fields are provided in the JSON body
            newPrescription.getDoctorId() == null || 
            newPrescription.getMedicationName() == null ||
            newPrescription.getDosage() == null ||
            newPrescription.getInstructions() == null ||
            newPrescription.getDuration() == null) {
            throw new ResourceNotFoundException("All values are required in the JSON body");
        }

        try {

            Doctor doctor = DoctorDAO.getDoctorByIdUsingClassName(newPrescription.getDoctorId()); //Retrieve doctor using ID
            if (doctor == null) {
                throw new ResourceNotFoundException("Could not find the doctor with the provided ID: " + newPrescription.getDoctorId()); // If doctor is not found, throw an exception
            }
            newPrescription.setDoctor(doctor); // Set doctor for prescription
            prescriptionDAO.addPrescription(newPrescription); // Add prescription to the prescription array list
        } catch (ResourceNotFoundException e) {
            LOG.info(e.getMessage());
            return null;
        }

        return newPrescription;
    }


        /**
     * Endpoint for getting prescription by ID.
     *
     * @param prescriptionId The ID of  prescription to retrieve.
     * @return Response containing the prescription if found, or error if not found.
     */
        @GET
        @Path("/{prescriptionId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getPrescription(@PathParam("prescriptionId") String prescriptionId) {
            Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);  //Use the get method to retrieve prescription 
            if (prescription != null) { //Will return null if prescription doesn't exist in database prescriptions Database Array list
                return Response.ok(prescription).build();
            } else {
                throw new ResourceNotFoundException("Prescription not found with ID: " + prescriptionId); //Throw exception if I did not found which in turn will provide a response to postman 
            }
        }
        
        
     /**
     * Endpoint for retrieving all prescriptions.
     *
     * @return Response containing list of all prescriptions.
     */   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Prescription> getAllPrescriptions() {
        ArrayList<Prescription> prescriptions = prescriptionDAO.getAllPrescriptions(); // Retrieve all prescriptions records from database
        return prescriptions;
    }

     /**
     * Endpoint for updating prescription by ID.
     *
     * @param prescriptionId     The ID of the prescription to update.
     * @param updatedPrescription The updated prescription object.
     * @return Response indicating success or failure.
     */
    @PUT
    @Path("/{prescriptionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("prescriptionId") String prescriptionId, Prescription updatedPrescription) {
        // Check if all values are set in the JSON
        if (updatedPrescription.getPatient() == null ||
            updatedPrescription.getDoctor() == null ||
            updatedPrescription.getMedicationName() == null ||
            updatedPrescription.getDosage() == null ||
            updatedPrescription.getInstructions() == null ||
            updatedPrescription.getDuration() == null) {
             throw new ResourceNotFoundException("All values are required in the JSON body");
        } 
        Prescription existingPrescription = prescriptionDAO.getPrescriptionById(prescriptionId);  //Check if the ID already exists in the list of prescription
        if (existingPrescription != null) {
             //If prescription ID exists in arraylist, ID is replaced with parameter value And updated prescription is inserted 
            updatedPrescription.setPrescriptionId(prescriptionId);
            prescriptionDAO.updatePrescription(updatedPrescription);
            return Response.ok(updatedPrescription).build();
        } else {
            throw new ResourceNotFoundException("Could not find prescription with the provided ID: " + prescriptionId); //Throw exception if prescription not found in array list 
        }
    }
    
    
    /**
     * Endpoint for deleting prescription by ID.
     *
     * @param prescriptionId The ID of prescription.
     * @return Response saying success or failure of deleting prescription.
     */ 
    @DELETE
    @Path("/{prescriptionId}")
    public Response deletePrescription(@PathParam("prescriptionId") String prescriptionId) {
        Prescription existingPrescription = prescriptionDAO.getPrescriptionById(prescriptionId); //Check if prescription ID exists in array list 
        if (existingPrescription != null) {
            prescriptionDAO.deletePrescription(prescriptionId); //If in array list, removal prescription
            return Response.ok().build();
        } else {
            throw new ResourceNotFoundException("Could not find prescription with the provided ID : " + prescriptionId); //Throw exception if not found in arraylist
        }
    }
}