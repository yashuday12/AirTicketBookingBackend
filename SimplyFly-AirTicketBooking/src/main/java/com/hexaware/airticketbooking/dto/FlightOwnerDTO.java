package com.hexaware.airticketbooking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/*
 * Author: Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This is DTO class for FlightOwner. It is completely used for data transfer between controller and service layers
 */
public class FlightOwnerDTO {
	private int flightOwnerId;
	@NotBlank(message = "FlightOwner password is required")
   @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}$")
	private String flightOwnerPassword;
	@NotBlank(message = "FlightOwner name is required")
    @Size(min = 2, max = 50, message = "FlightOwner name must be between 2 and 50 characters")
	private String flightOwnerName;
	@Pattern(regexp = "^[0-9]{10}$")
	private String flightOwnerContact;
	@NotBlank(message = "FlightOwner email is required")
    @Email(message = "Invalid email format")
	private String flightOwnerEmail;
	
	public FlightOwnerDTO() {
		super();
	}


	public FlightOwnerDTO(int flightOwnerId, String flightOwnerPassword, String flightOwnerName,
			String flightOwnerContact, String flightOwnerEmail) {
		super();
		this.flightOwnerId = flightOwnerId;
		this.flightOwnerPassword = flightOwnerPassword;
		this.flightOwnerName = flightOwnerName;
		this.flightOwnerContact = flightOwnerContact;
		this.flightOwnerEmail = flightOwnerEmail;
		
	}


	public int getFlightOwnerId() {
		return flightOwnerId;
	}


	@Override
	public String toString() {
		return "FlightOwnerDto [flightOwnerId=" + flightOwnerId + ", flightOwnerPassword=" + flightOwnerPassword
				+ ", flightOwnerName=" + flightOwnerName + ", flightOwnerContact=" + flightOwnerContact
				+ ", flightOwnerEmail=" + flightOwnerEmail + "]";
	}


	public void setFlightOwnerId(int flightOwnerId) {
		this.flightOwnerId = flightOwnerId;
	}


	public String getFlightOwnerPassword() {
		return flightOwnerPassword;
	}


	public void setFlightOwnerPassword(String flightOwnerPassword) {
		this.flightOwnerPassword = flightOwnerPassword;
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