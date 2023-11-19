package com.hexaware.airticketbooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
   
	@ExceptionHandler({AdminNotFoundException.class,UserNotFoundException.class,FlightOwnerNotFoundException.class,PassengerNotFoundException.class,TicketNotFoundException.class,FlightNotFoundException.class,InsufficientBalanceException.class,NullPointerException.class,NoSeatsAvailableException.class,UsernameNotFoundException.class})
	public ResponseEntity<String> handleAnyExp(Exception e) {
		
		return new ResponseEntity<>(e.toString(),HttpStatus.NOT_FOUND);
	}

}
