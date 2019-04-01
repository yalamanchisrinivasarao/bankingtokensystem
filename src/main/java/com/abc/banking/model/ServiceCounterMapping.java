package com.abc.banking.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Srinivasa
 */
@Entity
@Table(name = "service_counter_mapping")
public class ServiceCounterMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
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
