package com.hexaware.airticketbooking.services;

import java.util.List;

import com.hexaware.airticketbooking.dto.PaymentDTO;
/*
 * Author: Uday Kiran and Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This is Service Interface of Payment. Abstract methods are declared and they are implemented in service implementation classes. */

public interface IPaymentService {
	
	
	public List<PaymentDTO> viewPaymentHistoryByUserId(int userId);
	
}
