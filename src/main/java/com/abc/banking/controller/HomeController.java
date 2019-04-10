package com.abc.banking.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping("/")
    public String index() {
    	return  String.format("%s\n%s\n%s\n%s",
    			"Welcome to the Turvo Banking System." ,
    		    "For available Counters please click " + "<a href=\"http://localhost:8080/counters\">Counters</a>" ,
    		    "For available services please click " + "<a href=\"http://localhost:8080/services\">Services</a>" ,
    		    "Thank You");
    }

}
