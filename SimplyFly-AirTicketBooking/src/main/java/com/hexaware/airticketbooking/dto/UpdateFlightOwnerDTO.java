package com.hexaware.airticketbooking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateFlightOwnerDTO {
	private int flightOwnerId;
	@NotBlank(message = "FlightOwner name is required")
    @Size(min = 2, max = 50, message = "FlightOwner name must be between 2 and 50 characters")
	private String flightOwnerName;
	@Pattern(regexp = "^[0-9]{10}$")
	private String flightOwnerContact;
	@NotBlank(message = "FlightOwner email is required")
    @Email(message = "Invalid email format")
	private String flightOwnerEmail;
	public UpdateFlightOwnerDTO() {
		super();
	}
	public UpdateFlightOwnerDTO(int flightOwnerId,
			 String flightOwnerName,
			 String flightOwnerContact,
			 String flightOwnerEmail) {
		super();
		this.flightOwnerId = flightOwnerId;
		this.flightOwnerName = flightOwnerName;
		this.flightOwnerContact = flightOwnerContact;
		this.flightOwnerEmail = flightOwnerEmail;
	}
	public int getFlightOwnerId() {
		return flightOwnerId;
	}
	public void setFlightOwnerId(int flightOwnerId) {
		this.flightOwnerId = flightOwnerId;
	}
	public String getFlightOwnerName() {
		return flightOwnerName;
	}
	public void setFlightOwnerName(String flightOwnerName) {
		this.flightOwnerName = flightOwnerName;
	}
	public String getFlightOwnerContact() {
		return flightOwnerContact;
	}
	public void setFlightOwnerContact(String flightOwnerContact) {
		this.flightOwnerContact = flightOwnerContact;
	}
	public String getFlightOwnerEmail() {
		return flightOwnerEmail;
	}
	public void setFlightOwnerEmail(String flightOwnerEmail) {
		this.flightOwnerEmail = flightOwnerEmail;
	}
	
	
	
}
