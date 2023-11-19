package com.hexaware.airticketbooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FlightOwnerNotFoundException  extends ResponseStatusException {

	public FlightOwnerNotFoundException(HttpStatus status,String msg) {
		super(status,msg);   

}
}
