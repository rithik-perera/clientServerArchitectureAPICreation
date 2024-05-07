/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;





import com.example.dao.BillingDAO;
import com.example.exception.ResourceConflictException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Billing;
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
@Path("/bills")
public class BillingResource {

    private final BillingDAO billingDAO;
    private static final Logger LOG = Logger.getLogger(BillingResource.class.getName());
    
    public BillingResource() {
        billingDAO = new BillingDAO();
    }
    
    /**
     * Endpoint for adding new billing record
     * If no billing ID is provided, UUID is generated
     *
     * @param newBilling The new billing object to add
     * @return Response indicating success or failure Which will appear in postman
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing newBilling) {
        if (newBilling.getBillingId() == null) {
            newBilling.setBillingId(UuidGenerator.generateUuidWithPrefix("bill")); // If the user does not provide the ID, one will be generated automatically using UuidGenerator 
        } else {
             LOG.log(Level.INFO, "A custom billing ID has been provided: {0}", newBilling.getBillingId()); 
            Billing existingBilling = billingDAO.getBillById(newBilling.getBillingId());
            if (existingBilling != null) { //Check if the provided ID is already existing, if so we can't allow           
                 throw new ResourceConflictException("Bill with the provided ID already exists");
            }
        }
        if (newBilling.getPatient() == null || //Check if all the data are provided in the  JSON 
            newBilling.getAmount() == 0 || 
            newBilling.getBalance() == 0 || 
            newBilling.getPaymentDate() == null ||
            newBilling.getInvoiceNumber() == null) {      
            throw new ResourceNotFoundException("All values are required in the JSON body"); //if all data not provided in JSON throw exception 
        }

        billingDAO.addBill(newBilling); //Add new bill to database 
        return Response.status(Response.Status.CREATED).build();
    }
    

    /**
     * Endpoint for getting bill by ID.
     *
     * @param billingId The ID of  bill to retrieve.
     * @return Response containing the bill if found, or error if not found.
     */
    @GET
    @Path("/{billingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBill(@PathParam("billingId") String billingId) {
        Billing bill = billingDAO.getBillById(billingId); //Use the get method to retrieve bill 
        if (bill != null) { //Will return null if bill doesn't exist in database Billing Database Array list
            return Response.ok(bill).build();
        } else {
            throw new ResourceNotFoundException("Bill not found with ID: " + billingId); //Throw exception if I did not found which in turn will provide a response to postman 
        }
    }
    
    
    /**
     * Endpoint for retrieving all bills.
     *
     * @return Response containing list of all bills.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBills() {
        ArrayList<Billing> bills = billingDAO.getAllBills(); // Retrieve all billing records from database 
        return Response.ok(bills).build();
    }

     /**
     * Endpoint for updating bill by ID.
     *
     * @param billingId     The ID of the bill to update.
     * @param updatedBilling The updated billing object.
     * @return Response indicating success or failure.
     */
    @PUT
    @Path("/{billingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBill(@PathParam("billingId") String billingId, Billing updatedBilling) {
            // Check if all values are set in the JSON
    if (updatedBilling.getPatient() == null ||
        updatedBilling.getInvoiceNumber() == null ||
        updatedBilling.getAmount() == 0 ||
        updatedBilling.getPaymentDate() == null ||
        updatedBilling.getBalance() == 0) {
         throw new ResourceNotFoundException("All values are required in the JSON body");
    }    
        Billing existingBill = billingDAO.getBillById(billingId); //Check if the ID already exists in the list of bills 
        if (existingBill != null) {
            //If billing ID exists in arraylist, ID is replaced with parameter value And updated bill is inserted 
            updatedBilling.setBillingId(billingId);
            billingDAO.updateBill(updatedBilling);
            return Response.ok(updatedBilling).build();
        } else {
            //Throw exception if bill not found in array list 
            throw new ResourceNotFoundException("Could not find bill with the provider ID: " + billingId);
        }
    }
    

    /**
     * Endpoint for deleting bill by ID.
     *
     * @param billingId The ID of bill.
     * @return Response saying success or failure of deleting bill.
     */    
    @DELETE
    @Path("/{billingId}")
    public Response deleteBill(@PathParam("billingId") String billingId) {
        Billing existingBill = billingDAO.getBillById(billingId); //Check if billing ID exists in array list 
        if (existingBill != null) {
            billingDAO.deleteBill(billingId); //If in array list, removal Bill
            return Response.ok().build();
        } else {
            throw new ResourceNotFoundException("Could not find bill with the provider ID: " + billingId); //Throw exception if not found in arraylist 
        }
    }
}
