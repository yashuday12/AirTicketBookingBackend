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


import com.hexaware.airticketbooking.dto.FlightOwnerDTO;
import com.hexaware.airticketbooking.dto.UpdateFlightOwnerDTO;
import com.hexaware.airticketbooking.exceptions.FlightNotFoundException;
import com.hexaware.airticketbooking.exceptions.FlightOwnerNotFoundException;
import com.hexaware.airticketbooking.services.IFlightOwnerService;

import jakarta.validation.Valid;
/*
 * Author: Yashwanth
 * Last Modified Date: 19-11-2023
 * Description: This class represents a FlightOwner Rest Controller, which handles HTTP requests related to flight owners.
 */
@RestController
@RequestMapping("/api/v1/flightowner")
@CrossOrigin("http://localhost:4200")
public class FlightOwnerRestController {
	
	private IFlightOwnerService flightOwnerService;
	Logger logger = LoggerFactory.getLogger(FlightOwnerRestController.class);

	public FlightOwnerRestController(IFlightOwnerService flightOwnerService) {
		super();
		this.flightOwnerService = flightOwnerService;
	}

	@PostMapping("/addflightowner")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public FlightOwnerDTO addFlightOwner(@RequestBody @Valid FlightOwnerDTO flightOwnerDto) {
        logger.info("Received request to add flight owner: {}", flightOwnerDto.getFlightOwnerName());
        return flightOwnerService.addFlightOwner(flightOwnerDto);
		
	}

	@PutMapping("/updateflightowner/{flightOwnerId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_FLIGHTOWNER')")
	public UpdateFlightOwnerDTO editFlightOwnerProfile(@RequestBody @Valid UpdateFlightOwnerDTO flightOwnerDto,@PathVariable int flightOwnerId) {
        logger.info("Received request to update flight owner profile for flightOwnerId: {}", flightOwnerDto.getFlightOwnerId());
        return flightOwnerService.editFlightOwnerProfile(flightOwnerDto,flightOwnerId);
		
	}
	
	@DeleteMapping("/deleteflightowner/{flightOwnerId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteFlightOwner(@PathVariable int flightOwnerId) {
        logger.info("Received request to delete flight owner with flightOwnerId: {}", flightOwnerId);
        flightOwnerService.deleteFlightOwner(flightOwnerId);
		return new ResponseEntity<>("Flight Owner deleted sucessfully", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getflightownerbyid/{flightOwnerId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_FLIGHTOWNER')")
	public UpdateFlightOwnerDTO getFlightOwnerDetailsById(@PathVariable int flightOwnerId) {
        logger.info("Received request to get flight owner details for flightOwnerId: {}", flightOwnerId);
        UpdateFlightOwnerDTO flighOwner= flightOwnerService.getFlightOwnerDetailsById(flightOwnerId);
		if (flighOwner.getFlightOwnerId()!=flightOwnerId) {
            logger.info("Flight owner not found for flightOwnerId: {}", flightOwnerId);
            throw new FlightNotFoundException(HttpStatus.NOT_FOUND, "flightOwnerId with flightOwnerId :"+flightOwnerId+"not found");
		}
		return flighOwner;
	}
	
	@GetMapping("/getallflightowner")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<FlightOwnerDTO> viewAllFlightOwners(){
        logger.info("Received request to get all flight owners");
        List<FlightOwnerDTO> flighOwner= flightOwnerService.viewAllFlightOwners();
		if (flighOwner.isEmpty()) {
            logger.info("Zero flight owners found");
            throw new FlightOwnerNotFoundException(HttpStatus.NOT_FOUND, "zero flightOwners");
		}
		return flighOwner;
	}
	@GetMapping("/verifyownerpassword/{flightOwnerId}/{password}")
	@PreAuthorize("hasAnyAuthority('ROLE_FLIGHTOWNER')")
	public boolean verifyFlightOwnerPassword(@PathVariable int flightOwnerId, @PathVariable String password) {
		return flightOwnerService.verifyownerpassword(password, flightOwnerId);
	}
	@PutMapping("/changeownerpassword/{flightOwnerId}/{password}")
	@PreAuthorize("hasAnyAuthority('ROLE_FLIGHTOWNER')")
	public FlightOwnerDTO changeUserPassword(@PathVariable int flightOwnerId,@PathVariable String password) {
        logger.info("Received request to change password for flightOwnerId: {}", flightOwnerId);
        return flightOwnerService.changeOwnerPassword(flightOwnerId, password);	
	}
}
