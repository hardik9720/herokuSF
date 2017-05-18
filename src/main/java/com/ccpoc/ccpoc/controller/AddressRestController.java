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
 
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deleteUser(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting Address with id " + id);
 
        Address address = addressService.findById(id);
        if (address == null) {
            System.out.println("Unable to delete. address with id " + id + " not found");
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
 
        addressService.deleteAddressById(id);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }
     
    //-------------------Update a Address--------------------------------------------------------
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Void> createUpdateAddress(@RequestBody Address address,    UriComponentsBuilder ucBuilder) {
        System.out.println("Updating address " + address.getAutoNumberName());
        
        if(address.getAutoNumberName()!= null && !address.getAutoNumberName().isEmpty())
        {
            System.out.println("Updating User " + address.getAutoNumberName());
            addressService.updateAddress(address);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
}