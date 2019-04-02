package com.abc.banking.service;

import com.abc.banking.dao.CustomerDao;
import com.abc.banking.exception.BusinessException;
import com.abc.banking.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * @author Srinivasa
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public Customer findByMobile(String mobile) {
        return customerDao.findByMobile(mobile);
    }

    public Customer createCustomer(Customer customer) {
        Customer existing = customerDao.findByMobile(customer.getMobile());
        if (existing != null) {
            throw new BusinessException(BusinessException.ErrorCode.DUPLICATE_CUSTOMER);
        }
        customer.setCreated(new Date());
        customer.getAddress().setName(customer.getName());
        customer.getAddress().setCreated(new Date());
        return customerDao.save(customer);
    }
}
