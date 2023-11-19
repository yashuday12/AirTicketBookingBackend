package com.hexaware.airticketbooking.services;

import java.util.List;

import com.hexaware.airticketbooking.dto.FlightDTO;
import com.hexaware.airticketbooking.exceptions.FlightNotFoundException;
/*
 * Author: Uday Kiran
 * LastModifiedDate:19-11-2023
 * Description: This is Service Interface of Flight. Abstract methods are declared and they are implemented in service implementation classes. */

public interface IFlightService {
	public FlightDTO addFlight(FlightDTO flightDto, int flightOwnerId);
	public FlightDTO modifyFlightDetails(FlightDTO flightDto,int flightOwnerId);
	public void cancelFlight(int flightId);
	public FlightDTO getFlightById(int flightId)throws FlightNotFoundException;
	public List<FlightDTO> viewAllFlights() throws FlightNotFoundException;
	public List<FlightDTO> searchFlight(String source, String destination) throws FlightNotFoundException;
	

}
