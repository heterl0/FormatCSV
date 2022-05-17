package com.heterl0;

/**
 * Class Person represent for a person with id, name, email, phone, address.
 * @author Le Van Hieu CE160866
 */
public class Person {
    String id;
    String name;
    String email;
    String phoneNumber;
    String address;

    /**
     * Construct a new object Person.
     * @param id the id of person
     * @param name the name of person
     * @param email the email of person
     * @param phoneNumber the phone number of person
     * @param address the address of person
     */
    public Person(String id, String name,String email, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    /**
     * Get the id of person
     * @return the id of person
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id of person
     * @param id the id of person
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the name of person
     * @return the name of person
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of person
     * @param name the name of person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the phone number of person
     * @return the the phone number of person
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the phone number of person
     * @param phoneNumber the phone number of person
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the address of person
     * @return the address of person
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of person
     * @param address the address of person
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the email of person
     * @return the email of person
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of person
     * @param email the email of person
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}

