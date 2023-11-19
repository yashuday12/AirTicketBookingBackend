package com.hexaware.airticketbooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TicketNotFoundException extends ResponseStatusException
{

		public TicketNotFoundException(HttpStatus status,String msg) {
			super(status,msg);   

	}
}

