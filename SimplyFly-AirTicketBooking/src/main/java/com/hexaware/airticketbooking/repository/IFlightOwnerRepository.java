package com.hexaware.airticketbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hexaware.airticketbooking.entities.FlightOwner;

/*
 * Author:Yashwanth
 * LastModifiedDate:15-11-2023
 * Description: It is FlightOwner Repository which extends JPA Repsoitory to perform crud operation
 */
@Repository
public interface IFlightOwnerRepository extends JpaRepository<FlightOwner, Integer>{
	public FlightOwner findByFlightOwnerNameAndFlightOwnerPassword( String flightOwnerName, String flightOwnerPassword);
	Optional<FlightOwner> findByFlightOwnerName(String flightownerName);
	@Query("select f.ROLES from FlightOwner f where f.flightOwnerName=:name")
	public String getRoleByFlightOwnerName(@Param("name") String flightOwnerName);
	
} 