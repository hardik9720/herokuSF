package com.ccpoc.ccpoc.service;

import java.util.List;

import com.ccpoc.ccpoc.model.Person;

public interface PersonService {
    
    List<Person> findAllUsers();
    Person findById(Integer Id);
    void updatePerson(Person personObj);
}
