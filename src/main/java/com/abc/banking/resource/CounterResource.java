package com.abc.banking.resource;

import com.abc.banking.dao.CounterDao;
import com.abc.banking.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Srinivasa
 */
@Controller
public class CounterResource {

    @Autowired
    private CounterDao counterDao;

    @RequestMapping(value = "/counters", method = RequestMethod.GET)
    @ResponseBody
    public List<Counter> getAll() {
        List<Counter> counters = new ArrayList<>();
        counterDao.findAll().forEach(counters::add);
        return counters;
    }

    public synchronized void incrementQueueSize(long counterId) {
        Counter counter = counterDao.findOne(counterId);
        counter.setQueueSize(counter.getQueueSize() + 1);
    }

    public synchronized void decrmentQueueSize(long counterId) {
        Counter counter = counterDao.findOne(counterId);
        counter.setQueueSize(counter.getQueueSize() - 1);
    }
}
