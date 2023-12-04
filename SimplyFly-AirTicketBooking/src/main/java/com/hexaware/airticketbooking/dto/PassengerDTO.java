package com.hexaware.airticketbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/*
 * Author: Uday Kiran
 * LastModifiedDate:19-11-2023
 * Description: This is DTO class for Passenger. It is completely used for data transfer between controller and service layers
 */
public class PassengerDTO {


	private int passengerId;
	@NotBlank(message = "Passenger name is required")
	@Size(min = 2, max = 50, message = "Passenger name must be between 2 and 50 characters")
 	private String passengerName;
	@Positive
	private int passengerAge;
	@Pattern(regexp="[a][1-5]{2}")  //seats a1-a55
	private String passengerSeatNumber;
	
	
public PassengerDTO() {
	super();
}


public PassengerDTO(int passengerId, String passengerName, int passengerAge, String passengerSeatNumber) {
	super();
	this.passengerId = passengerId;
	this.passengerName = passengerName;
	this.passengerAge = passengerAge;
	this.passengerSeatNumber = passengerSeatNumber;
}


public int getPassengerId() {
	return passengerId;
}


public void setPassengerId(int passengerId) {
	this.passengerId = passengerId;
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


@Override
public String toString() {
	return "PassengerDTO [passenegerId=" + passengerId + ", passengerName=" + passengerName + ", passengerAge="
			+ passengerAge + ", passengerSeatNumber=" + passengerSeatNumber + "]";
}


 
}