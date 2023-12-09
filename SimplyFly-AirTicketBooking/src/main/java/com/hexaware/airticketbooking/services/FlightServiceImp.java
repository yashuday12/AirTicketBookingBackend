package com.hexaware.airticketbooking.services;
import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hexaware.airticketbooking.dto.FlightDTO;
import com.hexaware.airticketbooking.entities.Flight;
import com.hexaware.airticketbooking.entities.FlightOwner;
import com.hexaware.airticketbooking.exceptions.FlightNotFoundException;
import com.hexaware.airticketbooking.repository.IFlightOwnerRepository;
import com.hexaware.airticketbooking.repository.IFlightRepository;
/*
 * Author: UdayKIran
 * LastModifiedDate: 19-11-2023
 * Description: This Service class interacts  the FlightRepository and ensures business logic related to all Flights
 */
@Service
public class FlightServiceImp implements IFlightService {

	private IFlightRepository flightRepository;
	private IFlightOwnerRepository flightOwnerRepository;
    
	public FlightServiceImp(IFlightRepository flightRepository, IFlightOwnerRepository flightOwnerRepository) {
		super();
		this.flightRepository = flightRepository;
		this.flightOwnerRepository = flightOwnerRepository;
	}
	Logger logger = LoggerFactory.getLogger(FlightServiceImp.class);

	@Override
	public List<FlightDTO> viewAllFlights() throws FlightNotFoundException{
	      List<Flight> flight=flightRepository.findAll();
	      List<FlightDTO> flightDto=new ArrayList<>();
	      for(Flight flightTemp:flight) {
	    	  flightDto.add(new FlightDTO(flightTemp.getFlightId(),flightTemp.getFlightName(),flightTemp.getTypeOfFlight(),flightTemp.getSource(),flightTemp.getDestination(),flightTemp.getTimeOfArrival(),flightTemp.getTimeOfDeparture(),flightTemp.getDuration(),flightTemp.getFare(),flightTemp.getNumberOfSeats()));
	      }
	        logger.info("Flight Service Implementation - Fetched all Flights Successfully");
	      return flightDto;
	}

	@Override
	public List<FlightDTO> searchFlight(String source, String destination) throws FlightNotFoundException{

		List<Flight> flight=flightRepository.findBySourceAndDestination(source, destination);
		  List<FlightDTO> flightDto=new ArrayList<>();
	      for(Flight flightTemp:flight) {
	    	  flightDto.add(new FlightDTO(flightTemp.getFlightId(),flightTemp.getFlightName(),flightTemp.getTypeOfFlight(),flightTemp.getSource(),flightTemp.getDestination(),flightTemp.getTimeOfArrival(),flightTemp.getTimeOfDeparture(),flightTemp.getDuration(),flightTemp.getFare(),flightTemp.getNumberOfSeats()));
	      }
	        logger.info("Flight Service Implementation - Flight search from {} to {} successful.", source, destination);
	      return flightDto;
	}

	@Override
	public FlightDTO addFlight(FlightDTO flightDto, int flightOwnerId) {
		Flight flight=new Flight();
		FlightOwner flightOwner=flightOwnerRepository.findById(flightOwnerId).orElse(new FlightOwner());
		flight.setFlightName(flightDto.getFlightName());
		flight.setTypeOfFlight(flightDto.getTypeOfFlight());
		flight.setSource(flightDto.getSource());
		flight.setDestination(flightDto.getDestination());
		flight.setTimeOfArrival(flightDto.getTimeOfArrival());
		flight.setTimeOfDeparture(flightDto.getTimeOfDeparture());
		flight.setDuration(flightDto.getDuration());
		flight.setFare(flightDto.getFare());
		flight.setNumberOfSeats(flightDto.getNumberOfSeats());
		flight.setFlightOwner(flightOwner);
		Flight flightTemp=flightRepository.save(flight);
        logger.info("Flight Service Implementation - Added Flight data into database successfully");
		return new FlightDTO(flightTemp.getFlightId(),flightTemp.getFlightName(),flightTemp.getTypeOfFlight(),flightTemp.getSource(),flightTemp.getDestination(),flightTemp.getTimeOfArrival(),flightTemp.getTimeOfDeparture(),flightTemp.getDuration(),flightTemp.getFare(),flightTemp.getNumberOfSeats());
	}

	@Override
	public FlightDTO modifyFlightDetails(FlightDTO flightDto,int flightOwnerId) {
		Flight flight=new Flight();
		FlightOwner flightOwner=flightOwnerRepository.findById(flightOwnerId).orElse(new FlightOwner());
		flight.setFlightId(flightDto.getFlightId());
		flight.setFlightName(flightDto.getFlightName());
		flight.setTypeOfFlight(flightDto.getTypeOfFlight());
		flight.setSource(flightDto.getSource());
		flight.setDestination(flightDto.getDestination());
		flight.setTimeOfArrival(flightDto.getTimeOfArrival());
		flight.setTimeOfDeparture(flightDto.getTimeOfDeparture());
		flight.setDuration(flightDto.getDuration());
		flight.setFare(flightDto.getFare());
		flight.setNumberOfSeats(flightDto.getNumberOfSeats());
		flight.setFlightOwner(flightOwner);
		Flight flightTemp=flightRepository.save(flight);
        logger.info("Flight Service Implementation - Updated Flight data into database successfully");
        return new FlightDTO(flightTemp.getFlightId(),flightTemp.getFlightName(),flightTemp.getTypeOfFlight(),flightTemp.getSource(),flightTemp.getDestination(),flightTemp.getTimeOfArrival(),flightTemp.getTimeOfDeparture(),flightTemp.getDuration(),flightTemp.getFare(),flightTemp.getNumberOfSeats());
	}
	

	@Override
	public void cancelFlight(int flightId) {
        logger.info("Flight Data with ID :{} deleted successfully in the Flight Service Implementation", flightId);

		flightRepository.deleteById(flightId);
	}

	@Override
	public FlightDTO getFlightById(int flightId) throws FlightNotFoundException{
		Flight flight=flightRepository.findById(flightId).orElse(new Flight());
        logger.info("Flight Service Implementation - Fetching Flight with ID :{}", flightId);

		return new FlightDTO(flight.getFlightId(),flight.getFlightName(),flight.getTypeOfFlight(),flight.getSource(),flight.getDestination(),flight.getTimeOfArrival(),flight.getTimeOfDeparture(),flight.getDuration(),flight.getFare(),flight.getNumberOfSeats());
	}

	@Override
	public List<FlightDTO> getFlightDetailsByFlightOwnerId(int flightOwnerId) {

		List<Flight> flights=flightRepository.getFlightsByFlightOwnerId(flightOwnerId);
		List<FlightDTO> flightList=new ArrayList<FlightDTO>();
		for (Flight flight : flights) {
			FlightDTO flightDto=new FlightDTO();
			flightDto.setFlightId(flight.getFlightId());
			flightDto.setFlightName(flight.getFlightName());
			flightDto.setSource(flight.getSource());
			flightDto.setDestination(flight.getDestination());
			flightDto.setTimeOfArrival(flight.getTimeOfArrival());
			flightDto.setTimeOfDeparture(flight.getTimeOfDeparture());
			flightDto.setTypeOfFlight(flight.getTypeOfFlight());
			flightDto.setFare(flight.getFare());
			flightDto.setDuration(flight.getDuration());
			flightDto.setNumberOfSeats(flight.getNumberOfSeats());
			flightList.add(flightDto);
		}
			
		return flightList;
	}
	
}
