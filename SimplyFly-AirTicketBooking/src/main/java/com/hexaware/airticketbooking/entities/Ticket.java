package com.hexaware.airticketbooking.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
/*
 * Author: UdayKIran
 * LastModifiedDate:19-11-2023
 * Description: This class represents the entity for Ticket. It defines the structure of the
 * Ticket objects stored in the database.
 */
@Entity
@Table(name="ticketdetails")
public class Ticket {
	@Id
	@SequenceGenerator(name="ticket_seq",initialValue=1000,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ticket_seq")
	private int ticketId;
	private LocalDate travelDate;
	private String email;
	private double totalAmount;
	private int numberOfPassengers;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	@ManyToOne
	@JoinColumn(name="flightId")
	private Flight flight;
	@OneToMany(mappedBy="ticket",cascade=CascadeType.ALL)
	private List<Passenger> passenger=new ArrayList<>();
	@OneToOne(mappedBy="ticket",cascade=CascadeType.ALL)
	private Payment payment;
	
	public Ticket() {
		super();
	}
	public Ticket(int ticketId, LocalDate travelDate, String email, double totalAmount, int numberOfPassengers,
			User user, Flight flight, List<Passenger> passenger) {
		super();
		this.ticketId = ticketId;
		this.travelDate = travelDate;
		this.email = email;
		this.totalAmount = totalAmount;
		this.numberOfPassengers = numberOfPassengers;
		this.user = user;
		this.flight = flight;
		this.passenger = passenger;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public List<Passenger> getPassenger() {
		return passenger;
	}
	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}
	public void addPassenger(Passenger passenger) {

				
		passenger.setTicket(this); 

				List<Passenger> list = getPassenger();

				list.add(passenger);

			}
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", travelDate=" + travelDate + ", email=" + email + ", totalAmount="
				+ totalAmount + ", numberOfPassengers=" + numberOfPassengers + ", user=" + user + ", flight=" + flight
				+ ", passenger=" + passenger + "]";
	}

	
	
}