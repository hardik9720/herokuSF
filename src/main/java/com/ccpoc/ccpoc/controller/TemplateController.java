/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccpoc.ccpoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Hardik Mehta
 */

@Controller
public class TemplateController {//Serves Templates.
 
    @RequestMapping(value="/person")
    public String getPersonTemplate() {
        System.out.println("template controller person view method called11");
        return "/person";   
    }
    @RequestMapping(value="/address")
    public String getAddressTemplate() {
        System.out.println("template controller address view method called1");
        return "/address";   
    }
    
    @RequestMapping(value="/phone")
    public String getPhoneTemplate() {
        System.out.println("template controller phone view method called11");
        return "/phone";   
    }
    
    @RequestMapping(value="/403")
    public String getAccessDeniedTemplate() {
        System.out.println("template controller access denied view method called11");
        return "/403";   
    }
}
