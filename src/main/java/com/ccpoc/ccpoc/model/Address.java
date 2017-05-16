package com.ccpoc.ccpoc.model;

public class Address {

    private long id;
    private String sfId;
    private String statename;
    private String addressline2;
    private String addressline1;
    private String personId;
    private String zipCode;
    private String ownerId;
    private String autoNumberName;
    
    //parameterized constructor
    public Address(long id, String sfId, String statename, String addressline2, String addressline1, String personId, String zipCode, String ownerId, String autoNumberName) {
        this.id = id;
        this.sfId = sfId;
        this.statename = statename;
        this.addressline2 = addressline2;
        this.addressline1 = addressline1;
        this.personId = personId;
        this.zipCode = zipCode;
        this.ownerId = ownerId;
        this.autoNumberName = autoNumberName;
    }
    
    //Empty Constructor
    public Address() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSfId() {
        return sfId;
    }

    public void setSfId(String sfId) {
        this.sfId = sfId;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getAutoNumberName() {
        return autoNumberName;
    }

    public void setAutoNumberName(String autoNumberName) {
        this.autoNumberName = autoNumberName;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", sfId=" + sfId + ", statename=" + statename + ", addressline2=" + addressline2 + ", addressline1=" + addressline1 + ", personId=" + personId + ", zipCode=" + zipCode + ", ownerId=" + ownerId + ", autoNumberName=" + autoNumberName + '}';
    }


}
