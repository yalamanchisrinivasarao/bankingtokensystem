package com.abc.banking.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Srinivasa
 */
@Entity
@Table(name = "counter")
@Data
public class Counter {

    public enum Priority {
        HIGH,
        NORMAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private int number;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "queue_size")
    private int queueSize;

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

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public int getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}
    
}