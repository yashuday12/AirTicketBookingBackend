package com.hexaware.airticketbooking.entities;
/*
 * Author: Yashwanth
 * Date:
 * Description: This is FlightOwner Entity Class
 */
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
/*
 * Author: Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This class represents the entity for FlightOwner. It defines the structure of the
 * FlightOwner objects stored in the database.
 */
@Entity
@Table(name = "flightownerdetails")
public class FlightOwner {
	@Id
	@SequenceGenerator(name="flightOwner_seq",initialValue=3000,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="flightOwner_seq")
	private int flightOwnerId;
	private String flightOwnerPassword;
	private String flightOwnerName;
	private String flightOwnerContact;
	private String flightOwnerEmail;
	@OneToMany(mappedBy = "flightOwner",cascade = CascadeType.ALL)
	private List<Flight> flight=new ArrayList<>();
	private  final String ROLES="ROLE_FLIGHTOWNER";
	public FlightOwner() {
		super();
	}
	public FlightOwner(int flightOwnerId, String flightOwnerPassword, String flightOwnerName, String flightOwnerContact,
			String flightOwnerEmail, List<Flight> flight) {
		super();
		this.flightOwnerId = flightOwnerId;
		this.flightOwnerPassword = flightOwnerPassword;
		this.flightOwnerName = flightOwnerName;
		this.flightOwnerContact = flightOwnerContact;
		this.flightOwnerEmail = flightOwnerEmail;
		this.flight = flight;
		
	}
	public int getFlightOwnerId() {
		return flightOwnerId;
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
	public List<Flight> getFlight() {
		return flight;
	}
	public void setFlight(List<Flight> flight) {
		this.flight = flight;
	}
	
	public String getRoles() {
		return ROLES;
	}
	
	@Override
	public String toString() {
		return "FlightOwner [flightOwnerId=" + flightOwnerId + ", flightOwnerPassword=" + flightOwnerPassword
				+ ", flightOwnerName=" + flightOwnerName + ", flightOwnerContact=" + flightOwnerContact
				+ ", flightOwnerEmail=" + flightOwnerEmail + ", flight=" + flight + "]";
	}
	public void addFlight(Flight flight) {

				
		flight.setFlightOwner(this); 

				List<Flight> list = getFlight();

				list.add(flight);

			}

}