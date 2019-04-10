package com.abc.banking.controller;

import com.abc.banking.dto.TokenRequest;
import com.abc.banking.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    public Map<Integer, List<Integer>> activeTokens() {
        return tokenService.getAllActiveTokens();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/tokens", method = RequestMethod.POST)
    public Integer create(@RequestBody @NotNull @Valid TokenRequest tokenRequest) {
    	return tokenService.createToken(tokenRequest);    
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/tokens/{tokenNumber}/comment", method = RequestMethod.PUT)
    public void comment(@PathVariable("tokenNumber") @NotNull int tokenNumber, @RequestBody String comments) {
    	tokenService.createTokenComment(tokenNumber, comments);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/tokens/{tokenNumber}/cancel", method = RequestMethod.PUT)
    public void cancel(@PathVariable("tokenNumber") @NotNull int tokenNumber) {
    	tokenService.markTokenAsCancel(tokenNumber);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/tokens/{tokenNumber}/complete", method = RequestMethod.PUT)
    public void complete(@PathVariable("tokenNumber") @NotNull int tokenNumber) {
    	tokenService.markTokenAsComplete(tokenNumber);    
    }

}
