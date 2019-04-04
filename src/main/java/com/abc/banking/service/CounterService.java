package com.abc.banking.service;

import com.abc.banking.dao.CounterDao;
import com.abc.banking.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Srinivasa
 */
@Service
public class CounterService {

    @Autowired
    private CounterDao counterDao;
    public List<Counter> getAllCounters() {
        List<Counter> counters = new ArrayList<>();
        counterDao.findAll().forEach(counters::add);
        return counters;
    }

    public synchronized void incrementQueueSize(long counterId) {
        Optional<Counter> counter = counterDao.findById(counterId);
        counter.get().setQueueSize(counter.get().getQueueSize() + 1);
    }

    public synchronized void decrmentQueueSize(long counterId) {
        Optional<Counter> counter = counterDao.findById(counterId);
        counter.get().setQueueSize(counter.get().getQueueSize() - 1);
    }
}
