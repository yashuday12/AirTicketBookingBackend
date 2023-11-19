package com.hexaware.airticketbooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSeatsAvailableException extends ResponseStatusException {

		public NoSeatsAvailableException(HttpStatus status,String msg) {
        		super(status,msg);   
	}
}
