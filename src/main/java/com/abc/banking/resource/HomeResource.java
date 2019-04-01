package com.abc.banking.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeResource {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Turvo Banking Token System :)";
    }

}
