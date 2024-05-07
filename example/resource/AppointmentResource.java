/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

import com.example.dao.AppointmentDAO;
import com.example.dao.DoctorDAO;
import com.example.exception.ResourceConflictException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Appointment;
import com.example.model.Doctor;
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
@Path("/appointments")
public class AppointmentResource {

    private AppointmentDAO appointmentDAO;
    private static final Logger LOG = Logger.getLogger(AppointmentResource.class.getName());
    
    

    public AppointmentResource() {
        appointmentDAO = new AppointmentDAO();
         
    }


        /**
     * Endpoint for adding new appointment 
     * If no appointment ID is provided, UUID is generated
     *
     * @param newAppointment The new appointment object to add
     * @return Appointment object
     */
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)    
public Appointment addAppointment(Appointment newAppointment) {
    if (newAppointment.getAppointmentId() == null) {
        newAppointment.setAppointmentId(UuidGenerator.generateUuidWithPrefix("appointment"));// Generate a UUID if no appointments ID  provided
    } else {
         LOG.log(Level.INFO, "A custom appointment ID has been provided: {0}", newAppointment.getAppointmentId());
        Appointment existingAppointment = appointmentDAO.getAppointmentById(newAppointment.getAppointmentId());
        if (existingAppointment != null) { // Check if provided ID already exists
        throw new ResourceConflictException("Appointment with the provided ID already exists");

        }
    }
    if (newAppointment.getDoctorId() == null || // Check if all required fields are provided in the JSON body
        newAppointment.getPatient() == null ||
        newAppointment.getAppointmentDate() == null ||
        newAppointment.getAppointmentTime() == null) {
        throw new ResourceNotFoundException("All values are required in the JSON body");
        
    }
    
    try{


    Doctor doctor = DoctorDAO.getDoctorByIdUsingClassName(newAppointment.getDoctorId()); //Retrieve doctor using ID
    if (doctor == null) { 
        throw new ResourceNotFoundException("Could not find the doctor with the provided ID: " + newAppointment.getDoctorId()); // If doctor is not found, throw an exception
       
    }
    
    newAppointment.setDoctor(doctor); // Set doctor for appointments
    appointmentDAO.addAppointment(newAppointment);  // Add appointments to the appointments array list  
    }catch(ResourceNotFoundException e){
        LOG.info(e.getMessage());
        return null;
    }
    return newAppointment;
}
    
    /**
     * Endpoint for retrieving all appointments.
     *
     * @return Response containing list of all appointments.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        ArrayList<Appointment> appointments = appointmentDAO.getAllAppointments(); // Retrieve all appointments from database       
        return Response.ok(appointments).build();
    }

        /**
     * Endpoint for getting appointment by ID.
     *
     * @param appointmentId The ID of  appointment to retrieve.
     * @return Response containing the appointment if found, or error if not found.
     */
    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAppointment(@PathParam("appointmentId") String appointmentId) {
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);  //Use the get method to retrieve appointment 
        if (appointment != null) { //Will return null if appointment doesn't exist in database appointments Database Array list
            return Response.ok(appointment).build();
        } else {
            throw new ResourceNotFoundException("Could not find appointment with the provided ID: " + appointmentId); //Throw exception if I did not found which in turn will provide a response to postman 
        }
    }

     /**
     * Endpoint for updating appointment by ID.
     *
     * @param appointmentId     The ID of the appointment to update.
     * @param updatedAppointment The updated appointment object.
     * @return Response indicating success or failure.
     */
    @PUT
    @Path("/{appointmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("appointmentId") String appointmentId, Appointment updatedAppointment) {
        
            // Check if all values are set in the JSON
    if (updatedAppointment.getDoctorId() == null ||
        updatedAppointment.getPatient() == null ||
        updatedAppointment.getAppointmentDate() == null ||
        updatedAppointment.getAppointmentTime() == null) {
         throw new ResourceNotFoundException("All values are required in the JSON body");
    }    
        updatedAppointment.setAppointmentId(appointmentId);  //ID is replaced with parameter value 
        Appointment existingAppointment = appointmentDAO.getAppointmentById(appointmentId);  //Check if the ID already exists in the list of appointments
        if (existingAppointment != null) {
             //If appointment ID exists in arraylist, updated appointment is inserted 
            try {
                appointmentDAO.updateAppointment(updatedAppointment);
                return Response.ok(updatedAppointment).build();
            } catch (IllegalArgumentException e) {
                return Response.serverError().entity("Failed to update appointment").build();
            }
        } else {
            throw new ResourceNotFoundException("Appointment not found with ID: " + appointmentId); //Throw exception if appointment not found in array list 
        }
    }
    
    
    /**
     * Endpoint for deleting appointment by ID.
     *
     * @param appointmentId The ID of appointment.
     * @return Response saying success or failure of deleting appointment.
     */ 
    @DELETE
    @Path("/{appointmentId}")
    public Response deleteAppointment(@PathParam("appointmentId") String appointmentId) {
        Appointment existingAppointment = appointmentDAO.getAppointmentById(appointmentId); //Check if appointment ID exists in array list 
        if (existingAppointment != null) {
            appointmentDAO.deleteAppointment(appointmentId); //If in array list, remove appointment
            return Response.ok("Appointment with ID (" + appointmentId + ") successfully removed").build();
        } else {
            throw new ResourceNotFoundException("Could not find appointment with the provided ID: " + appointmentId); //Throw exception if not found in arraylist
        }
    }
}