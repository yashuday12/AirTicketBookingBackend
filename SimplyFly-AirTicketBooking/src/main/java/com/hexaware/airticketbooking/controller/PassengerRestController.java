package com.hexaware.airticketbooking.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.airticketbooking.dto.PassengerDTO;
import com.hexaware.airticketbooking.exceptions.PassengerNotFoundException;
import com.hexaware.airticketbooking.services.IPassengerService;
/*
 * Author: UdayKiran
 * LastModifiedDate: 19-11-2023
 * Description: This class represents an Passenger Rest Controller, which handles HTTP requests related to Passengers.
 */
@RestController
@RequestMapping("/api/v1/passenger")
@CrossOrigin("http://localhost:4200/")
public class PassengerRestController {
	
	private IPassengerService passengerService;
	Logger logger = LoggerFactory.getLogger(PassengerRestController.class);

	public PassengerRestController(IPassengerService passengerService) {
		super();
		this.passengerService = passengerService;
	}
	
	@DeleteMapping("/deletePassenger/{passengerId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN,ROLE_USER')")

	public ResponseEntity<String> removePassenger(@PathVariable int passengerId) {
        logger.info("Received request to remove passenger with passengerId: {}", passengerId);

		passengerService.removePassenger(passengerId);
		return new ResponseEntity<>("Passenger deleted sucessfully", HttpStatus.ACCEPTED);
			}
	
	@GetMapping("/getpassengerbyid/{passengerId}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")

	public PassengerDTO getByPassengerId(@PathVariable int passengerId) {
        logger.info("Received request to get passenger details for passengerId: {}", passengerId);

		PassengerDTO passengerDto= passengerService.getByPassengerId(passengerId);
		if(passengerDto.getPassengerId()!=passengerId) {
            logger.warn("No passenger details found for passengerId: {}", passengerId);
            throw new PassengerNotFoundException(HttpStatus.NOT_FOUND,"No Passenger details found with passengerId:"+passengerId);
		}
		return passengerDto;
	}
	
	@GetMapping("/getallpassenger")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<PassengerDTO> getAllPassengers() {
        logger.info("Received request to get all passengers");
        List<PassengerDTO> passengerDto= passengerService.getAllPassengers();
		if(passengerDto.isEmpty()) {
            logger.warn("No passenger details found");
            throw new PassengerNotFoundException(HttpStatus.NOT_FOUND,"No Passenger details found ");
		}
		return passengerDto;
	}
	
	@GetMapping("/getpassengersbyticketId/{ticketId}")
	@PreAuthorize("hasAnyAuthority('ROLE_FLIGHTOWNER','ROLE_USER')")
	public List<PassengerDTO> getPassengerByTicketId(@PathVariable int ticketId) {
        logger.info("Received request to get passengers by ticketId: {}", ticketId);

		List<PassengerDTO> passengerDto= passengerService.getPassengerByTicketId(ticketId);
		if(passengerDto.isEmpty()) {
            logger.warn("No passenger details found under ticketId: {}", ticketId);
            throw new PassengerNotFoundException(HttpStatus.NOT_FOUND,"No Passenger details found under ticketId:"+ticketId);
		}
		return passengerDto;
	}
	
	@GetMapping("/fetchbookedseats/{travelDate}/{flightId}")

	public List<String> fetchBookedSeats(@PathVariable String travelDate, @PathVariable int flightId) {
        logger.info("Received request to fetch booked seats for travelDate: {} and flightId: {}", travelDate, flightId);
        LocalDate date = LocalDate.parse(travelDate.trim());
		return passengerService.fetchBookedSeats(date, flightId);
	}
}