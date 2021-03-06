package com.abc.banking.service.impl;

import com.abc.banking.dao.CustomerDao;
import com.abc.banking.exception.BusinessException;
import com.abc.banking.model.Customer;
import com.abc.banking.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * @author Srinivasa
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public Customer findByMobile(String mobile) {
        return findCustomerByMobile(mobile);
    }

    public Customer createCustomer(Customer customer) {
        Customer existing = findCustomerByMobile(customer.getMobile());
        if (existing != null) {
            throw new BusinessException(BusinessException.ErrorCode.DUPLICATE_CUSTOMER);
        }
        customer.setCreated(new Date());
        customer.getAddress().setCreated(customer.getCreated());
        return customerDao.save(customer);
    }
    
    private Customer findCustomerByMobile(String mobile)
    {
    	return customerDao.findByMobile(mobile);
    }
}
