/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;

import com.example.dao.PersonDAO;
import com.example.exception.ResourceBadRequestException;
import com.example.exception.ResourceConflictException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Person;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.ws.rs.Path;

/**
 *
 * @author Rithik
 */
@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
    private final PersonDAO personDAO;
    private static final Logger LOGGER = Logger.getLogger(PersonResource.class.getName());

    public PersonResource() {
        this.personDAO = new PersonDAO();
    }

    /**
     * Endpoint for adding new person
     * If no person ID is provided, ID is generated
     *
     * @param newPerson The new person object to add
     * @return Response indicating success or failure Which will appear in postman
     */    
@POST
@Consumes(MediaType.APPLICATION_JSON)
public Response addPerson(Person newPerson) {
    try {
        if (newPerson.getId() == null) {
            newPerson.setRandomId(("person")); // If the user does not provide the ID, one will be generated automatically
        } else {
            LOGGER.log(Level.INFO, "A custom person ID has been provided: {0}", newPerson.getId());
            Person existingPerson = personDAO.getPersonById(newPerson.getId());
            if (existingPerson != null) {   //Check if the provided ID is already existing, if so we can't allow         
                throw new ResourceConflictException("Person with the provided ID already exists");
            }
        }

        if (newPerson.getName() == null || //Check if all the data are provided in the  JSON 
            newPerson.getPhoneNumber() == null ||
            newPerson.getAge() == 0 || 
            newPerson.getAddress() == null) {
            throw new ResourceNotFoundException("All values are required in the JSON body");
        }
        personDAO.addPerson(newPerson);  //Add new person to database 
        return Response.status(Response.Status.CREATED).build();
    } catch (IllegalArgumentException e) {
        throw new ResourceBadRequestException("There was a mismatch in the data types");
    }
}
        /**
     * Endpoint for retrieving all persons.
     *
     * @return Response containing list of all persons.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Person> getAllPersons(){ 
        return personDAO.getAllPerson(); // Retrieve all personS records from database 
    }

        /**
     * Endpoint for getting person by ID.
     *
     * @param personId The ID of  person to retrieve.
     * @return Response containing the person if found, or error if not found.
     */
    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonById(@PathParam("personId") String personId) {
        Person person = personDAO.getPersonById(personId);  //Use the get method to retrieve person 
        if (person != null) { //Will return null if person doesn't exist in database person Database Array list
            return person;
        } else {
            throw new ResourceNotFoundException("Could not find person with the provided ID: " + personId); //Throw exception if I did not found which in turn will provide a response to postman 
        }
    }
    
     /**
     * Endpoint for updating person by ID.
     *
     * @param personId     The ID of the person to update.
     * @param updatedPerson The updated person object.
     * @return Response indicating success or failure.
     */    
    @PUT
    @Path("/{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("personId") String personId, Person updatedPerson) {
        // Check if all values are set in the JSON
        if (updatedPerson.getName() == null ||
            updatedPerson.getPhoneNumber() == null ||
            updatedPerson.getAge() == 0 ||
            updatedPerson.getAddress() == null) {
             throw new ResourceNotFoundException("All values are required in the JSON body");
        }  
        updatedPerson.setId(personId); // ID is replaced with parameter value 
        if (personDAO.getPersonById(personId) != null) {  //Check if the ID already exists in the list of persons
             //If person ID exists in arraylist, updated person is inserted 
            personDAO.updatePerson(updatedPerson);
            return Response.ok(updatedPerson).build();
        } else {
            throw new ResourceNotFoundException("Person not found with ID: " + personId + ". Update failed."); //Throw exception if person not found in array list 
        }
    }
    
    
    /**
     * Endpoint for deleting person by ID.
     *
     * @param personId The ID of person.
     * @return Response saying success or failure of deleting person.
     */     
    @DELETE
    @Path("/{personId}")
    public Response deletePerson(@PathParam("personId") String personId) {
        Person existingPerson =  personDAO.getPersonById(personId); //Check if person ID exists in array list 
        if (existingPerson != null) {
            personDAO.deletePerson(personId); //If in array list, removal person
            return Response.ok("Person with ID (" + personId + ") has been successfully removed").build();
        } else {
            throw new ResourceNotFoundException("Person not found with ID: " + personId + ". Deletion failed."); //Throw exception if not found in arraylist
        }
    }
    
    
}
