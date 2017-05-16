package com.ccpoc.ccpoc.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ccpoc.ccpoc.model.Person;
import com.ccpoc.ccpoc.model.Phone;
import com.ccpoc.ccpoc.service.PersonService;
import com.ccpoc.ccpoc.service.PhoneService;
 
@RestController
@RequestMapping("/phone")
public class PhoneRestController {
 
    @Autowired
    PhoneService phoneService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/allPhones", method = RequestMethod.GET)
    public ResponseEntity<List<Phone>> listAllUsers() {
        List<Phone> phones = phoneService.findAllPhones();
        if(phones.isEmpty()){
            return new ResponseEntity<List<Phone>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        System.out.println("controller phones "+phones);
        return new ResponseEntity<List<Phone>>(phones, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single Phone--------------------------------------------------------
     
    @RequestMapping(value = "/getPhone/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Phone> getPhone(@PathVariable("id") Integer id) {
        System.out.println("Fetching Phone with id " + id);
        Phone user = phoneService.findById(id);
        if (user == null) {
            System.out.println("Person with id " + id + " not found");
            return new ResponseEntity<Phone>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Phone>(user, HttpStatus.OK);
    }
 
     
     
    //-------------------Update a Phone--------------------------------------------------------
    @RequestMapping(value = "/updatePhone", method = RequestMethod.POST)
    public ResponseEntity<Void> updatePhone(@RequestBody Phone phone, UriComponentsBuilder ucBuilder) {
        System.out.println("Updating Phone " + phone.getAutoNumberName());
        phoneService.updatePhone(phone);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
 
    
     
   /* //------------------- Update a Person --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Person> updateUser(@PathVariable("id") long id, @RequestBody Person user) {
        System.out.println("Updating User " + id);
         
        Person currentUser = userService.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setUsername(user.getUsername());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
         
        userService.updateUser(currentUser);
        return new ResponseEntity<Person>(currentUser, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a Person --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        Person user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
 
        userService.deleteUserById(id);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All Users --------------------------------------------------------
     
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        userService.deleteAllUsers();
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }
 */
}