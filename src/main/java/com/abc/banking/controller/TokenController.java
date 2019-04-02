package com.abc.banking.controller;

import com.abc.banking.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author Srinivasa
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;


    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    public Map<Integer, List<Integer>> activeTokens() {
        return tokenService.getAllActiveTokens();
    }

    @RequestMapping(value = "/tokens", method = RequestMethod.POST)
    @Transactional
    public Integer create(@RequestBody @NotNull @Valid TokenRequest tokenRequest) {
    	return tokenService.createToken(tokenRequest);    
    }

    @RequestMapping(value = "/tokens/{tokenNumber}/comment", method = RequestMethod.PUT)
    @Transactional
    public void comment(@PathVariable("tokenNumber") @NotNull Integer tokenNumber, @RequestBody String comments) {
    	tokenService.createTokenComment(tokenNumber, comments);
    }

    @RequestMapping(value = "/tokens/{tokenNumber}/cancel", method = RequestMethod.PUT)
    @Transactional
    public void cancel(@PathVariable("tokenNumber") @NotNull Integer tokenNumber) {
    	tokenService.markTokenAsCancel(tokenNumber);
    }

    @RequestMapping(value = "/tokens/{tokenNumber}/complete", method = RequestMethod.PUT)
    @Transactional
    public void complete(@PathVariable("tokenNumber") @NotNull Integer tokenNumber) {
    	tokenService.markTokenAsComplete(tokenNumber);    
    }

    public static class TokenRequest {
        @NotNull
        private String customerMobile;

        @NotNull
        private List<String> services;

		public String getCustomerMobile() {
			return customerMobile;
		}

		public void setCustomerMobile(String customerMobile) {
			this.customerMobile = customerMobile;
		}

		public List<String> getServices() {
			return services;
		}

		public void setServices(List<String> services) {
			this.services = services;
		}
        
    }
}
