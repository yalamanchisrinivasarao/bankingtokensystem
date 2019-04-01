package com.abc.banking.controller;

import com.abc.banking.dao.CustomerDao;
import com.abc.banking.exception.BusinessException;
import com.abc.banking.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Srinivasa
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping("/customers/{mobile}")
    public Customer findByMobile(@PathVariable("mobile") @NotNull String mobile) {
        return customerDao.findByMobile(mobile);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public Customer create(@RequestBody @NotNull @Valid Customer customer) {
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
