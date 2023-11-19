package com.hexaware.airticketbooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FlightNotFoundException extends ResponseStatusException {

		public FlightNotFoundException(HttpStatus status,String msg) {
			super(status,msg);   

	}
}
