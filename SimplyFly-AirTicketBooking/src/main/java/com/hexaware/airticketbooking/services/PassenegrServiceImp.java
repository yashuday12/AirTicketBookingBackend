package com.hexaware.airticketbooking.services;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hexaware.airticketbooking.dto.PassengerDTO;
import com.hexaware.airticketbooking.entities.Passenger;
import com.hexaware.airticketbooking.exceptions.PassengerNotFoundException;
import com.hexaware.airticketbooking.repository.IPassengerRepository;
/*
 * Author: UdayKIran
 * LastModifiedDate: 19-11-2023
 * Description: This Service class interacts  the PassengerRepository and ensures business logic related to Passenger
 */
@Service
public class PassenegrServiceImp implements IPassengerService {

	private IPassengerRepository passengerRepository;
	
	
	public PassenegrServiceImp(IPassengerRepository passengerRepository) {
		super();
		this.passengerRepository = passengerRepository;
	}
	Logger logger = LoggerFactory.getLogger(PassenegrServiceImp.class);

	@Override
	public void removePassenger(int passengerId) {
        logger.info("Passenger Service - Deleting passenger with ID: {}", passengerId);
		passengerRepository.deleteById(passengerId);
        logger.info("Passenger Service - Passenger with ID: {} deleted successfully", passengerId);

	}

	@Override
	public PassengerDTO getByPassengerId(int passengerId) throws PassengerNotFoundException {
		Passenger passenger=passengerRepository.findById(passengerId).orElse(new Passenger());

        logger.info("Passenger Service - Fetching passenger with ID: {}", passengerId);
		return new PassengerDTO(passenger.getPassenegerId(),passenger.getPassengerName(),passenger.getPassengerAge(),passenger.getPassengerSeatNumber());

	}

	@Override
	public List<PassengerDTO> getAllPassengers() throws PassengerNotFoundException{

		List<Passenger> passengerTemp=passengerRepository.findAll();
		List<PassengerDTO> passengerDto=new ArrayList<>();
		for(Passenger passenger: passengerTemp) {
			
			passengerDto.add(new PassengerDTO(passenger.getPassenegerId(),passenger.getPassengerName(),passenger.getPassengerAge(),passenger.getPassengerSeatNumber()));
		}
        logger.info("Passenger Service - Fetching all passengers");
		return passengerDto;
	}

	@Override
	public List<PassengerDTO> getPassengerByTicketId(int ticketId) throws PassengerNotFoundException{
        logger.info("Passenger Service - Fetching passengers for ticket ID: {}", ticketId);
		
	       List<Passenger> passengerTemp= passengerRepository.getPassengerByTicketId(ticketId);
	       List<PassengerDTO> passengerDto=new ArrayList<>();
	       for(Passenger passenger: passengerTemp) {
		
		        passengerDto.add(new PassengerDTO(passenger.getPassenegerId(),passenger.getPassengerName(),passenger.getPassengerAge(),passenger.getPassengerSeatNumber()));
	       }
	        logger.info("Passenger Service - Passengers fetched successfully for ticket ID: {}. Count: {}", ticketId, passengerDto.size());
	       return passengerDto;
	}
	@Override
    public List<String> fetchBookedSeats(LocalDate travelDate, int flightId) {
        logger.info("Passenger Service - Based on the travelled date: {} and flight ID: {} fetched booked seats successfully", travelDate, flightId);
		return passengerRepository.fetchBookedSeats(travelDate, flightId);
	}

}
