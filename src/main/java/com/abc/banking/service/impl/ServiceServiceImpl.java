package com.abc.banking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.banking.dao.ServiceDao;
import com.abc.banking.model.ServiceEntity;
import com.abc.banking.service.ServiceService;

/**
 * @author Srinivasa
 */
@Service
public class ServiceServiceImpl implements ServiceService {

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

    public Optional<ServiceEntity> findById(long id) {
        return serviceDao.findById(id);
    }
}
