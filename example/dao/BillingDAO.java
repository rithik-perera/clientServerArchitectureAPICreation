/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;




import com.example.model.Billing;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rithik
 */
public class BillingDAO {
    private static final ArrayList<Billing> billingDatabase = new ArrayList<>(); //The list in which all the bills will be stored in 
    private static final Logger LOGGER = Logger.getLogger(BillingDAO.class.getName());

    //Sample data that will be used for testing purposes with postman 
    static{
    Patient patient1 = new Patient("John Doe", "1234567890", 30, "123 Main St, Cityville, USA", "Chronic condition", "Stable");
    billingDatabase.add(new Billing(patient1, "INV-001", 100.00, true, "2024-04-01", 0.00));

    Patient patient2 = new Patient("Jane Smith", "0987654321", 25, "456 Elm St, Townsville, USA", "Acute condition", "Improving");
    billingDatabase.add(new Billing(patient2, "INV-002", 150.00, true, "2024-04-15", 25.00));

    Patient patient3 = new Patient("Alice Johnson", "9876543210", 40, "789 Oak St, Villagetown, USA", "Terminal condition", "Critical");
    billingDatabase.add(new Billing(patient3, "INV-003", 200.00, false, "2024-05-01", 200.00));
    
    Patient patient4 = new Patient("Bob Brown", "5678901234", 35, "321 Pine St, Countryside, USA", "Temporary condition", "Stable");
    billingDatabase.add(new Billing(patient4, "INV-004", 120.00, true, "2024-05-10", 0.00));

    Patient patient5 = new Patient("Emily Davis", "3456789012", 28, "567 Maple St, Mountainville, USA", "Chronic condition", "Improving");
    billingDatabase.add(new Billing(patient5, "INV-005", 80.00, false, "2024-05-20", 80.00));
    }
    
     /**
     * @param billing The bills of type Billing 
     This bill will be added to the Billing database
     */
    public void addBill(Billing billing) {
        billingDatabase.add(billing);
        LOGGER.info("Billing created successfully");
    }

     /**
     * @param billingId The ID of the bill 
     * @return Returns the bill if available in the billing database, else it will return null
     */
    public Billing getBillById(String billingId) {
        for (Billing billing : billingDatabase) { // Iterates through all the elements in the billing database using for each loop 
            if (billing.getBillingId().equals(billingId)) {
                return billing; // bill id matches, It will return the bill as an object 
            }
        }
        LOGGER.log(Level.WARNING, "Could not find bill with the provided ID: {0}", billingId);
        return null; //Returns null if the bill was not found in the billing database 
    }

     /**
     * @return Returns the array list of all the bills (In the billing database)
     */
    public ArrayList<Billing> getAllBills() {
        return billingDatabase;
    }

     /**
     * takes in the bill and updates it according to the user 
     * @param updatedBilling The bills with the updated details
     */
    public void updateBill(Billing updatedBilling) {
        for (int i = 0; i < billingDatabase.size(); i++) { //Iterate through all the billss 
            Billing billing = billingDatabase.get(i); //gets the bills with the i value
            if (billing.getBillingId().equals(updatedBilling.getBillingId())) {
                billingDatabase.set(i, updatedBilling); //If bill is found, Updates the bill
                LOGGER.log(Level.INFO, "Bill with ID ({0}) successfully updated", updatedBilling.getBillingId());
                return;
            }
        }
        LOGGER.log(Level.WARNING, "Failed to update bill");
    }

     /**
     * @param billingId The ID of the bill 
     * Deletes the bill from the Billing database array list 
     */
    public void deleteBill(String billingId) {
        billingDatabase.removeIf(bill -> {//Remove the bill from the Billing database only if there is a match found
            if (bill.getBillingId().equals(billingId)) {
                LOGGER.log(Level.INFO, "Bill with ID ({0}) successfully removed", billingId);
                return true;
            }
            return false;
        });
        LOGGER.log(Level.WARNING, "No existing bill found with the provided ID: {0}", billingId); //Logs That the bill with the provided ID was not found 
    }
}
