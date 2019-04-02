package com.abc.banking.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author Srinivasa
 */
@Entity
@Table(name = "token")
public class Token {

    public enum StatusCode {
        ACTIVE,
        CANCELLED,
        COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int number;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "token")
    private List<TokenServiceMapping> tokenServices;

    @ManyToOne
    @JoinColumn(name = "current_counter_id")
    private Counter currentCounter;

    @ManyToOne
    @JoinColumn(name = "current_service_id")
    private ServiceEntity currentService;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusCode statusCode = StatusCode.ACTIVE;

    @NotNull
    private Date created;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<TokenServiceMapping> getTokenServices() {
		return tokenServices;
	}

	public void setTokenServices(List<TokenServiceMapping> tokenServices) {
		this.tokenServices = tokenServices;
	}

	public Counter getCurrentCounter() {
		return currentCounter;
	}

	public void setCurrentCounter(Counter currentCounter) {
		this.currentCounter = currentCounter;
	}

	public ServiceEntity getCurrentService() {
		return currentService;
	}

	public void setCurrentService(ServiceEntity currentService) {
		this.currentService = currentService;
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
    

}
