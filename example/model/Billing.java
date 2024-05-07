/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;



/**
 *
 * @author Rithik
 */
public class Billing {
    private String billingId; //The billing ID that uniquely identifies the bill 
    private Patient patient; //The patient that the bill belongs to 
    private double amount; //The amount that is supposed to be paid 
    private double balance; //The balance that should be given to the patient 
    private String paymentDate; //The date when the payment should be made 
    private String invoiceNumber; //The invoice number 
    private boolean hasSuccessfullyPaid; //If the patient has successfully paid (Boolean value)

    public Billing(Patient patient, String invoiceNumber, double amount, boolean hasSuccessfullyPaid, String paymentDate, double balance) {
        this.billingId = UuidGenerator.generateUuidWithPrefix("bill"); //A unique ID generated with bill as the prefix
        this.patient = patient;
        this.amount = amount;
        this.balance = balance;
        this.paymentDate = paymentDate;
        this.invoiceNumber = invoiceNumber;
        this.hasSuccessfullyPaid = hasSuccessfullyPaid;
    }



    public String getBillingId() {
        return billingId;
    }

    public void setBillingId(String billingId) {
        this.billingId = billingId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isHasSuccessfullyPaid() {
        return hasSuccessfullyPaid;
    }

    public void setHasSuccessfullyPaid(boolean hasSuccessfullyPaid) {
        this.hasSuccessfullyPaid = hasSuccessfullyPaid;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
