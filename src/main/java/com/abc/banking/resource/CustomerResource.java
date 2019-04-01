package com.abc.banking.resource;

import com.abc.banking.dao.CustomerDao;
import com.abc.banking.exception.BusinessException;
import com.abc.banking.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Srinivasa
 */
@Controller
public class CustomerResource {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping("/customers/{mobile}")
    @ResponseBody
    public Customer findByMobile(@PathVariable("mobile") @NotNull String mobile) {
        return customerDao.findByMobile(mobile);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @ResponseBody
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
