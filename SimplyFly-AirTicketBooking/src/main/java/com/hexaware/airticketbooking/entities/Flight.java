package com.hexaware.airticketbooking.entities;
import java.time.LocalTime;
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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
/*
 * Author: UdayKIran
 * LastModifiedDate:19-11-2023
 * Description: This class represents the entity for Flights. It defines the structure of the
 * Flight objects stored in the database.
 */
@Entity
@Table(name="flightdetails")
public class Flight {

	@Id
	@SequenceGenerator(name="flight_seq",initialValue=4000,allocationSize=1)

	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="flight_seq")
	private int flightId;
	private String flightName;
	private String typeOfFlight;
	private String source;
	private String destination;
	private LocalTime timeOfArrival;
	private LocalTime timeOfDeparture;
	private String duration;
	private double fare;
	private int numberOfSeats;
	@ManyToOne
	@JoinColumn(name = "flightOwnerId")
	private FlightOwner flightOwner;
	@OneToMany(mappedBy = "flight",cascade = CascadeType.ALL)
	private List<Ticket> ticket =new ArrayList<>();
	public Flight() {
		super();
	}
	
    
	public Flight(int flightId, String flightName, String typeOfFlight, String source, String destination,
			LocalTime timeOfArrival, LocalTime timeOfDeparture, String duration, double fare, int numberOfSeats,
			FlightOwner flightOwner, List<Ticket> ticket) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.typeOfFlight = typeOfFlight;
		this.source = source;
		this.destination = destination;
		this.timeOfArrival = timeOfArrival;
		this.timeOfDeparture = timeOfDeparture;
		this.duration = duration;
		this.fare = fare;
		this.numberOfSeats = numberOfSeats;
		this.flightOwner = flightOwner;
		this.ticket = ticket;
	}


	public int getFlightId() {
		return flightId;
	}


	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}


	public String getFlightName() {
		return flightName;
	}


	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}


	public String getTypeOfFlight() {
		return typeOfFlight;
	}


	public void setTypeOfFlight(String typeOfFlight) {
		this.typeOfFlight = typeOfFlight;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public LocalTime getTimeOfArrival() {
		return timeOfArrival;
	}


	public void setTimeOfArrival(LocalTime timeOfArrival) {
		this.timeOfArrival = timeOfArrival;
	}


	public LocalTime getTimeOfDeparture() {
		return timeOfDeparture;
	}


	public void setTimeOfDeparture(LocalTime timeOfDeparture) {
		this.timeOfDeparture = timeOfDeparture;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public double getFare() {
		return fare;
	}


	public void setFare(double fare) {
		this.fare = fare;
	}


	public int getNumberOfSeats() {
		return numberOfSeats;
	}


	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}


	public FlightOwner getFlightOwner() {
		return flightOwner;
	}


	public void setFlightOwner(FlightOwner flightOwner) {
		this.flightOwner = flightOwner;
	}


	public List<Ticket> getTicket() {
		return ticket;
	}


	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}


	public void addTicket(Ticket ticket) {

				
		        ticket.setFlight(this); 

				List<Ticket> list = getTicket();

				list.add(ticket);

			}
	
}