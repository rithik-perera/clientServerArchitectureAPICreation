/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;


/**
 *
 * @author Rithik
 */
public class Person {
    private String id; //A unique identifier to identify each person 
    private String name; //Name of the person 
    private String phoneNumber; //Phone number of the person 
    private int age; //Age of the person 
    private String address; //Address of the person 


    public Person(String name,  String phoneNumber, int age, String address) {
        this.id = UuidGenerator.generateUuidWithPrefix("person"); //A unique ID generated with person as the prefix
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.address = address;
    }
    
        public Person(String id,String name,  String phoneNumber, int age, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
    public Person(){
    
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setRandomId(String prefix){
        this.id = UuidGenerator.generateUuidWithPrefix(prefix);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
}
