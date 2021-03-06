package com.abc.banking.dao;

import static org.junit.Assert.assertEquals;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.abc.banking.AbstractTest;
import com.abc.banking.model.Address;
import com.abc.banking.model.Customer;

@RunWith(SpringRunner.class)

@Transactional

@SpringBootTest

public class CustomerDaoTest extends AbstractTest{
	
	@Autowired
	
	CustomerDao customerDao;
	
	@Test
	public void testFindByMobile() 
	{
		
		Customer customer = customerDao.findByMobile("8669083912");
        assertEquals("8669083912", customer.getMobile());
        assertEquals("Srinivasa Yalamanchi12", customer.getName());
	}
   
	@Test
	public void testSave() throws Exception {
	   
		Address address = new Address();
		address.setAddressLine1("Near Jaiswal Hospital");
		address.setAddressLine2("Moti Nagar");
		address.setCity("Hyderabad");
		address.setCreated(new Date());
		address.setState("Telanagana");
		address.setZipCode("500018");
		Customer customer = new Customer();
		customer.setName("Srinivasa Rao Yalamanchi1");
		customer.setAddress(address);
		customer.setMobile("8669083113");
		customer.setCreated(address.getCreated());
		
		Customer daoCustomer = customerDao.save(customer);		
        assertEquals("8669083113", daoCustomer.getMobile());
        assertEquals("Srinivasa Rao Yalamanchi1", daoCustomer.getName());
	}	
	
	
}