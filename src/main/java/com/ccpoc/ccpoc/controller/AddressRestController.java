package com.ccpoc.ccpoc.controller;
 
import com.ccpoc.ccpoc.model.Address;
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
import com.ccpoc.ccpoc.service.AddressService;
import com.ccpoc.ccpoc.service.PersonService;
 
@RestController
@RequestMapping("/address")
public class AddressRestController {
 
    @Autowired
    AddressService addressService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All Address--------------------------------------------------------
     
    @RequestMapping(value = "/allAddress", method = RequestMethod.GET)
    public ResponseEntity<List<Address>> listAllAddresses() {
        List<Address> addressList = addressService.findAllAddress();
        if(addressList.isEmpty()){
            return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        System.out.println("controller Address "+addressList);
        return new ResponseEntity<List<Address>>(addressList, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single Person--------------------------------------------------------
     
    @RequestMapping(value = "/getAddress/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> getUser(@PathVariable("id") Integer id) {
        System.out.println("Fetching Address with id " + id);
        Address address = addressService.findById(id);
        if (address == null) {
            System.out.println("address with id " + id + " not found");
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Address record"+address);
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }
 
     
     
    //-------------------Update a Person--------------------------------------------------------
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Address address,    UriComponentsBuilder ucBuilder) {
        System.out.println("Updating address " + address.getAutoNumberName());
        addressService.updateAddress(address);
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