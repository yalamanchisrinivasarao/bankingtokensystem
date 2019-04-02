package com.abc.banking.service;

import com.abc.banking.business.counterallocator.CounterAllocator;
import com.abc.banking.business.sequencegenerator.SequenceGenerator;
import com.abc.banking.controller.TokenController.TokenRequest;
import com.abc.banking.dao.TokenDao;
import com.abc.banking.exception.BusinessException;
import com.abc.banking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Srinivasa
 */
@Service
public class TokenService {

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CounterService counterService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private CounterAllocator counterAllocator;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    public Map<Integer, List<Integer>> getAllActiveTokens() {
        List<Token> activeTokens = tokenDao.findByStatusCode(Token.StatusCode.ACTIVE);
        Map<Integer, List<Integer>> counterToTokens =
                activeTokens.stream()
                        .collect(Collectors.groupingBy(t -> t.getCurrentCounter().getNumber(),
                                Collectors.mapping(Token::getNumber, Collectors.toList())));
        return counterToTokens;
    }

    public Integer createToken(TokenRequest tokenRequest) {
        Customer customer = customerService.findByMobile(tokenRequest.getCustomerMobile());
        if (customer == null) {
            throw new BusinessException(BusinessException.ErrorCode.CUSTOMER_NOT_FOUND);
        }
        Token token = new Token();
        token.setNumber(sequenceGenerator.generate());
        List<TokenServiceMapping> tokenServices = new ArrayList<>();
        for (String s : tokenRequest.getServices()) {
            ServiceEntity service = serviceService.findByName(s);
            if (service == null) {
                throw new BusinessException(BusinessException.ErrorCode.SERVICE_NOT_FOUND);
            }
            tokenServices.add(new TokenServiceMapping(token, service));
            while (service.getNextServiceId() != null) {
                ServiceEntity nextService = serviceService.findById(service.getNextServiceId());
                tokenServices.add(new TokenServiceMapping(token, nextService));
                service = nextService;
            }
        }
        token.setTokenServices(tokenServices);
        token.setCreated(new Date());
        token.setStatusCode(Token.StatusCode.ACTIVE);
        token.setCustomer(customer);
        token.setCurrentService(tokenServices.get(0).getService());
        Counter counter = counterAllocator.allocate(tokenServices.get(0).getService(), customer);
        counterService.incrementQueueSize(counter.getId());
        token.setCurrentCounter(counter);
        token = tokenDao.save(token);
        return token.getNumber();
    }


    public void createTokenComment(Integer tokenNumber, String comments) {
        Token token = checkTokenValidity(tokenNumber);
        TokenServiceMapping current =
                token.getTokenServices()
                        .stream()
                        .filter(tsm -> tsm.getService().getId() == token.getCurrentService().getId())
                        .findFirst().get();
        current.setComments(comments);
    }

    
    public void markTokenAsCancel(Integer tokenNumber) {
        Token token = checkTokenValidity(tokenNumber);
        Counter counter = token.getCurrentCounter();
        counterService.decrmentQueueSize(counter.getId());
        token.setStatusCode(Token.StatusCode.CANCELLED);
    }


    public void markTokenAsComplete(Integer tokenNumber) {
        Token token = checkTokenValidity(tokenNumber);
        Counter counter = token.getCurrentCounter();
        counterService.decrmentQueueSize(counter.getId());

        TokenServiceMapping next = null;
        Iterator<TokenServiceMapping> i = token.getTokenServices().iterator();
        while (i.hasNext()) {
            TokenServiceMapping tsm = i.next();
            if (tsm.getService().getId() == token.getCurrentService().getId() && i.hasNext()) {
                next = i.next();
                break;
            }
        }
        if (next != null) {
            token.setCurrentService(next.getService());
            Counter nextCounter = counterAllocator.allocate(next.getService(), token.getCustomer());
            counter.setQueueSize(nextCounter.getQueueSize() + 1);
            token.setCurrentCounter(nextCounter);
        } else {
            token.setStatusCode(Token.StatusCode.COMPLETED);
        }
    }

    private Token checkTokenValidity(@PathVariable("number") @NotNull Integer tokenNumber) {
        Token token = tokenDao.findByNumber(tokenNumber);
        if (token == null) {
            throw new BusinessException(BusinessException.ErrorCode.INVALID_TOKEN);
        } else if (!Token.StatusCode.ACTIVE.equals(token.getStatusCode())) {
            throw new BusinessException(BusinessException.ErrorCode.INVALID_TOKEN_STATE);
        }
        return token;
    }

}
