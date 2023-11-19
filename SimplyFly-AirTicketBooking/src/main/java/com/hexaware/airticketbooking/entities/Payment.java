package com.hexaware.airticketbooking.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
/*
 * Author: UdayKiran and Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This class represents the entity for Payment. It defines the structure of the
 * Payment details stored in the database.
 */
@Entity
public class Payment {
	@Id
	@SequenceGenerator(name="payment_seq", initialValue=70000,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="payment_seq")
	private int referenceNumber;
	private double amountToBePaid;
	private final LocalDate paymentDate=LocalDate.now();
	private String status;
	@OneToOne
	@JoinColumn(name="ticketId")
	private Ticket ticket;
	@ManyToOne
	@JoinColumn(name="user_Id")
	private User user;
	public Payment() {
		super();
	}

	public Payment(int referenceNumber,double amountToBePaid,String status,User user) {
		super();
		this.referenceNumber = referenceNumber;
		this.amountToBePaid = amountToBePaid;
		this.status=status;
		this.user=user;
	
	}
	public int getReferenceNumber() {
		return referenceNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setReferenceNumber(int referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	
	public double getAmountToBePaid() {
		return amountToBePaid;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAmountToBePaid(double amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}

	@Override
	public String toString() {
		return "Payment [referenceNumber=" + referenceNumber + ", amountToBePaid=" + amountToBePaid + ", paymentDate="
				+ paymentDate + ", status=" + status + ", ticket=" + ticket + "]";
	}

	
}
