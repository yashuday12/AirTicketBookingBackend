package com.hexaware.airticketbooking.entities;
/*
 * Author: UdayKIran
 * Date:
 * Description: This is Passenger Entity Class
 */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
/*
 * Author: UdayKIran
 * LastModifiedDate:19-11-2023
 * Description: This class represents the entity for Passengers. It defines the structure of the
 * Passenger objects stored in the database.
 */
@Entity
@Table(name="passengerdetails")
public class Passenger {
	@Id
	@SequenceGenerator(name="Passenger_seq",initialValue=2000,allocationSize=1)

	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Passenger_seq")
	private int passenegerId;
 	private String passengerName;
	private int passengerAge;
	private String passengerSeatNumber;
	@ManyToOne
	@JoinColumn(name="ticket_Id")
	private Ticket ticket;
	public Passenger() {
		super();
	}
	public Passenger(int passenegerId, String passengerName, int passengerAge, String passengerSeatNumber,
			Ticket ticket) {
		super();
		this.passenegerId = passenegerId;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerSeatNumber = passengerSeatNumber;
		this.ticket = ticket;
	}
	public int getPassenegerId() {
		return passenegerId;
	}
	public void setPassenegerId(int passenegerId) {
		this.passenegerId = passenegerId;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getPassengerAge() {
		return passengerAge;
	}
	
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}
	public String getPassengerSeatNumber() {
		return passengerSeatNumber;
	}
	public void setPassengerSeatNumber(String passengerSeatNumber) {
		this.passengerSeatNumber = passengerSeatNumber;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	

	
 
}