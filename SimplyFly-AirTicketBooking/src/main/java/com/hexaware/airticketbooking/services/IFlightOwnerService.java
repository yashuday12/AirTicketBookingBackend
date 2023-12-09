package com.hexaware.airticketbooking.services;

import java.util.List;

import com.hexaware.airticketbooking.dto.FlightOwnerDTO;
import com.hexaware.airticketbooking.dto.UpdateFlightOwnerDTO;

import com.hexaware.airticketbooking.exceptions.FlightOwnerNotFoundException;


/*
 * Author: Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This is Service Interface of FlightOwner. Abstract methods are declared and they are implemented in service implementation classes. */

public interface IFlightOwnerService {
	public FlightOwnerDTO addFlightOwner(FlightOwnerDTO flightOwnerDto);// Using this method we can add flightowner details
	public UpdateFlightOwnerDTO editFlightOwnerProfile(UpdateFlightOwnerDTO flightOwnerDto,int flightOwnerId);//This method will help us in editing flightowner details
	public void deleteFlightOwner(int flightOwnerId );// Flight Owner can be deleted
	public UpdateFlightOwnerDTO getFlightOwnerDetailsById(int flightOwnerId)throws FlightOwnerNotFoundException;//we can fetch flightowner details by using id;
	public List<FlightOwnerDTO> viewAllFlightOwners() throws FlightOwnerNotFoundException;//It is used to fetch and display all flightowners
	 public boolean verifyownerpassword(String password,int flightOwnerId);
	 public FlightOwnerDTO changeOwnerPassword(int flightOwnerId, String password);
}
