package com.hexaware.airticketbooking.services;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.airticketbooking.dto.PassengerDTO;
import com.hexaware.airticketbooking.exceptions.PassengerNotFoundException;
/*
 * Author: Uday Kiran
 * LastModifiedDate:19-11-2023
 * Description: This is Service Interface of Passenger. Abstract methods are declared and they are implemented in service implementation classes. */

public interface IPassengerService {
	public void removePassenger(int passengerId);//It is used to delete the passenger details
	public PassengerDTO getByPassengerId(int passengerId)throws PassengerNotFoundException;//Used to fetch passenger details by using passenger id
	public List<PassengerDTO> getAllPassengers()throws PassengerNotFoundException;// This method will fetch all passenger details
	public List<PassengerDTO> getPassengerByTicketId(int ticketId) throws PassengerNotFoundException;
	public List<String> fetchBookedSeats(LocalDate travelDate, int flightId);//We can fetch the booked seats for particular flight at particular date
	

}
