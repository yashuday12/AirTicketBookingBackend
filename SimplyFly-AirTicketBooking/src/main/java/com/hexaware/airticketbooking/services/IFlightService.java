package com.hexaware.airticketbooking.services;

import java.util.List;

import com.hexaware.airticketbooking.dto.FlightDTO;
import com.hexaware.airticketbooking.exceptions.FlightNotFoundException;
/*
 * Author: Uday Kiran
 * LastModifiedDate:19-11-2023
 * Description: This is Service Interface of Flight. Abstract methods are declared and they are implemented in service implementation classes. */

public interface IFlightService {
	public FlightDTO addFlight(FlightDTO flightDto, int flightOwnerId);//Flight can be added by flightowner using this method;
	public FlightDTO modifyFlightDetails(FlightDTO flightDto,int flightOwnerId);// we can manage routes by using this method
	public void cancelFlight(int flightId);// we can cancel flight using this method
	public FlightDTO getFlightById(int flightId)throws FlightNotFoundException;// Flight details can be fetched by using flightid
	public List<FlightDTO> viewAllFlights() throws FlightNotFoundException;// All Flights can be displayed
	public List<FlightDTO> searchFlight(String source, String destination) throws FlightNotFoundException;//user can search flightthat are travelling from one source to destination
	
	public List<FlightDTO> getFlightDetailsByFlightOwnerId(int flightOwnerId);
	

}
