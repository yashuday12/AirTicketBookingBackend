package com.hexaware.airticketbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/*
 * Author: Yashwanth and UdayKiran
 * LastModifiedDate:19-11-2023
 * Description: This is DTO class to accept the username and password entered by admin,user and FlightOwner
 */
public class AuthRequest {
    @NotBlank(message = "User name is required")
	@Size(min = 2, max = 50, message = "User name must be between 2 and 50 characters")
	private String username;
	@NotBlank(message = " password is required")
    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}$")
	private String password;

	public AuthRequest() {
		super();
	}

	public AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
