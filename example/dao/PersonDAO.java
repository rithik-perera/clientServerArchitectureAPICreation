/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.model.Person;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rithik
 */
public class PersonDAO {

    private static final Logger LOG = Logger.getLogger(PersonDAO.class.getName());
   
  private final static ArrayList<Person> personsDatabase =  new ArrayList<>(); //The list in which all the Persons will be stored in 
  
  //Sample data that will be used for testing purposes with postman 
  static {
      personsDatabase.add(new Person("hello", "212155451", 50, "kamathawatta Rd Colombo"));
  }

     /**
     * @param person The Person of type Person 
     This Person will be added to the Person database
     */
    public void addPerson(Person person) {
        personsDatabase.add(person);
         LOG.info("Person has been successfully added");
    }

        /**
     * @return Returns the array list of all the Persons (In the Persons database)
     */
    public ArrayList<Person> getAllPerson(){
        return personsDatabase;
    }
    
        /**
     * @param personId The ID of the Person 
     * @return Returns the Person if available in the Persons database, else it will return null
     */
    public Person getPersonById(String personId) {
        for (Person person : personsDatabase) { // Iterates through all the elements in the Persons database using for each loop 
            if (person.getId().equals(personId)) {
                return person;  // Person id matches, It will return the Person as an object 
            }
        }
        LOG.log(Level.WARNING, "Could not find prescription with the provided ID: {0}", personId); //Logs that the person was not found 
        return null; //Returns null if the Person was not found in the Persons database 
    }

        /**
     * takes in the Person and updates it according to the user 
     * @param updatedPerson The Person with the updated details
     */
    public void updatePerson(Person updatedPerson) {
    for (int i = 0; i < personsDatabase.size(); i++) { //Iterate through all the Persons 
        Person person = personsDatabase.get(i); //gets the Person with the i value
        if (person.getId().equals(updatedPerson.getId())) {
            personsDatabase.set(i, updatedPerson); //If Person is found, Updates the Person
            LOG.log(Level.INFO, "Person with ID ({0}) successfully updated", updatedPerson.getId());
            return; 
        }
    }
}
    
    
        /**
     * @param id The ID of the Person 
     * Deletes the Person from the Persons database array list 
     */
    public void deletePerson(String id) {
    personsDatabase.removeIf(person -> {//Remove the Person from the Persons database only if there is a match found
        if (person.getId().equals(id)) {
            LOG.log(Level.INFO, "Person with the provided ID ({0}) was successfully removed", id);
            return true;
        }
        return false;
    });
    LOG.log(Level.WARNING, "No existing Person found with the provided ID: {0}", id);//Logs That the Person with the provided ID was not found 
}

}
