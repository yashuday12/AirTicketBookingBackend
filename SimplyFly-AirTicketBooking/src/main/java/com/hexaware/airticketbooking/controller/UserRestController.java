
package com.hexaware.airticketbooking.controller;
/*
 * Author: Yashwanth
 * LastModifiedDate: 19-11-2023
 * Description: This class represents an User Rest Controller, which handles HTTP requests related to Users.
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.airticketbooking.dto.UpdateUserDTO;
import com.hexaware.airticketbooking.dto.UserDTO;

import com.hexaware.airticketbooking.exceptions.UserNotFoundException;

import com.hexaware.airticketbooking.services.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins="http://localhost:4200/")
public class UserRestController {
	
	private IUserService userService;
	Logger logger = LoggerFactory.getLogger(UserRestController.class);

	public UserRestController(IUserService userService) {
		super();
		this.userService = userService;
	}
	@PostMapping("/adduser")
	public UserDTO registerUser(@RequestBody @Valid UserDTO userDto) {
        logger.info("Received request to register user ");
return userService.registerUser(userDto);
		
	}
	@PutMapping("/updateuser/{userId}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	public UpdateUserDTO editUserProfile(@RequestBody @Valid UpdateUserDTO userDto,@PathVariable int userId) {
		
		return userService.editUserProfile(userDto,userId);
		
	}
	@DeleteMapping("/deleteuserbyid/{userId}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<String> deleteUserProfile(@PathVariable int userId) {
        logger.info("Received request to delete user profile for userId: {}", userId);
        userService.deleteUserProfile(userId);
		return new ResponseEntity<>("USER deleted sucessfully", HttpStatus.ACCEPTED);
	}
	@GetMapping("/getuserbyid/{userId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public UserDTO getByUserId(@PathVariable int userId) {
        logger.info("Received request to get user details for userId: {}", userId);
        UserDTO user= userService.getByUserId(userId);
		if (user.getUserId()!=userId) {
            logger.info("User details not found for userId: {}", userId);
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "userId with userId :"+userId+"not found");
		}
		return user;
	}
	@GetMapping("/getallusers")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<UserDTO> getAllUsers() {
        logger.info("Received request to get all registered users");
        List<UserDTO> userDto= userService.getAllUsers();
		if (userDto.isEmpty()) {
            logger.info("No registered users found");
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "There are no registered users");
		}
		return userDto;
	}
	@PutMapping("/changeuserpassword/{userId}/{password}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	public UserDTO changeUserPassword(@PathVariable int userId,@PathVariable String password) {
        logger.info("Received request to change password for userId: {}", userId);
        return userService.changeUserPassword(userId, password) ;
		
	}
	@PutMapping("/rechargewallet/{userId}/{amount}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	public UserDTO rechargeWallet(@PathVariable int userId, @PathVariable long amount) {
		return userService.rechargeWallet(userId, amount);
		
	}
	@GetMapping("/verifyuserpassword/{userId}/{password}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	public boolean verifyUserPassword(@PathVariable int userId, @PathVariable String password) {
		return userService.verifyuserpassword(password, userId);
	}
	@GetMapping("/sendmailonregistration/{userId}")
	public boolean successRegistration(@PathVariable int userId) {
		userService.sendEmailOnRegistration(userId);
		return true;
	}
}
