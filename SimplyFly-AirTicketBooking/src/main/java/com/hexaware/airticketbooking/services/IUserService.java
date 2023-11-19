package com.hexaware.airticketbooking.services;

import java.util.List;

import com.hexaware.airticketbooking.dto.UserDTO;
import com.hexaware.airticketbooking.entities.User;
import com.hexaware.airticketbooking.exceptions.UserNotFoundException;
/*
 * Author: Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This is Service Interface of user. Abstract methods are declared and they are implemented in service implementation classes. */

public interface IUserService {
	public UserDTO registerUser(UserDTO userDto);
	public UserDTO editUserProfile(UserDTO userDto);
	public void deleteUserProfile(int userId);
	public UserDTO getByUserId(int userId) throws UserNotFoundException;
	public List<User> getAllUsers() throws UserNotFoundException;
	public long rechargeWallet(int userId ,long Ammount);
	public UserDTO changePassword(int userId, String password); 
	public void sendEmailOnRegistration(User user);

}
