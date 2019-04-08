package com.abc.banking.service;

import com.abc.banking.model.Customer;

/**
 * @author Srinivasa
 */
public interface CustomerService {

    public Customer findByMobile(String mobile);

    public Customer createCustomer(Customer customer);
}
