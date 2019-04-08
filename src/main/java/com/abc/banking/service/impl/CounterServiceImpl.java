package com.abc.banking.service.impl;

import com.abc.banking.dao.CounterDao;
import com.abc.banking.model.Counter;
import com.abc.banking.service.CounterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Srinivasa
 */
@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterDao counterDao;
    public List<Counter> getAllCounters() {
        List<Counter> counters = new ArrayList<>();
        counterDao.findAll().forEach(counters::add);
        return counters;
    }

    public void incrementQueueSize(long counterId) {
        Optional<Counter> counter = counterDao.findById(counterId);
        synchronized(this) 
        {
        	counter.get().setQueueSize(counter.get().getQueueSize() + 1);
        }
    }

    public void decrmentQueueSize(long counterId) {
        Optional<Counter> counter = counterDao.findById(counterId);
        synchronized(this)
        {
        	counter.get().setQueueSize(counter.get().getQueueSize() - 1);
        }
    }
}
