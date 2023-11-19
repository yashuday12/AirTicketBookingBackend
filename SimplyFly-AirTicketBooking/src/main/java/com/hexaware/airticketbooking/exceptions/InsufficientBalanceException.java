package com.hexaware.airticketbooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InsufficientBalanceException extends ResponseStatusException {
	
	public InsufficientBalanceException(HttpStatus status,String msg) {
		super(status,msg); }


}
