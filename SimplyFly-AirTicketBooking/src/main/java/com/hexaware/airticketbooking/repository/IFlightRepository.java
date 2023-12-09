package com.hexaware.airticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.airticketbooking.entities.Flight;
/*
 * Author:Uday Kiran
 * LastModifiedDate:15-11-2023
 * Description: It is Flight Repository which extends JPA Repsoitory to perform crud operation
 */
@Repository
public interface IFlightRepository extends JpaRepository<Flight, Integer>{
	
	
	public List<Flight> findBySourceAndDestination(String source,String destination);
     
	
	@Query("select f from Flight f where f.flightOwner.flightOwnerId=:flightOwnerId")
	public List<Flight> getFlightsByFlightOwnerId(@Param("flightOwnerId") int flightOwnerId);
}
