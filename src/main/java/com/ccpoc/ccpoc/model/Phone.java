package com.ccpoc.ccpoc.model;

import java.util.Date;

public class Phone {

    private long id;
    private String sfId;
    private String autoNumberName;
    private String type;
    private String person;
    private String phone;
    private String ownerId;

    public Phone(long id, String sfId, String autoNumberName, String type, String person, String phone, String ownerId) {
        this.id = id;
        this.sfId = sfId;
        this.autoNumberName = autoNumberName;
        this.type = type;
        this.person = person;
        this.phone = phone;
        this.ownerId = ownerId;
    }

    public Phone() {
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

    public String getAutoNumberName() {
        return autoNumberName;
    }

    public void setAutoNumberName(String autoNumberName) {
        this.autoNumberName = autoNumberName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Phone{" + "id=" + id + ", sfId=" + sfId + ", autoNumberName=" + autoNumberName + ", type=" + type + ", person=" + person + ", phone=" + phone + ", ownerId=" + ownerId + '}';
    }
    
    
}
