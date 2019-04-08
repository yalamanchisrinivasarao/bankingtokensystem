package com.abc.banking.model;


import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Srinivasa
 */
@Entity
@Table(name = "service_counter_mapping")
public class ServiceCounterMapping implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "counter_id")
    private Counter counter;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Customer.Type type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ServiceEntity getService() {
		return service;
	}

	public void setService(ServiceEntity service) {
		this.service = service;
	}

	public Counter getCounter() {
		return counter;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public Customer.Type getType() {
		return type;
	}

	public void setType(Customer.Type type) {
		this.type = type;
	}
    
}
