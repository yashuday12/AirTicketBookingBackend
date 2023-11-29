package com.hexaware.airticketbooking.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.airticketbooking.dto.AuthRequest;
import com.hexaware.airticketbooking.services.IUserService;
import com.hexaware.airticketbooking.services.JwtService;
/*
 * Author: Yashwanth
 * LastModifiedDate: 19-11-2023
 * Description: This class represents an Login Rest Controller, which handles the credentials entered by Admin,User and FlightOwner
 */
@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin(origins="http://localhost:4200")
public class LoginRestController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	Logger logger = LoggerFactory.getLogger(LoginRestController.class);

	@Autowired
	private JwtService jwtService;
	@Autowired
	private IUserService userService;
	
	@PostMapping("/adminlogin")
	public String adminLogin(@RequestBody AuthRequest authRequest) {
        logger.info("Received request for admin login for username: {}", authRequest.getUsername());

		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		String token = null;
		
		if (authenticate.isAuthenticated()) {

			token = jwtService.generateToken(authRequest.getUsername());
            logger.info("Admin login successful for username: {}", authRequest.getUsername());

		}

		else {
            logger.warn("Invalid admin login request for username: {}", authRequest.getUsername());

			throw  new UsernameNotFoundException("Invalid Username or Password / Invalid request");
		}
        logger.info("Generated token: {}", token);
		return token;
	}
	@PostMapping("/flightownerlogin")
	public String flightOwnerLogin(@RequestBody AuthRequest authRequest) {
        logger.info("Received request for flight owner login for username: {}", authRequest.getUsername());

		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		String token = null;
		
		if (authenticate.isAuthenticated()) {

			token = jwtService.generateToken(authRequest.getUsername());
            logger.info("Flight owner login successful for username: {}", authRequest.getUsername());

		}

		else {
            logger.warn("Invalid flight owner login request for username: {}", authRequest.getUsername());

			throw  new UsernameNotFoundException("Invalid Username or Password / Invalid request");
		}
        logger.info("Generated token: {}", token);

		return token;
	}
	@PostMapping("/userlogin")
	public String userLogin(@RequestBody AuthRequest authRequest) {
        logger.info("Received request for user login for username: {}", authRequest.getUsername());

		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		String token = null;
		
		if (authenticate.isAuthenticated()) {

			token = jwtService.generateToken(authRequest.getUsername());
            logger.info("User login successful for username: {}", authRequest.getUsername());

		}

		else {
            logger.warn("Invalid user login request for username: {}", authRequest.getUsername());

			throw  new UsernameNotFoundException("Invalid Username or Password / Invalid request");
		}
        logger.debug("Generated token: {}", token);
		return token;
	}
	@GetMapping("/getrole/{name}")
	public String getLoginRole(@PathVariable String name) {
		return userService.getLoginRole(name);
		 
	}
	
	
	@GetMapping("/getid/{name}")
	public int getLoginId(@PathVariable String name) {
		System.out.println(name+"hi");
		return userService.getLoginId(name);
		 
	}	
}
