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
	public TicketDTO bookAirTicket(TicketPassengerVO ticketPassengerVo ,int userId,int flightId);//user can book a ticket and can add passenger details
	public void cancelTicket(int ticketId);//user can cancel the ticket i.e user can delete the ticket
	public TicketDTO fetchBookingDetailsByTicketId(int ticketId)throws TicketNotFoundException;// ticket details are fetched by using ticketid
	public List<TicketDTO> veiwTicketDetails() throws TicketNotFoundException;// We can fetch all ticket details booked by all users
	public List<TicketDTO> viewTicketBookedByUserId(int userId)throws TicketNotFoundException;// user can fetch his/her tickets by giving userId as refernce
	public List<TicketDTO> getAllTicketsByFlightOwnerId(int flightOwnerId ) throws TicketNotFoundException;//flightowner can view all ticketdetails that are booked under flights addded by flightowner 
    public int getFlightIdByTicketId(int ticketId);
	
    public boolean sendEmailOnBooking(int ticketId);
}
