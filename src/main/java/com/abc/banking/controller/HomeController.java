package com.abc.banking.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    
    @Secured({"USER", "ADMIN"})
    @RequestMapping("/")
    public String index() {
        return "Turvo Banking Token System..";
    }

}
