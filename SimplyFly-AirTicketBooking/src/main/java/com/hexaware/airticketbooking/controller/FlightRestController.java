package com.hexaware.airticketbooking.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.airticketbooking.dto.FlightDTO;
import com.hexaware.airticketbooking.exceptions.FlightNotFoundException;
import com.hexaware.airticketbooking.services.IFlightService;

import jakarta.validation.Valid;
/*
 * Author: UdayKiran
 * LastModifiedDate: 19-11-2023
 * Description: This class represents an Flight Rest Controller, which handles HTTP requests related to Flights.
 */
@RestController
@RequestMapping("/api/v1/flight")
@CrossOrigin(origins="http://localhost:4200/")
public class FlightRestController {

	private IFlightService flightService;
	Logger logger = LoggerFactory.getLogger(FlightRestController.class);

	public FlightRestController(IFlightService flightService) {
		super();
		this.flightService = flightService;
	}
	
	@PostMapping("/addflight/{flightOwnerId}")
	public FlightDTO addFlight(@RequestBody @Valid FlightDTO flightDto,@PathVariable int flightOwnerId) {
        logger.info("Received request to add flight with flightOwnerId: {}", flightOwnerId);
        return flightService.addFlight(flightDto,flightOwnerId);
	}
	
	@PutMapping("/updateflight/{flightOwnerId}")
	@PreAuthorize("hasAnyAuthority('ROLE_FLIGHTOWNER','ROLE_ADMIN')")
	public FlightDTO modifyFlightDetails(@RequestBody @Valid FlightDTO flightDto, @PathVariable int flightOwnerId) {
        logger.info("Received request to update flight details with flightOwnerId: {}", flightOwnerId);
        return flightService.modifyFlightDetails(flightDto,flightOwnerId);
	}
	

	@DeleteMapping("/deleteflight/{flightId}")
	@PreAuthorize("hasAnyAuthority('ROLE_FLIGHTOWNER')")
	public ResponseEntity<String> cancelFlight(@PathVariable int flightId) {
        logger.info("Received request to cancel flight with flightId: {}", flightId);
        flightService.cancelFlight(flightId);
		return new ResponseEntity<>("Flight deleted sucessfully", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/Getflightbyid/{flightId}")
	@PreAuthorize("hasAnyAuthority('ROLE_FLIGHTOWNER','ROLE_USER')")
	public FlightDTO getFlightById(@PathVariable int flightId) {
        logger.info("Received request to get flight details for flightId: {}", flightId);
        FlightDTO flightDto= flightService.getFlightById(flightId);
		if(flightDto.getFlightId()!=flightId) {
            logger.warn("Flight not found for flightId: {}", flightId);
            throw new FlightNotFoundException(HttpStatus.NOT_FOUND,"Flight not found for flightId:"+flightId);
		}
		return flightDto;
	}
	
	@GetMapping("/Getallflights")
	@PreAuthorize("hasAnyAuthority('ROLE_FLIGHTOWNER')")
	public List<FlightDTO> viewAllFlights(){
        logger.info("Received request to get all flights");
        List<FlightDTO> flightDto=flightService.viewAllFlights();
		if(flightDto.isEmpty()) {
            logger.warn("Zero flights found");
            throw new FlightNotFoundException(HttpStatus.NOT_FOUND,"no flights are available");
	     }
	     return flightDto;
	}
	
	@GetMapping("/searchflight/{source}/{destination}")
	public List<FlightDTO> searchFlight(@PathVariable String source, @PathVariable String destination){
        logger.info("Received request to search flights from {} to {}", source, destination);

		List<FlightDTO> flightDto=flightService.searchFlight( source, destination);
		if(flightDto.isEmpty()) {
            logger.warn("No flights found from {} to {}", source, destination);
            throw new FlightNotFoundException(HttpStatus.NOT_FOUND,"no flights are available from source"+source+"to destination");
	     }
	     return flightDto;
	}
	@GetMapping("/getflightsbyflightownerid/{flightOwnerId}")
	@PreAuthorize("hasAnyAuthority('ROLE_FLIGHTOWNER')")
	public List<FlightDTO> getFlightDetailsByFlightOwnerId(@PathVariable int flightOwnerId){
		
		return flightService.getFlightDetailsByFlightOwnerId(flightOwnerId);
	}
	
	
	}

