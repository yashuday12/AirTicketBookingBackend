package com.hexaware.airticketbooking.dto;

import java.util.List;

/*
 * Author: UdayKiran
 * LastModifiedDate:19-11-2023
 * Description: This is VO Class to handle both ticket and passenger details.
 */
public class TicketPassengerVO {
	private TicketDTO ticketDto;
	private List<PassengerDTO> passengerDto;
	public TicketPassengerVO(TicketDTO ticketDto, List<PassengerDTO> passengerDto) {
		super();
		this.ticketDto = ticketDto;
		this.passengerDto = passengerDto;
	}
	
	public TicketPassengerVO() {
		super();
	}

	public TicketDTO getTicketDto() {
		return ticketDto;
	}
	public void setTicketDto(TicketDTO ticketDto) {
		this.ticketDto = ticketDto;
	}
	public List<PassengerDTO> getPassengerDto() {
		return passengerDto;
	}
	public void setPassengerDto(List<PassengerDTO> passengerDto) {
		this.passengerDto = passengerDto;
	}

	@Override
	public String toString() {
		return "TicketPassengerVO [ticketDto=" + ticketDto + ", passengerDto=" + passengerDto + "]";
	}

	
}
