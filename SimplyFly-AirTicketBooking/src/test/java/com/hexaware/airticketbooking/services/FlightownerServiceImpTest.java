package com.hexaware.airticketbooking.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hexaware.airticketbooking.dto.FlightOwnerDTO;
import com.hexaware.airticketbooking.dto.UpdateFlightOwnerDTO;
import com.hexaware.airticketbooking.entities.FlightOwner;
import com.hexaware.airticketbooking.exceptions.FlightOwnerNotFoundException;
import com.hexaware.airticketbooking.repository.IFlightOwnerRepository;
@SpringBootTest
class FlightownerServiceImpTest {
     @Mock
	 private IFlightOwnerRepository flightOwnerRepository;

	 @Mock
	 private PasswordEncoder passwordEncoder;

	 @InjectMocks
	 private FlightownerServiceImp flightownerService;

	 @BeforeEach
	 void setUp() {
	     MockitoAnnotations.openMocks(this);
	  }


	 @Test
	    void testAddFlightOwner() {
	        FlightOwnerDTO flightOwnerDto = new FlightOwnerDTO(0,"owner1", "password", "contact", "email");
	        FlightOwner flightOwner = new FlightOwner();
	        flightOwner.setFlightOwnerId(1);
	        flightOwner.setFlightOwnerName(flightOwnerDto.getFlightOwnerName());
	        flightOwner.setFlightOwnerPassword("encodedPassword");
	        flightOwner.setFlightOwnerContact(flightOwnerDto.getFlightOwnerContact());
	        flightOwner.setFlightOwnerEmail(flightOwnerDto.getFlightOwnerEmail());

	        when(passwordEncoder.encode(flightOwnerDto.getFlightOwnerPassword())).thenReturn("encodedPassword");
	        when(flightOwnerRepository.save(any())).thenReturn(flightOwner);

	        FlightOwnerDTO result = flightownerService.addFlightOwner(flightOwnerDto);

	        assertNotNull(result);
	        assertEquals(flightOwner.getFlightOwnerName(), result.getFlightOwnerName());
	        assertEquals(flightOwner.getFlightOwnerPassword(), result.getFlightOwnerPassword());
	        assertEquals(flightOwner.getFlightOwnerContact(), result.getFlightOwnerContact());
	        assertEquals(flightOwner.getFlightOwnerEmail(), result.getFlightOwnerEmail());
	    }

	    @Test
	    void testEditFlightOwnerProfile() {
	    	 UpdateFlightOwnerDTO flightOwnerDto = new UpdateFlightOwnerDTO();
	    	 flightOwnerDto.setFlightOwnerId(1);
	         FlightOwner flightOwner = new FlightOwner();
	         flightOwner.setFlightOwnerId(flightOwnerDto.getFlightOwnerId());
	         flightOwner.setFlightOwnerName(flightOwnerDto.getFlightOwnerName());
	         flightOwner.setFlightOwnerContact(flightOwnerDto.getFlightOwnerContact());
	         flightOwner.setFlightOwnerEmail(flightOwnerDto.getFlightOwnerEmail());

	         when(flightOwnerRepository.save(any())).thenReturn(flightOwner);

	         UpdateFlightOwnerDTO result = flightownerService.editFlightOwnerProfile(flightOwnerDto,1);

	         assertNotNull(result);
	         assertEquals(flightOwner.getFlightOwnerId(), result.getFlightOwnerId());
	         assertEquals(flightOwner.getFlightOwnerName(), result.getFlightOwnerName());
	         assertEquals(flightOwner.getFlightOwnerContact(), result.getFlightOwnerContact());
	         assertEquals(flightOwner.getFlightOwnerEmail(), result.getFlightOwnerEmail());
	    }

	    @Test
	    void testDeleteFlightOwner() {
	        int flightOwnerId = 1;

	        flightownerService.deleteFlightOwner(flightOwnerId);
	       verify(flightOwnerRepository, times(1)).deleteById(flightOwnerId);
	    }

	    @Test
	    void testGetFlightOwnerDetailsById() throws FlightOwnerNotFoundException {
	    	 int flightOwnerId = 1;
	         FlightOwner flightOwner = new FlightOwner();
	          flightOwner.setFlightOwnerId(flightOwnerId);
	         flightOwner.setFlightOwnerName("owner1");
	         flightOwner.setFlightOwnerContact("contact");
	         flightOwner.setFlightOwnerEmail("email");

	         when(flightOwnerRepository.findById(flightOwnerId)).thenReturn(Optional.of(flightOwner));

	        
	         UpdateFlightOwnerDTO result = flightownerService.getFlightOwnerDetailsById(flightOwnerId);

	        
	         assertNotNull(result);
	         assertEquals(flightOwner.getFlightOwnerId(), result.getFlightOwnerId());
	         assertEquals(flightOwner.getFlightOwnerName(), result.getFlightOwnerName());
	         assertEquals(flightOwner.getFlightOwnerContact(), result.getFlightOwnerContact());
	         assertEquals(flightOwner.getFlightOwnerEmail(), result.getFlightOwnerEmail());

	         
	         verify(flightOwnerRepository, times(1)).findById(flightOwnerId);}
	    @Test
	    void testViewAllFlightOwners() {
	        List<FlightOwner> flightOwners = Arrays.asList(new FlightOwner(), new FlightOwner());

	        when(flightOwnerRepository.findAll()).thenReturn(flightOwners);

	        List<FlightOwnerDTO> result = flightownerService.viewAllFlightOwners();

	        assertNotNull(result);
	        assertEquals(flightOwners.size(), result.size());
	    }

}
