package com.hexaware.airticketbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hexaware.airticketbooking.entities.User;
/*
 * Author:Yashwanth
 * LastModifiedDate:15-11-2023
 * Description: It is User Repository which extends JPA Repsoitory to perform crud operation
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{

	
	 Optional<User> findByUserName(String userName);
	public User findByUserNameAndPassword( String userName, String password);
}
