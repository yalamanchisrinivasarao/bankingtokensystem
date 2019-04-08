package com.abc.banking.model;


import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Srinivasa
 */
@Entity
@Table(name = "token_service_mapping")
public class TokenServiceMapping implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenServiceMapping() {
    }

    public TokenServiceMapping(Token token, ServiceEntity service) {
        this.service = service;
        this.token = token;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "token_id")
    private Token token;

    private String comments;

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

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
    
}
