package com.hexaware.airticketbooking.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
/*
 * Author:Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This class represents the entity for User. It defines the structure of the
 * User objects stored in the database.
 */
@Entity
@Table(name = "userdetails")
public class User {
	
	@Id
	@SequenceGenerator(name="User_seq",initialValue=100,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="User_seq")
	private int userId;
	private String userName;
	private String gender;
	private String contactNumber;
	private String address;
	private LocalDate dateOfBirth;
	private String userEmail;
	private String password;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	@JsonIgnore
	private List<Ticket> ticket=new ArrayList<>();
	private final String ROLES="ROLE_USER";
	private long wallet;
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Payment> payment;
	public User() {
		super();
	}
	public User(int userId, String userName, String gender, String contactNumber, String address,
			LocalDate dateOfBirth, String userEmail, String password, List<Ticket> ticket,long wallet,List<Payment> payment) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.userEmail = userEmail;
		this.password = password;
		this.ticket = ticket;
		this.wallet=wallet;
		this.payment=payment;
	}
	public int getUserId() {
		return userId;
	}
	
	public List<Payment> getPayment() {
		return payment;
	}
	public void setPayment(List<Payment> payment) {
		this.payment = payment;
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
	public List<Ticket> getTicket() {
		return ticket;
	}
	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}
	
	public String getRoles() {
		return ROLES;
	}
	
	public long getWallet() {
		return wallet;
	}
	public void setWallet(long wallet) {
		this.wallet = wallet;
	}
	public void addTicket(Ticket ticket) {

	
		ticket.setUser(this); 

				List<Ticket> list = getTicket();

				list.add(ticket);

			}

	
	
}
