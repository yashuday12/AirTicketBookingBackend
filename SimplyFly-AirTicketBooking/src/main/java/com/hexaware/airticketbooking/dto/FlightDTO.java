package com.hexaware.airticketbooking.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
/*
 * Author: Uday Kiran
 * LastModifiedDate:19-11-2023
 * Description: This is DTO class for Flight. It is completely used for data transfer between controller and service layers
 */
public class FlightDTO {

	private int flightId;
	@NotBlank(message = "Flight name is required")
	private String flightName;
	@NotBlank(message = "type of flight is required")
	private String typeOfFlight;
    @NotBlank(message = "Source is required")
	private String source;
    @NotBlank(message = "Destination is required")
    private String destination;
    @NotNull(message = "Time of arrival is required")
    private LocalTime timeOfArrival;
    @NotNull(message = "Time of departure is required")
    private LocalTime timeOfDeparture;
    @NotNull(message = "Time of departure is required")
    private String duration;
    @NotNull(message = "Fare is required")
    @Positive(message = "Fare must be a positive number")
    private double fare;
    private int numberOfSeats;
	
	public FlightDTO() {
		super();
	}

	public FlightDTO(int flightId, String flightName, String typeOfFlight,String source, String destination,LocalTime timeOfArrival, LocalTime timeOfDeparture,String duration,double fare,int numberOfSeats) {
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

}