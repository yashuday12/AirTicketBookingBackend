package com.hexaware.airticketbooking.services;

import java.util.List;

import com.hexaware.airticketbooking.dto.TicketDTO;
import com.hexaware.airticketbooking.dto.TicketPassengerVO;
import com.hexaware.airticketbooking.exceptions.TicketNotFoundException;
/*
 * Author: Uday Kiran
 * LastModifiedDate:19-11-2023
 * Description: This is Service Interface of Ticket. Abstract methods are declared and they are implemented in service implementation classes. */

public interface ITicketService {
	public TicketDTO bookAirTicket(TicketPassengerVO ticketPassengerVo ,String userName,int flightId);
	public void cancelTicket(int ticketId);
	public TicketDTO fetchBookingDetailsByTicketId(int ticketId)throws TicketNotFoundException;
	public List<TicketDTO> veiwTicketDetails() throws TicketNotFoundException;
	public List<TicketDTO> viewTicketBookedByUserId(int userId)throws TicketNotFoundException;
	public List<TicketDTO> getAllTicketsByFlightOwnerId(int flightOwnerId ) throws TicketNotFoundException;

	

}
