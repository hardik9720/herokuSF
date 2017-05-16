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
import com.ccpoc.ccpoc.service.PersonService;
 
@RestController
@RequestMapping("/person")
public class HelloWorldRestController {
 
    @Autowired
    PersonService personService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> listAllUsers() {
        List<Person> users = personService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        System.out.println("controller users "+users);
        return new ResponseEntity<List<Person>>(users, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single Person--------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getUser(@PathVariable("id") Integer id) {
        System.out.println("Fetching User with id " + id);
        Person user = personService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(user, HttpStatus.OK);
    }
 
     
     
    //-------------------Update a Person--------------------------------------------------------
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Person user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Updating User " + user.getAutoNumberName());
        personService.updatePerson(user);
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