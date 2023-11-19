package com.hexaware.airticketbooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.airticketbooking.entities.Passenger;
/*
 * Author:Uday Kiran
 * LastModifiedDate:15-11-2023
 * Description: It is Passenger Repository which extends JPA Repsoitory to perform crud operation
 */
@Repository
public interface IPassengerRepository extends JpaRepository<Passenger, Integer>{
	@Query("select p from Passenger p  where p.ticket.ticketId=:ticketId")
	public List<Passenger> getPassengerByTicketId(@Param("ticketId")int ticketId);
	
	
	
	@Query("select p.passengerSeatNumber from Passenger p where p.ticket.ticketId In (select t.ticketId from Ticket t where t.travelDate=:date and t.flight.flightId=:fid)")
	public List<String> fetchBookedSeats(@Param("date")LocalDate travelDate, @Param("fid") int flightId);
	

}
