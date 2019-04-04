package com.abc.banking.business.counterallocator.impl;

import com.abc.banking.business.counterallocator.CounterAllocator;
import com.abc.banking.dao.CounterDao;
import com.abc.banking.dao.ServiceCounterMappingDao;
import com.abc.banking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Srinivasa.
 */
@Component
public class CustomerPriorityAndQueueBasedCounterAllocator implements CounterAllocator {

    private Map<String, List<Counter>> premiumServiceCounters = new HashMap<>();
    private Map<String, List<Counter>> normalServiceCounters = new HashMap<>();

    @Autowired
    private ServiceCounterMappingDao serviceCounterMappingDao;

    @Autowired
    private CounterDao counterDao;

    @PostConstruct
    public void init() {
        serviceCounterMappingDao.findAll().forEach(scm -> {
            if (scm.getType().equals(Customer.Type.PREMIUM)) {
                addServiceCounterMapping(scm, premiumServiceCounters);
            } else {
                addServiceCounterMapping(scm, normalServiceCounters);
            }
        });
    }

    @Override
    public Counter allocate(ServiceEntity service, Customer customer) {
        List<Counter> counters = getServiceCountersBasedOnCustomerPriority(service, customer);
        if (!counters.isEmpty()) {
            // Allocate counter with minimum queue size
            int minQueueSize = Integer.MAX_VALUE;
            Counter allocatedCounter = null;
            for (Counter counter : counters) {
                int queueSize = counterDao.findById(counter.getId()).get().getQueueSize();
                if (queueSize < minQueueSize) {
                    minQueueSize = queueSize;
                    allocatedCounter = counter;
                }
            }
            return allocatedCounter;
        }
        return null;
    }

    private List<Counter> getServiceCountersBasedOnCustomerPriority(ServiceEntity service, Customer customer) {
        List<Counter> counters = Collections.emptyList();
        switch (customer.getType()) {
            case PREMIUM:
                counters = premiumServiceCounters.get(service.getName());
                break;
            case REGULAR:
                counters = normalServiceCounters.get(service.getName());
                break;
        }
        return counters;
    }

    private void addServiceCounterMapping(ServiceCounterMapping scm, Map<String, List<Counter>> serviceCounters) {
        String serviceName = scm.getService().getName();
        List<Counter> counters = serviceCounters.get(serviceName);
        if (counters == null) {
            counters = new ArrayList<>();
            serviceCounters.put(serviceName, counters);
        }
        counters.add(scm.getCounter());
    }
}
