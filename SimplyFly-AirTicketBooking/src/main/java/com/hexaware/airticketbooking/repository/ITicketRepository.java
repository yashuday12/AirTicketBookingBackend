package com.hexaware.airticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.airticketbooking.entities.Ticket;
/*
 * Author:Uday Kiran
 * LastModifiedDate:15-11-2023
 * Description: It is Ticket Repository which extends JPA Repsoitory to perform crud operation
 */
@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Integer> {

	@Query("select t from Ticket t where t.user.userId=:userId")
	public List<Ticket> getAllTicketByUserId(@Param("userId")int userId);
	
	@Query(" select t from Ticket t where t.flight.flightId In (select f.flightId from Flight f where f.flightOwner.flightOwnerId=:flightOwnerId)")
	public List<Ticket> getAllTicketsByFlightOwnerId(@Param("flightOwnerId") int flightOwnerId );
	
	
	
}
