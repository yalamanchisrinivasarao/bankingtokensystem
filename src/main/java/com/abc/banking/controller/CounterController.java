package com.abc.banking.controller;

import com.abc.banking.service.CounterService;
import com.abc.banking.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Srinivasa
 */
@RestController
public class CounterController {

    @Autowired
    private CounterService counterservice;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/counters", method = RequestMethod.GET)
    public List<Counter> getAll() {
        List<Counter> counters = new ArrayList<>();
        counterservice.getAllCounters().forEach(counters::add);
        return counters;
    }

}
