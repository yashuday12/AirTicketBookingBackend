package com.hexaware.airticketbooking.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.airticketbooking.dto.UserDTO;
import com.hexaware.airticketbooking.entities.User;
import com.hexaware.airticketbooking.exceptions.UserNotFoundException;
import com.hexaware.airticketbooking.repository.IUserRepository;

/*
* Author: Yashwanth
* LastModifiedDate: 19-11-2023
* Description: This Service class interacts  the UserRepository and ensures business logic related to User
* 
*/
@Service
public class UserServiceImp implements IUserService {
    private  PasswordEncoder passwordEncoder;	
	private IUserRepository userRepository;
	
	public UserServiceImp(PasswordEncoder passwordEncoder, IUserRepository userRepository) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}
	 private final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

	

	@Override
	public UserDTO registerUser(UserDTO userDto) {
		logger.info("Entering insertUser method with userDto: {}", userDto);

		User user=new User();
		user.setUserName(userDto.getUserName());
		user.setGender(userDto.getGender());
		user.setContactNumber(userDto.getContactNumber());
		user.setAddress(userDto.getAddress());
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setUserEmail(userDto.getUserEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		user.setWallet(userDto.getWallet());
		User user1=userRepository.save(user);
		logger.info("User Service Implementation - User inserted successfully. User ID: {}", user.getUserId());
        logger.info("Exiting insertUser method with userDto: {}", userDto);

		return new UserDTO(user1.getUserId(),user1.getUserName(),user1.getGender(),user1.getContactNumber(),user1.getAddress(),user1.getDateOfBirth(),user1.getUserEmail(),user1.getPassword(),user1.getWallet());
	}

	@Override
	public UserDTO editUserProfile(UserDTO userDto) {
		 logger.info("Entering updateUser method with userDto: {}", userDto);

		User user=new User();
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setGender(userDto.getGender());
		user.setContactNumber(userDto.getContactNumber());
		user.setAddress(userDto.getAddress());
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setUserEmail(userDto.getUserEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
	
		user.setWallet(userDto.getWallet());
		User user1=userRepository.save(user);
		logger.info("User Service Implementation - User updated successfully. User ID: {}", user.getUserId());
        logger.info("Exiting updateUser method with userDto: {}", userDto);

		return new UserDTO(user1.getUserId(),user1.getUserName(),user1.getGender(),user1.getContactNumber(),user1.getAddress(),user1.getDateOfBirth(),user1.getUserEmail(),user1.getPassword(),user1.getWallet());
	}

	@Override
	public void deleteUserProfile(int userId) {
		logger.info("Entering deleteUser method with userId: {}", userId);

        logger.info("User Service Implementation - Deleting user with ID: {}", userId);
		userRepository.deleteById(userId);
		logger.info("User Service Implementation - User deleted successfully. User ID: {}", userId);
        logger.info("Exiting deleteUser method with userId: {}", userId);
   
	}

	@Override
	public UserDTO getByUserId(int userId)throws UserNotFoundException {
		 logger.info("Entering getByUserId method with userId: {}", userId);

		User user=userRepository.findById(userId).orElse(new User());
		 logger.info("User Service Implementation - User fetched successfully. User ID: {}", user.getUserId());
	        logger.info("Exiting getByUserId method with userId: {}", userId);

		return new UserDTO(user.getUserId(),user.getUserName(),user.getGender(),user.getContactNumber(),user.getAddress(),user.getDateOfBirth(),user.getUserEmail(),user.getPassword(),user.getWallet());

	}

	@Override
	public List<User> getAllUsers() throws UserNotFoundException{
		logger.info("User Service Implementation-Fetching all users from the repository");

		return userRepository.findAll();
	}

	
	@Override
	public UserDTO changePassword(int userId, String password)  {
		 logger.info("Entering changePassword method with userId: {} and password: {}", userId, password);
		 
		User user=userRepository.findById(userId).orElse(null);
		 if (user==null) {
			throw new NullPointerException();
		}
		user.setPassword(password);
		User updatedUser=userRepository.save(user);
		logger.info("User Service Implementation - Password changed successfully. User ID: {}", updatedUser.getUserId());
        logger.info("Exiting changePassword method with userId: {} and password: {}", userId, password);

		return new UserDTO(updatedUser.getUserId(),updatedUser.getUserName(),updatedUser.getGender(),updatedUser.getContactNumber(),updatedUser.getAddress(),updatedUser.getDateOfBirth(),updatedUser.getUserEmail(),updatedUser.getPassword(),updatedUser.getWallet());

	}

	@Override
	public void sendEmailOnRegistration(User user) {
		
		//nothing to do with  repository
	}

	@Override
	public long rechargeWallet(int userId, long Amount) {
		User user=userRepository.findById(userId).orElse(new User());
		user.setWallet(user.getWallet()+Amount);
		User updatedUser=userRepository.save(user);
		return updatedUser.getWallet();
	}

}
