package com.hexaware.airticketbooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PassengerNotFoundException extends ResponseStatusException {

	public PassengerNotFoundException(HttpStatus status,String msg) {
		super(status,msg);   

}
}