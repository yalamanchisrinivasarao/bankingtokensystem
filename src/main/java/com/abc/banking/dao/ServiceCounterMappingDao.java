package com.abc.banking.dao;

import com.abc.banking.model.ServiceCounterMapping;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 *
 * @author Srinivasa
 */
@Transactional
public interface ServiceCounterMappingDao extends CrudRepository<ServiceCounterMapping, Long> {

}
