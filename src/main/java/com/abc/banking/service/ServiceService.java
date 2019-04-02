package com.abc.banking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.banking.dao.ServiceDao;
import com.abc.banking.model.ServiceEntity;

/**
 * @author Srinivasa
 */
@Service
public class ServiceService {

    @Autowired
    private ServiceDao serviceDao;

    public List<ServiceEntity> getAllServices() {
        List<ServiceEntity> services = new ArrayList<>();
        serviceDao.findAll().forEach(services::add);
        return services;
    }

    public ServiceEntity findByName(String name) {
        return serviceDao.findByName(name);
    }

    public ServiceEntity findById(long id) {
        return serviceDao.findOne(id);
    }
}
