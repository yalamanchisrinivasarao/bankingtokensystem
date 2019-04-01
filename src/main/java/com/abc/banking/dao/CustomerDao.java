package com.abc.banking.dao;

import com.abc.banking.model.Customer;
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
public interface CustomerDao extends CrudRepository<Customer, Long> {

    /**
     * Return the customer having the passed mobile or null if no mobile is found.
     *
     * @param mobile the customer mobile.
     */
    public Customer findByMobile(String mobile);

}
