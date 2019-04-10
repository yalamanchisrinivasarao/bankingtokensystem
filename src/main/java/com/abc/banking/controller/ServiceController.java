package com.abc.banking.controller;

import com.abc.banking.service.ServiceService;
import com.abc.banking.model.ServiceEntity;
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
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public List<ServiceEntity> getAll() {
        List<ServiceEntity> services = new ArrayList<>();
        serviceService.getAllServices().forEach(services::add);
        return services;
    }

}
