package com.hexaware.airticketbooking.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.airticketbooking.dto.FlightOwnerDTO;
import com.hexaware.airticketbooking.dto.UpdateFlightOwnerDTO;
import com.hexaware.airticketbooking.entities.FlightOwner;

import com.hexaware.airticketbooking.exceptions.FlightOwnerNotFoundException;
import com.hexaware.airticketbooking.repository.IFlightOwnerRepository;
/*
 * Author: Yashwanth
 * LastModifiedDate: 19-11-2023
 * Description: This Service class interacts  the FlightOwnerRepository and ensures business logic related to FlightOwner
 * 
 */
@Service
public class FlightownerServiceImp implements IFlightOwnerService {

	private IFlightOwnerRepository flightOwnerRepository;
    private  PasswordEncoder passwordEncoder;
	
    public FlightownerServiceImp(IFlightOwnerRepository flightOwnerRepository, PasswordEncoder passwordEncoder) {
		super();
		this.flightOwnerRepository = flightOwnerRepository;
		this.passwordEncoder = passwordEncoder;
	}
    Logger logger = LoggerFactory.getLogger(FlightownerServiceImp.class);
	@Override
	public FlightOwnerDTO addFlightOwner(FlightOwnerDTO flightOwnerDto) {
		
		FlightOwner flightOwner=new FlightOwner();
		flightOwner.setFlightOwnerName(flightOwnerDto.getFlightOwnerName());
		flightOwner.setFlightOwnerPassword(passwordEncoder.encode(flightOwnerDto.getFlightOwnerPassword()));
		flightOwner.setFlightOwnerContact(flightOwnerDto.getFlightOwnerContact());
		flightOwner.setFlightOwnerEmail(flightOwnerDto.getFlightOwnerEmail());
		
		FlightOwner flightOwnerTemp=flightOwnerRepository.save(flightOwner);
        logger.info("Flight Owner Service Implementation - Added Flight Owner data into the database successfully");
		return new FlightOwnerDTO(flightOwnerTemp.getFlightOwnerId(),flightOwnerTemp.getFlightOwnerPassword(),flightOwnerTemp.getFlightOwnerName(),flightOwnerTemp.getFlightOwnerContact(),flightOwnerTemp.getFlightOwnerEmail());
	}

	@Override
	public UpdateFlightOwnerDTO editFlightOwnerProfile(UpdateFlightOwnerDTO flightOwnerDto,int flightOwnerId) {
		FlightOwner flightOwner=flightOwnerRepository.findById(flightOwnerId).orElse(new FlightOwner());
		flightOwner.setFlightOwnerId(flightOwnerDto.getFlightOwnerId());
		flightOwner.setFlightOwnerName(flightOwnerDto.getFlightOwnerName());
		flightOwner.setFlightOwnerContact(flightOwnerDto.getFlightOwnerContact());
		flightOwner.setFlightOwnerEmail(flightOwnerDto.getFlightOwnerEmail());
		FlightOwner flightOwnerTemp=flightOwnerRepository.save(flightOwner);
        logger.info("Flight Owner Service Implementation - Updated the Flight Owner data in the existing record in the database successfully");
        return new UpdateFlightOwnerDTO(flightOwnerTemp.getFlightOwnerId(),flightOwnerTemp.getFlightOwnerName(),flightOwnerTemp.getFlightOwnerContact(),flightOwnerTemp.getFlightOwnerEmail());
	}

	@Override
	public void deleteFlightOwner(int flightOwnerId) {
        logger.info("Flight Owner Data with ID :{} deleted successfully in the Flight Owner Service Implementation", flightOwnerId);
		flightOwnerRepository.deleteById(flightOwnerId);
	}

	@Override
	public UpdateFlightOwnerDTO getFlightOwnerDetailsById(int flightOwnerId)throws FlightOwnerNotFoundException  {
		
		FlightOwner flightOwner=flightOwnerRepository.findById(flightOwnerId).orElse(new FlightOwner());
        logger.info("Flight Owner Service Implementation - Fetching Flight Owner with ID :{}", flightOwnerId);
		UpdateFlightOwnerDTO flightOwnerDto=new UpdateFlightOwnerDTO();
		flightOwnerDto.setFlightOwnerId(flightOwner.getFlightOwnerId());
		flightOwnerDto.setFlightOwnerName(flightOwner.getFlightOwnerName());
		flightOwnerDto.setFlightOwnerEmail(flightOwner.getFlightOwnerEmail());
		flightOwnerDto.setFlightOwnerContact(flightOwner.getFlightOwnerContact());
		return flightOwnerDto;
	}

	@Override
	public List<FlightOwnerDTO> viewAllFlightOwners()throws FlightOwnerNotFoundException {
        logger.info("Flight Owner Service Implementation - Fetching all Flight Owners");
		List<FlightOwner>flightOwner=flightOwnerRepository.findAll();
		List<FlightOwnerDTO> flightOwnerDto=new ArrayList();
		for (FlightOwner flightOwner2 : flightOwner) {
		    FlightOwnerDTO flightOwnerTemp=new FlightOwnerDTO();
		    flightOwnerTemp.setFlightOwnerId(flightOwner2.getFlightOwnerId());
		    flightOwnerTemp.setFlightOwnerName(flightOwner2.getFlightOwnerName());
		    flightOwnerTemp.setFlightOwnerContact(flightOwner2.getFlightOwnerContact());
		    flightOwnerTemp.setFlightOwnerEmail(flightOwner2.getFlightOwnerEmail());
		    flightOwnerTemp.setFlightOwnerPassword(flightOwner2.getFlightOwnerPassword());
		    flightOwnerDto.add(flightOwnerTemp);   
		}
		return flightOwnerDto;
	}

	@Override
	public boolean verifyownerpassword(String password, int flightOwnerId) {
		FlightOwner flightOwner=flightOwnerRepository.findById(flightOwnerId).orElse(new FlightOwner());
		boolean flag=false;
		if(passwordEncoder.matches(password, flightOwner.getFlightOwnerPassword())) {
		  flag=true;
	    }
	    else {
	    	flag=false;
	    }
		return flag;
	   }

	@Override
	public FlightOwnerDTO changeOwnerPassword(int flightOwnerId, String password) {
		FlightOwner flightOwnerTemp=flightOwnerRepository.findById(flightOwnerId).orElse(new FlightOwner());
		 
		flightOwnerTemp.setFlightOwnerPassword(passwordEncoder.encode(password));
		FlightOwner flightOwner =flightOwnerRepository.save(flightOwnerTemp);
		FlightOwnerDTO flightOwnerDto=new FlightOwnerDTO();
		flightOwnerDto.setFlightOwnerId(flightOwner.getFlightOwnerId());
		flightOwnerDto.setFlightOwnerName(flightOwner.getFlightOwnerName());
		flightOwnerDto.setFlightOwnerEmail(flightOwner.getFlightOwnerEmail());
		flightOwnerDto.setFlightOwnerContact(flightOwner.getFlightOwnerContact());
		return flightOwnerDto;
	}

}


