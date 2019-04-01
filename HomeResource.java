package com.abc.banking.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @RequestMapping("/")
    public String index() {
        return "Turvo Banking Token System :)";
    }

}
