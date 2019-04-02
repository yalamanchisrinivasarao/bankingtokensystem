package com.abc.banking.business.counterallocator;

import com.abc.banking.model.Counter;
import com.abc.banking.model.Customer;
import com.abc.banking.model.ServiceEntity;

/**
 * Created by Srinivasa.
 */
public interface CounterAllocator {

    Counter allocate(ServiceEntity service, Customer customer);

}
