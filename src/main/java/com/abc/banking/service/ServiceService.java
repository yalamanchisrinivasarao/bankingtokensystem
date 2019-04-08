package com.abc.banking.service;

import java.util.List;
import java.util.Optional;

import com.abc.banking.model.ServiceEntity;

/**
 * @author Srinivasa
 */
public interface ServiceService {

    public List<ServiceEntity> getAllServices();

    public ServiceEntity findByName(String name);

    public Optional<ServiceEntity> findById(long id);
}
