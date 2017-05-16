package com.ccpoc.ccpoc.model;

import java.util.Date;

public class Person {

    private long id;
    private String sfId;
    private String firstname;
    private String lastname;
    private String fullname;
    private Date birthdate;
    private Double amount;
    private String autoNumberName;
    private String description;
    

    public Person() {

    }

    public Person(long id, String sfId, String firstname, String lastname, String fullname, Date birthdate, Double amount, String autoNumberName) {
        this.id = id;
        this.sfId = sfId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.amount = amount;
        this.autoNumberName = autoNumberName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public String getSfId() {
        return sfId;
    }

    public void setSfId(String sfId) {
        this.sfId = sfId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAutoNumberName() {
        return autoNumberName;
    }

    public void setAutoNumberName(String autoNumberName) {
        this.autoNumberName = autoNumberName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", sfId=" + sfId + ", firstname=" + firstname + ", lastname=" + lastname + ", fullname=" + fullname + ", birthdate=" + birthdate + ", amount=" + amount + ", autoNumberName=" + autoNumberName + '}';
    }
    
    
}
