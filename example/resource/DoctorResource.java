/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;




/**
 *
 * @author Rithik
 */
import com.example.dao.DoctorDAO;
import com.example.exception.ResourceConflictException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Doctor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/doctors")
public class DoctorResource {

    private DoctorDAO doctorDAO;

    public DoctorResource() {
        doctorDAO = new DoctorDAO();
    }
    private static final Logger LOG = Logger.getLogger(DoctorResource.class.getName());
    
    
    /**
     * Endpoint for adding doctors record
     * If no doctor ID is provided, id is generated
     *
     * @param newDoctor The new doctors object to add
     * @return Response indicating success or failure Which will appear in postman
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor newDoctor) {

        if (newDoctor.getId() == null) {

            newDoctor.setRandomId("doctor"); // If the user does not provide the ID, one will be generated automatically u
        } else {
            LOG.log(Level.INFO, "A custom ID has been provided: {0}", newDoctor.getId());
            Doctor existingDoctor = doctorDAO.getDoctorById(newDoctor.getId());      
            if (existingDoctor != null) {  //Check if the provided ID is already existing, if so we can't allow            
                 throw new ResourceConflictException("Doctor with the provided ID already exists");
            }
        }

        if (newDoctor.getName() == null || //Check if all the data are provided in the  JSON 
            newDoctor.getPhoneNumber() == null ||
            newDoctor.getAge() == 0 || 
            newDoctor.getAddress() == null ||
            newDoctor.getSpecialization() == null) {

            throw new ResourceNotFoundException("All values are required in the JSON body");
        }

        doctorDAO.addDoctor(newDoctor);  //Add new Doctor to database 
        return Response.status(Response.Status.CREATED).build();
    }

        /**
     * Endpoint for retrieving all doctors.
     *
     * @return Response containing list of all doctors.
     */
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllDoctors() {
            ArrayList<Doctor> doctors = doctorDAO.getAllDoctors(); // Retrieve all doctors from database 
            return Response.ok(doctors).build();
        }

            /**
     * Endpoint for getting doctor by ID.
     *
     * @param doctorId The ID of  doctor to retrieve.
     * @return Response containing the doctor if found, or error if not found.
     */
        @GET
        @Path("/{doctorId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getDoctorById(@PathParam("doctorId") String doctorId) {
            Doctor doctor = doctorDAO.getDoctorById(doctorId);  //Use the get method to retrieve doctor 
            if (doctor != null) { //Will return null if doctor doesn't exist in database doctors Database Array list
                return Response.ok(doctor).build();
            } else {
                throw new ResourceNotFoundException("Could not find doctor with the provided ID: {0}" + doctorId); //Throw exception if not found which in turn will provide a response to postman 
            }
        }
    
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorByName(@PathParam("name") String name) {
        Doctor doctor = doctorDAO.findDoctorByName(name);
        if (doctor != null) {
            return Response.ok(doctor).build();
        } else {
            throw new ResourceNotFoundException("Try adding 'Dr. ' as the prefix if you already haven't. Could not find doctor with the provided name: " + name);
        }
    }

    @GET
    @Path("/specialization/{specialization}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findDoctorsBySpecialization(@PathParam("specialization") String specialization) {
        ArrayList<Doctor> doctors = doctorDAO.findDoctorsBySpecialization(specialization);
        if (doctors != null) {
            return Response.ok(doctors).build();
        } else {
            throw new ResourceNotFoundException("No doctors found with the provided specialization: " + specialization);
        }
    }

     /**
     * Endpoint for updating doctor by ID.
     *
     * @param doctorId     The ID of the doctor to update.
     * @param updatedDoctor The updated doctor object.
     * @return Response indicating success or failure.
     */
    @PUT
    @Path("/{doctorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("doctorId") String doctorId, Doctor updatedDoctor) {
            // Check if all values are set in the JSON
    if (updatedDoctor.getName() == null ||
        updatedDoctor.getPhoneNumber() == null ||
        updatedDoctor.getAge() == 0 ||
        updatedDoctor.getAddress() == null ||
        updatedDoctor.getSpecialization() == null) {
         throw new ResourceNotFoundException("All values are required in the JSON body");
    }
        updatedDoctor.setId(doctorId); // ID is replaced with parameter value 
        Doctor existingDoctor = doctorDAO.getDoctorById(doctorId);  //Check if the ID already exists in the list of doctors
        if (existingDoctor != null) {
             //If doctor ID exists in arraylist, updated doctor is inserted 
            try {
                doctorDAO.updateDoctor(updatedDoctor);
                return Response.ok(updatedDoctor).build();
            } catch (IllegalArgumentException e) {
                return Response.serverError().entity("Failed to update doctor").build();
            }
        } else {
            throw new ResourceNotFoundException("Could not find doctor with the provided ID: " + doctorId); //Throw exception if doctor not found in array list 
        }
    }

    /**
     * Endpoint for deleting doctor by ID.
     *
     * @param doctorId The ID of doctor.
     * @return Response saying success or failure of deleting doctor.
     */ 
    @DELETE
    @Path("/{doctorId}")
    public Response deleteDoctor(@PathParam("doctorId") String doctorId) {
        Doctor existingDoctor = doctorDAO.getDoctorById(doctorId); //Check if doctor ID exists in array list 
        if (existingDoctor != null) {
            doctorDAO.deleteDoctor(doctorId); //If in array list, removal doctor
            return Response.ok("Doctor with ID (" + doctorId + ") has been successfully removed").build();
        } else {
            throw new ResourceNotFoundException("No existing doctor with the provided ID: " + doctorId); //Throw exception if not found in arraylist
        }
    }
}