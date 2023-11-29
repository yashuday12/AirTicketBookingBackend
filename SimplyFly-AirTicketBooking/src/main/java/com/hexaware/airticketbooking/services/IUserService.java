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
	public UserDTO registerUser(UserDTO userDto);// It is used to register userdetails. It is similar to signup page
	public UserDTO editUserProfile(UserDTO userDto);// This method can be used to edit user details.
	public void deleteUserProfile(int userId);//User profile can be deleted
	public UserDTO getByUserId(int userId) throws UserNotFoundException;//we can fetch details by using userid
	public List<UserDTO> getAllUsers() throws UserNotFoundException; // It is used to fetch all user details
	public long rechargeWallet(int userId ,long amount);// If a user wanted to recharge his wallet,they can use this method
	public UserDTO changePassword(int userId, String password); //User can change his password
	public void sendEmailOnRegistration(User user);//After registration the user details are sent to his/her email
    public String getLoginRole(String name);
    public int getLoginId(String name);
}
