package com.abc.banking.service;

import com.abc.banking.model.Counter;
import java.util.List;

/**
 * @author Srinivasa
 */
public interface CounterService {

    public List<Counter> getAllCounters();
    
    public void incrementQueueSize(long counterId);

    public void decrmentQueueSize(long counterId);
}
