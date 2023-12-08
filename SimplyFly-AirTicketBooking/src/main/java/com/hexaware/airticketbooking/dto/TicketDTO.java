package com.hexaware.airticketbooking.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
/*
 * Author: Uday Kiran
 * LastModifiedDate:19-11-2023
 * Description: This is DTO class for Ticket. It is completely used for data transfer between controller and service layers
 */
public class TicketDTO {
	
	private int ticketId;
	@Future(message = "Travel date must be in the future")
    private LocalDate travelDate;
    @Email(message = "Invalid email format")
    private String email;
    @Positive(message = "Total amount must be a positive number")
    private double totalAmount;
    @Min(value = 1, message = "Number of passengers must be at least 1")
    private int numberOfPassengers;
   
	

	public TicketDTO() {
		super();
	}


	public TicketDTO(int ticketId, LocalDate travelDate, String email, double totalAmount, int numberOfPassengers) {
		super();
		this.ticketId = ticketId;
		this.travelDate = travelDate;
		this.email = email;
		this.totalAmount = totalAmount;
		this.numberOfPassengers = numberOfPassengers;
	}



	public int getTicketId() {
		return ticketId;
	}



	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}



	public LocalDate getTravelDate() {
		return travelDate;
	}



	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public double getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}



	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}



	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}


	@Override
	public String toString() {
		return "TicketDTO [ticketId=" + ticketId + ", travelDate=" + travelDate + ", email=" + email + ", totalAmount="
				+ totalAmount + ", numberOfPassengers=" + numberOfPassengers + "]";
	}
	
	
}	