package com.hexaware.airticketbooking.services;
/*
 * Author: UdayKIran
 * Date:
 * Description: This is Ticket Service Implementation class
 */
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hexaware.airticketbooking.dto.PassengerDTO;
import com.hexaware.airticketbooking.dto.TicketDTO;
import com.hexaware.airticketbooking.dto.TicketPassengerVO;
import com.hexaware.airticketbooking.entities.Flight;
import com.hexaware.airticketbooking.entities.Passenger;
import com.hexaware.airticketbooking.entities.Payment;
import com.hexaware.airticketbooking.entities.Ticket;
import com.hexaware.airticketbooking.entities.User;
import com.hexaware.airticketbooking.exceptions.InsufficientBalanceException;
import com.hexaware.airticketbooking.exceptions.NoSeatsAvailableException;
import com.hexaware.airticketbooking.exceptions.TicketNotFoundException;
import com.hexaware.airticketbooking.repository.IFlightRepository;
import com.hexaware.airticketbooking.repository.IPaymentRepository;
import com.hexaware.airticketbooking.repository.ITicketRepository;
import com.hexaware.airticketbooking.repository.IUserRepository;
/*
* Author: UdayKiran
* LastModifiedDate: 19-11-2023
* Description: This Service class interacts  the TicketRepository and ensures business logic related to Tickrts
* 
*/
@Service
public class TicketServiceimp implements ITicketService {

	private ITicketRepository ticketRepository;
	private IFlightRepository flightRepository;
	private IUserRepository userRepository;
	@Autowired
	private IPaymentRepository paymentRepository;
	 @Autowired
	 private JavaMailSender mailSender;

	
	public TicketServiceimp(ITicketRepository ticketRepository, IFlightRepository flightRepository,
			IUserRepository userRepository) {
		super();
		this.ticketRepository = ticketRepository;
		this.flightRepository = flightRepository;
		this.userRepository = userRepository;
	}
	Logger logger = LoggerFactory.getLogger(TicketServiceimp.class);

  
	@Override
	public TicketDTO bookAirTicket(TicketPassengerVO ticketPassengerVo ,int userId,int flightId) {
		 logger.info("Ticket Service Implementation - Inserting ticket for user: {} on flight ID: {}", userId, flightId);
	    Flight flight=flightRepository.findById(flightId).orElse(new Flight());
		 if(flight.getNumberOfSeats()!=0) {
			 TicketDTO ticketDto = ticketPassengerVo.getTicketDto();
			 Ticket ticketDetails =new Ticket();
			 ticketDetails.setNumberOfPassengers(ticketDto.getNumberOfPassengers());
			 ticketDetails.setEmail(ticketDto.getEmail());
			 ticketDetails.setTravelDate(ticketDto.getTravelDate());
			 ticketDetails.setTotalAmount(ticketDto.getTotalAmount());
			 User user=userRepository.findById(userId).orElse(new User());
			 if(user.getWallet() < ticketDetails.getTotalAmount()){
					throw new InsufficientBalanceException(HttpStatus.BAD_REQUEST,"Your wallet doesn't have sufficient balance to book a ticket");
				}
			 long remainingwallet=(long) (user.getWallet()-ticketDetails.getTotalAmount());
			 user.setWallet(remainingwallet);
			 userRepository.save(user);
			 ticketDetails.setUser(user);
			 ticketDetails.setFlight(flight);
			
			 List<PassengerDTO> passengerDto=ticketPassengerVo.getPassengerDto();
			 List<Passenger>  passenger=new ArrayList<>();
			 for(PassengerDTO passengerDtoTemp : passengerDto) {
				 Passenger passengerTemp=new Passenger();
				 passengerTemp.setPassengerAge(passengerDtoTemp.getPassengerAge());
				 passengerTemp.setPassengerName(passengerDtoTemp.getPassengerName());
				 passengerTemp.setPassengerSeatNumber(passengerDtoTemp.getPassengerSeatNumber());
			     passenger.add(passengerTemp);
			 }
			 
			 for (Passenger passenger1: passenger) {
				 ticketDetails.addPassenger(passenger1);
			 		}
			Ticket ticket=  ticketRepository.save(ticketDetails);
			 Payment payment=new Payment();
			 payment.setAmountToBePaid(ticket.getTotalAmount());
			 payment.setStatus("Success");
			 payment.setUser(user);
			 payment.setTicket(ticket);
			 paymentRepository.save(payment);
		logger.info("Ticket Service Implementation - Ticket inserted successfully. Ticket ID: {}", ticket.getTicketId());
		return new TicketDTO(ticket.getTicketId(),ticket.getTravelDate(),ticket.getEmail(),ticket.getTotalAmount(),ticket.getNumberOfPassengers());
}
	   else {
		   throw new  NoSeatsAvailableException(HttpStatus.BAD_REQUEST," All Seats are reserved");
	   }

	}   
	
	@Override
	public void cancelTicket(int ticketId) {
		 logger.info("Ticket Service Implementation - Deleting Ticket with ID: {}", ticketId);
        Ticket ticket=ticketRepository.findById(ticketId).orElse(new Ticket());
        
        User user=ticket.getUser();
      
        long refundedAmount=(long) (user.getWallet()+ticket.getTotalAmount());       
       
        user.setWallet(refundedAmount);
        
        userRepository.save(user);
		ticketRepository.deleteById(ticketId);
		logger.info("Ticket Service Implementation - Ticket Data with ID: {} deleted successfully.", ticketId);
		   
	}

	@Override
	public TicketDTO fetchBookingDetailsByTicketId(int ticketId)throws TicketNotFoundException {
		logger.info("Ticket Service Implementation - Fetching Ticket with ID: {}", ticketId);

		Ticket ticket=ticketRepository.findById(ticketId).orElse(new Ticket());

		logger.info("Ticket Service Implementation - Ticket fetched successfully for ID: {}", ticketId);

		return new TicketDTO(ticket.getTicketId(),ticket.getTravelDate(),ticket.getEmail(),ticket.getTotalAmount(),ticket.getNumberOfPassengers());
		
	}

	@Override
	public List<TicketDTO> veiwTicketDetails() throws TicketNotFoundException {
		 logger.info("Ticket Service Implementation - Fetching all tickets.");

		List<Ticket> ticketTemp= ticketRepository.findAll();
		List<TicketDTO> ticketDto=new ArrayList<>();
		for(Ticket ticket:ticketTemp) {
			ticketDto.add(new TicketDTO(ticket.getTicketId(),ticket.getTravelDate(),ticket.getEmail(),ticket.getTotalAmount(),ticket.getNumberOfPassengers()));
		}
		logger.info("Ticket Service Implementation - All tickets fetched successfully.");

		return ticketDto;
	}
    
	

	@Override
	public List<TicketDTO> viewTicketBookedByUserId(int userId) throws TicketNotFoundException{
        logger.info("Ticket Service Implementation - Fetching Tickets for user ID: {}", userId);
		List<Ticket> ticketTemp=ticketRepository.getAllTicketByUserId(userId);
		List<TicketDTO> ticketDto=new ArrayList<>();
		for(Ticket ticket:ticketTemp) {
			ticketDto.add(new TicketDTO(ticket.getTicketId(),ticket.getTravelDate(),ticket.getEmail(),ticket.getTotalAmount(),ticket.getNumberOfPassengers()));
		}
        logger.info("Ticket Service Implementation - Tickets fetched successfully for user ID: {}", userId);

		return ticketDto;
	}

	@Override
	public List<TicketDTO> getAllTicketsByFlightOwnerId(int flightOwnerId) throws TicketNotFoundException {
        logger.info("Ticket Service Implementation - Fetching all tickets for flight owner ID: {}", flightOwnerId);

		List<Ticket> ticketTemp=ticketRepository.getAllTicketsByFlightOwnerId(flightOwnerId);
		List<TicketDTO> ticketDto=new ArrayList<>();
		for(Ticket ticket:ticketTemp) {
			ticketDto.add(new TicketDTO(ticket.getTicketId(),ticket.getTravelDate(),ticket.getEmail(),ticket.getTotalAmount(),ticket.getNumberOfPassengers()));
		}
        logger.info("Ticket Service Implementation - All tickets fetched successfully for flight owner ID: {}. Count: {}", flightOwnerId, ticketDto.size());

		return ticketDto;
	}

	@Override
	public int getFlightIdByTicketId(int ticketId) {
		
		Ticket ticket=ticketRepository.findById(ticketId).orElse(new Ticket());
		Flight flight=ticket.getFlight();
		return flight.getFlightId();
	}

	@Override
	public boolean sendEmailOnBooking(int ticketId) {
		
		Ticket ticket=ticketRepository.findById(ticketId).orElse(new Ticket());
		String subject = "Ticket confirmation";
		String text="Hi " + "Your ticket has been successfully booked.\n"
				+ "Your Ticket Id is " + ticket.getTicketId() + ".\n " + "Source : " + ticket.getFlight().getSource()
				+ " Destination : " + ticket.getFlight().getDestination() + "\n" + "Departure Time : "
				+ ticket.getFlight().getTimeOfDeparture() + " Arrival Time : " + ticket.getFlight().getTimeOfArrival()
				+ "\n";
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(ticket.getEmail());
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);
    
        return true;
	}

}
