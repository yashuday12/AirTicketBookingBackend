package com.hexaware.airticketbooking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.airticketbooking.config.AdminInfoUserDetails;
import com.hexaware.airticketbooking.config.FlightOwnerInfoUserDetails;
import com.hexaware.airticketbooking.config.UserInfoUserDetails;
import com.hexaware.airticketbooking.dto.UserDTO;
import com.hexaware.airticketbooking.entities.Admin;
import com.hexaware.airticketbooking.entities.FlightOwner;
import com.hexaware.airticketbooking.entities.User;
import com.hexaware.airticketbooking.exceptions.UserNotFoundException;
import com.hexaware.airticketbooking.repository.IAdminRepository;
import com.hexaware.airticketbooking.repository.IFlightOwnerRepository;
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
	private IAdminRepository adminRepository;
	private IFlightOwnerRepository flightOwnerRepository;
	public UserServiceImp(PasswordEncoder passwordEncoder, IUserRepository userRepository,IAdminRepository adminRepository,IFlightOwnerRepository flightOwnerRepository) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.adminRepository=adminRepository;
		this.flightOwnerRepository=flightOwnerRepository;
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
		UserDTO userDto=new UserDTO();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setGender(user.getGender());
		userDto.setContactNumber(user.getContactNumber());
		userDto.setAddress(user.getAddress());
		userDto.setDateOfBirth(user.getDateOfBirth());
		userDto.setUserEmail(user.getUserEmail());
		 logger.info("User Service Implementation - User fetched successfully. User ID: {}", user.getUserId());
	     logger.info("Exiting getByUserId method with userId: {}", userId);
        return userDto;
		
	}

	@Override
	public List<UserDTO> getAllUsers() throws UserNotFoundException{
		logger.info("User Service Implementation-Fetching all users from the repository");
		List<User> user=userRepository.findAll();
		List<UserDTO> userDto=new ArrayList<>();
		for(User userTemp:user) {
			UserDTO userDtoTemp=new UserDTO();
			userDtoTemp.setUserId(userTemp.getUserId());
			userDtoTemp.setAddress(userTemp.getAddress());
			userDtoTemp.setContactNumber(userTemp.getContactNumber());
			userDtoTemp.setGender(userTemp.getGender());
			userDtoTemp.setDateOfBirth(userTemp.getDateOfBirth());
			userDtoTemp.setUserEmail(userTemp.getUserEmail());
			userDtoTemp.setUserName(userTemp.getUserName());
			userDtoTemp.setPassword(userTemp.getPassword());
			userDtoTemp.setWallet(userTemp.getWallet());
			userDto.add(userDtoTemp);	
		}
		return userDto;
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
	public long rechargeWallet(int userId, long amount) {
		User user=userRepository.findById(userId).orElse(new User());
		user.setWallet(user.getWallet()+amount);
		User updatedUser=userRepository.save(user);
		return updatedUser.getWallet();
	}

	@Override
	public String getLoginRole(String name) {
		String role;
		System.out.println(name);
		String adminInfo = adminRepository.getRoleByAdminName(name);

	    if (adminInfo!=null) {
	        role=adminInfo;
	    }
	    else {
	        String userInfo = userRepository.getRoleByUserName(name);

	        if (userInfo!=null) {
	        	role=userInfo;
	        }
	           
	        else {
	            String ownerInfo = flightOwnerRepository.getRoleByFlightOwnerName(name);

	            if (ownerInfo!=null) {
	            	role=ownerInfo;
	            }   
	            else {
	                throw new UsernameNotFoundException("User not found: " + name);
	            }
	        }
	    
	    }
	    return role;
	}
	@Override
	public int getLoginId(String name) {
		int id;
		Optional<Admin> adminInfo = adminRepository.findByAdminName(name);
      
		if (adminInfo.isPresent()) {
            Admin admin = adminInfo.get();
            id=admin.getAdminId();
        } else {
            Optional<User> userInfo = userRepository.findByUserName(name);

            if (userInfo.isPresent()) {
                User user = userInfo.get();
                id=user.getUserId();
            } else {
                Optional<FlightOwner> ownerInfo = flightOwnerRepository.findByFlightOwnerName(name);

                if (ownerInfo.isPresent()) {
                    FlightOwner owner = ownerInfo.get();
                    id=owner.getFlightOwnerId();
                } else {
                    throw new UsernameNotFoundException("User not found: " + name);
                }
            }
        }
		return id;
    }
	}


