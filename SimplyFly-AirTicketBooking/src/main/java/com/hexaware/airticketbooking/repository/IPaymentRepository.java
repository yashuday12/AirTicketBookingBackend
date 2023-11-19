package com.hexaware.airticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.airticketbooking.entities.Payment;
/*
 * Author:Uday Kiran and Yashwanth
 * LastModifiedDate:18-11-2023
 * Description: It is Payment Repository which extends JPA Repsoitory to perform crud operation
 */
@Repository
public interface IPaymentRepository extends JpaRepository<Payment,Integer >{

	@Query("select p from Payment p where p.user.userId=:userId")
	public List<Payment> viewPaymenHistoryByUserId(@Param("userId") int userId);
	
	

}
