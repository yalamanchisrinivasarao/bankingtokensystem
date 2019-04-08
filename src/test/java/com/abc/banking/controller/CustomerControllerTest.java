package com.abc.banking.controller;

import com.abc.banking.AbstractTest;
import com.abc.banking.model.Address;
import com.abc.banking.model.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class CustomerControllerTest extends AbstractTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }

   @Test
   public void testFindByMobile() throws Exception {
      String uri = "/customers/8669083911";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Customer customer = super.mapFromJson(content, Customer.class);
      assertTrue(customer.getId() > 0);
   }
   

   @Test
   public void testCreate() throws Exception {
      String uri = "/customers";
      
      Address address = new Address();
      address.setAddressLine1("Near Jaiswal Hospital");
      address.setAddressLine2("Moti Nagar");
      address.setCity("Hyderabad");
      address.setCreated(new Date());
      address.setState("Telanagana");
      address.setZipCode("500018");
      Customer customer = new Customer();
      customer.setName("Srinivasa Yalamanchi12");
      customer.setAddress(address);
      customer.setMobile("8669083912");
      customer.setCreated(address.getCreated());
      String inputJson = super.mapToJson(customer);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Customer createdCustomer = super.mapFromJson(content, Customer.class);
      assertTrue((createdCustomer.getMobile().equals("8669083912")));
   }
}
