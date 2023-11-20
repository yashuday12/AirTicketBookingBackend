package com.hexaware.airticketbooking.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hexaware.airticketbooking.dto.PaymentDTO;
import com.hexaware.airticketbooking.entities.Payment;
import com.hexaware.airticketbooking.repository.IPaymentRepository;
/*
* Author: Yashwanth and UdayKiran
* LastModifiedDate: 19-11-2023
* Description: This Service class interacts  the PaymentRepository and ensures business logic related to all payments
* 
*/
@Service
public class PaymentService implements IPaymentService {
	private IPaymentRepository paymentRepository;
	
	

	public PaymentService(IPaymentRepository paymentRepository) {
		super();
		this.paymentRepository = paymentRepository;
	}

    Logger logger = LoggerFactory.getLogger(PaymentService.class);

	@Override
	public List<PaymentDTO> viewPaymentHistoryByUserId(int userId) {
        logger.info("Payment Service - Viewing payment details for User ID: {}", userId);
		List<Payment> paymentTemp=paymentRepository.viewPaymenHistoryByUserId(userId);
		List<PaymentDTO> paymentDtoTemp=new ArrayList<>();
		for (Payment payment : paymentTemp) {
			PaymentDTO paymentDto=new PaymentDTO();
			paymentDto.setReferenceNumber(payment.getReferenceNumber());
			paymentDto.setAmountPaid(payment.getAmountToBePaid());
			paymentDto.setPaymentDate(payment.getPaymentDate());
			paymentDto.setStatus(payment.getStatus());
			paymentDtoTemp.add(paymentDto);
		}
        logger.info("Payment Service - Payment details retrieved successfully for User ID: {}", userId);
		return paymentDtoTemp;		
	}


}
