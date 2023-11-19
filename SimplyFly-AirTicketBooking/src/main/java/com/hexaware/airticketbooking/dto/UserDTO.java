package com.hexaware.airticketbooking.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*
 * Author: Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This is DTO class for User. It is completely used for data transfer between controller and service layers
 */
public class UserDTO {
	

    private int userId;
    @NotBlank(message = "User name is required")
    @Size(min = 2, max = 50, message = "User name must be between 2 and 50 characters")
	private String userName;
    @Pattern(regexp = "^(male|female)$", message = "Gender must be 'male' or 'female'")
    private String gender;
	@Pattern(regexp = "^[0-9]{10}$")
	private String contactNumber;
	@NotBlank
	private String address;
	private LocalDate dateOfBirth;
	@NotBlank(message = "User email is required")
    @Email(message = "Invalid email format")
    private String userEmail;
	@NotBlank(message = "User password is required")
    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}$")
	private String password;
	
	private long wallet;
	
	
	public UserDTO() {
		super();
	}

	public UserDTO(int userId, String userName, String gender, String contactNumber, String address,
			LocalDate dateOfBirth, String userEmail, String password,long wallet) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.userEmail = userEmail;
		this.password = password;
		
		this.wallet=wallet;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getWallet() {
		return wallet;
	}

	public void setWallet(long wallet) {
		this.wallet = wallet;
	}
	
	
}
