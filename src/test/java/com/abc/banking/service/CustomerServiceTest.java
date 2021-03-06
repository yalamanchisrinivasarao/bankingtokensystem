package com.abc.banking.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.abc.banking.AbstractTest;
import com.abc.banking.dao.CustomerDao;
import com.abc.banking.model.Address;
import com.abc.banking.model.Customer;
import com.abc.banking.service.impl.CustomerServiceImpl;
 
 
public class CustomerServiceTest extends AbstractTest {
     
    @InjectMocks
    CustomerServiceImpl customerService;
     
    @Mock
    CustomerDao customerDao;
 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
     
     
    @Test    
    public void testFindByMobile()
    {
    	Customer daoCustomer = new Customer();
		
		daoCustomer.setName("Srinivasa Yalamanchi12");
		daoCustomer.setMobile("8669083912");
		
		when(customerDao.findByMobile("8669083912")).thenReturn(daoCustomer);
         
        Customer serviceCustomer = customerService.findByMobile("8669083912");
         
        assertEquals("8669083912", serviceCustomer.getMobile());
        assertEquals("Srinivasa Yalamanchi12", serviceCustomer.getName());
    }
     
    @Test
    public void testCreateCustomer()
    {

        Address address = new Address();
        address.setAddressLine1("Near Jaiswal Hospital");
        address.setAddressLine2("Moti Nagar");
        address.setCity("Hyderabad");
        address.setCreated(new Date());
        address.setState("Telanagana");
        address.setZipCode("500018");
        Customer customer = new Customer();
        customer.setName("Srinivasa Yalamanchi16");
        customer.setAddress(address);
        customer.setMobile("8669083916");
        customer.setCreated(address.getCreated());
        
         
        customerService.createCustomer(customer);
         
        verify(customerDao, times(1)).save(customer);
    }
}
