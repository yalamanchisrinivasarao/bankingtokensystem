package com.abc.banking.controller;

import com.abc.banking.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    @Secured({"USER", "ADMIN"})
    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    public Map<Integer, List<Integer>> activeTokens() {
        return tokenService.getAllActiveTokens();
    }

    @Secured({"USER", "ADMIN"})
    @RequestMapping(value = "/tokens", method = RequestMethod.POST)
    @Transactional
    public Integer create(@RequestBody @NotNull @Valid TokenRequest tokenRequest) {
    	return tokenService.createToken(tokenRequest);    
    }

    @Secured({"USER", "ADMIN"})
    @RequestMapping(value = "/tokens/{tokenNumber}/comment", method = RequestMethod.PUT)
    @Transactional
    public void comment(@PathVariable("tokenNumber") @NotNull Integer tokenNumber, @RequestBody String comments) {
    	tokenService.createTokenComment(tokenNumber, comments);
    }

    @Secured("ADMIN")
    @RequestMapping(value = "/tokens/{tokenNumber}/cancel", method = RequestMethod.PUT)
    @Transactional
    public String cancel(@PathVariable("tokenNumber") @NotNull Integer tokenNumber) {
    	return tokenService.markTokenAsCancel(tokenNumber);
    }

    @Secured("ADMIN")
    @RequestMapping(value = "/tokens/{tokenNumber}/complete", method = RequestMethod.PUT)
    @Transactional
    public String complete(@PathVariable("tokenNumber") @NotNull Integer tokenNumber) {
    	return tokenService.markTokenAsComplete(tokenNumber);    
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
