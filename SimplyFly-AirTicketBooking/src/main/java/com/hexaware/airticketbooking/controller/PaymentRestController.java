package com.hexaware.airticketbooking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.airticketbooking.dto.PaymentDTO;
import com.hexaware.airticketbooking.services.IPaymentService;
/*
 * Author: UdayKiran
 * LastModifiedDate: 19-11-2023
 * Description: This class represents an Payement Rest Controller, which handles HTTP requests to get payment history.
 */
@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin
public class PaymentRestController {
	@Autowired
	IPaymentService service;
	Logger logger = LoggerFactory.getLogger(PaymentRestController.class);

	@GetMapping("/history/{userId}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	public List<PaymentDTO> viewPaymentByTicketId(@PathVariable int userId){
        logger.info("Received request to view payment history for ticketId: {}", userId);
        return service.viewPaymentHistoryByUserId(userId);
	}
}

