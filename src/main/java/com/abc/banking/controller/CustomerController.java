package com.abc.banking.controller;

import com.abc.banking.service.CustomerService;
import com.abc.banking.exception.BusinessException;
import com.abc.banking.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private CustomerService customerService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value="/customers/{mobile}", method = RequestMethod.GET)
    public Customer findByMobile(@PathVariable("mobile") @NotNull String mobile) {
        return findByMobileNumber(mobile);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public Customer create(@RequestBody @NotNull @Valid Customer customer) {
        Customer existing = findByMobileNumber(customer.getMobile());
        if (existing != null) {
            throw new BusinessException(BusinessException.ErrorCode.DUPLICATE_CUSTOMER);
        }
        customer.setCreated(new Date());
        customer.getAddress().setCreated(customer.getCreated());
        return customerService.createCustomer(customer);
    }
    
    private Customer findByMobileNumber(String mobileNumber) {
    	return customerService.findByMobile(mobileNumber);
    	
    }
}
