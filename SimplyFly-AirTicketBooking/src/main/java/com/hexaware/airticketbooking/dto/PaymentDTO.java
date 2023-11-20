package com.hexaware.airticketbooking.dto;

import java.time.LocalDate;

/*
 * Author: Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This is DTO class for Payment details. It is completely used for data transfer between controller and service layers
 */
public class PaymentDTO {
	
	private int referenceNumber;
	
    private double amountPaid;
	
	private LocalDate paymentDate;
	private String status;
	
	public PaymentDTO() {
		super();
	}


	public PaymentDTO(int referenceNumber, double amountPaid,LocalDate paymentDate,String status) {
		super();
		this.referenceNumber = referenceNumber;
		this.amountPaid=amountPaid;
		this.paymentDate=paymentDate;
		this.status=status;
	}


	public int getReferenceNumber() {
		return referenceNumber;
	}


	public void setReferenceNumber(int referenceNumber) {
		this.referenceNumber = referenceNumber;
	}


	
	public double getAmountPaid() {
		return amountPaid;
	}


	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDate getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}


	
}
