package com.hexaware.airticketbooking.services;

import java.util.List;

import com.hexaware.airticketbooking.dto.FlightOwnerDTO;
import com.hexaware.airticketbooking.entities.FlightOwner;
import com.hexaware.airticketbooking.exceptions.FlightOwnerNotFoundException;

/*
 * Author: Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This is Service Interface of FlightOwner. Abstract methods are declared and they are implemented in service implementation classes. */

public interface IFlightOwnerService {
	public FlightOwnerDTO addFlightOwner(FlightOwnerDTO flightOwnerDto);
	public FlightOwnerDTO editFlightOwnerProfile(FlightOwnerDTO flightOwnerDto);
	public void deleteFlightOwner(int flightOwnerId );
	public FlightOwnerDTO getFlightOwnerDetailsById(int flightOwnerId)throws FlightOwnerNotFoundException;
	public List<FlightOwner> viewAllFlightOwners() throws FlightOwnerNotFoundException;
	

}
