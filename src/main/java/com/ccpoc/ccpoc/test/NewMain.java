/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccpoc.ccpoc.test;

import com.ccpoc.ccpoc.model.Person;
import com.ccpoc.ccpoc.service.PersonServiceImpl;
import java.net.URISyntaxException;
import java.util.List;

/**
 *
 * @author Hardik
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws URISyntaxException {
        // TODO code application logic here
        System.out.println("main method is  called");
       /* PersonServiceImpl personObj = new PersonServiceImpl();
        List<Person> personList = personObj.findAllUsers();
        System.out.println("personList " + personList);
       */        
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println("utilDate:" + utilDate);
        System.out.println("sqlDate:" + sqlDate);

    }

}
