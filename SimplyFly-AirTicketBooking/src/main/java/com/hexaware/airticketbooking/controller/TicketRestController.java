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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.airticketbooking.dto.TicketDTO;
import com.hexaware.airticketbooking.dto.TicketPassengerVO;
import com.hexaware.airticketbooking.exceptions.TicketNotFoundException;
import com.hexaware.airticketbooking.services.ITicketService;
/*
 * Author: UdayKiran
 * LastModifiedDate: 19-11-2023
 * Description: This class represents an Ticket Rest Controller, which handles HTTP requests related to Tickets.
 */
@RestController
@RequestMapping("/api/v1/ticket")
@CrossOrigin("http://localhost:4200")
public class TicketRestController {

	private ITicketService ticketService;
	Logger logger = LoggerFactory.getLogger(TicketRestController.class);

	
	public TicketRestController(ITicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}
	
	@PostMapping("/addticket/{userId}/{flightId}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	public TicketDTO bookAirTicket(@RequestBody TicketPassengerVO ticketPassengerVo ,@PathVariable int userId, @PathVariable int flightId) {
        logger.info("Received request to book air ticket for userName: {} and flightId: {}", userId, flightId);
  
		return ticketService.bookAirTicket(ticketPassengerVo, userId, flightId);

	}
	
	@DeleteMapping("/deleteticket/{ticketId}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_FLIGHTOWNER','ROLE_ADMIN')")
	public ResponseEntity<String> cancelTicket(@PathVariable int ticketId) {
        logger.info("Received request to cancel ticket for ticketId: {}", ticketId);

		ticketService.cancelTicket(ticketId);
		return new ResponseEntity<>("Ticket deleted sucessfully", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getticketbyid/{ticketId}")
	@PreAuthorize("hasAnyAuthority('ROLE_FLIGHTOWNER','ROLE_USER')")
	public TicketDTO fetchBookingDetailsByTicketId(@PathVariable int ticketId) {
        logger.info("Received request to fetch booking details for ticketId: {}", ticketId);

		TicketDTO ticketDto=ticketService.fetchBookingDetailsByTicketId(ticketId);
		if(ticketDto.getTicketId()!=ticketId) {
            logger.warn("Ticket details not found for ticketId: {}", ticketId);
            throw new TicketNotFoundException(HttpStatus.NOT_FOUND,"Ticket details with ticketId:"+ticketId+" not found");
		}
		return ticketDto;
		
	}
	@GetMapping("/getallticket")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<TicketDTO> veiwTicketDetails() {
        logger.info("Received request to view all ticket details");

		List<TicketDTO> ticketDto=ticketService.veiwTicketDetails();
		if(ticketDto.isEmpty()) {
            logger.warn("No tickets booked");
            throw new TicketNotFoundException(HttpStatus.NO_CONTENT,"There are no tickets booked");
		}
		return ticketDto;
	}
	
	 @GetMapping("/ticketbookedbyuserid/{userId}")
		@PreAuthorize("hasAnyAuthority('ROLE_USER')")

	public List<TicketDTO> viewTicketBookedByUserId(@PathVariable int userId) {
	        logger.info("Received request to view tickets booked by userId: {}", userId);

		List<TicketDTO> ticketDto= ticketService.viewTicketBookedByUserId(userId);
		if(ticketDto.isEmpty()) {
            logger.warn("No tickets booked by userId: {}", userId);
            throw new TicketNotFoundException(HttpStatus.NOT_FOUND,"no tickets booked by userId"+userId);
		}
		return ticketDto;
	}

	@GetMapping("/getticketsByflightownerid/{flightOwnerId}")
	@PreAuthorize("hasAnyAuthority('ROLE_FLIGHTOWNER')")

	public List<TicketDTO> getAllTicketsByFlightOwnerId(@PathVariable int flightOwnerId) {
        logger.info("Received request to view all tickets booked for flight added by flightOwnerId: {}", flightOwnerId);

		List<TicketDTO> ticketDto=ticketService.getAllTicketsByFlightOwnerId(flightOwnerId);
		if(ticketDto.isEmpty()) {
            logger.warn("No tickets were booked for flight added by flightOwnerId: {}", flightOwnerId);
            throw new TicketNotFoundException(HttpStatus.NOT_FOUND,"no tickets were booked for flight added by flightOwnerId:"+flightOwnerId);
		}
		return ticketDto;
	}
	 @GetMapping("/getflightid/{ticketId}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	public int getFlightIdBYTicketId(@PathVariable int ticketId) {
		return ticketService.getFlightIdByTicketId(ticketId);
	}

		@GetMapping("/sendemailonbooking/{ticketId}")
		@PreAuthorize("hasAnyAuthority('ROLE_USER')")
		public boolean success(@PathVariable int ticketId) {
			ticketService.sendEmailOnBooking(ticketId);
			return true;
		}
	
}
