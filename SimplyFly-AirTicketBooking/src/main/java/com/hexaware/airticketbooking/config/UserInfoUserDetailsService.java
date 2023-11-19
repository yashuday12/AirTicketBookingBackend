package com.hexaware.airticketbooking.config;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hexaware.airticketbooking.entities.Admin;
import com.hexaware.airticketbooking.entities.FlightOwner;
import com.hexaware.airticketbooking.entities.User;
import com.hexaware.airticketbooking.repository.IAdminRepository;
import com.hexaware.airticketbooking.repository.IFlightOwnerRepository;
import com.hexaware.airticketbooking.repository.IUserRepository;


@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    
	@Autowired 
	IAdminRepository adminRepository;
	@Autowired 
	IUserRepository userRepository;
	@Autowired 
	IFlightOwnerRepository flightOwnerRepository;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> adminInfo = adminRepository.findByAdminName(username);

	    if (adminInfo.isPresent()) {
	        return adminInfo.map(AdminInfoUserDetails::new)
	                .orElseThrow(() -> new UsernameNotFoundException("Admin not found: " + username));
	    }
	    else {
	        Optional<User> userInfo = userRepository.findByUserName(username);

	        if (userInfo.isPresent()) {
	            return userInfo.map(UserInfoUserDetails::new)
	                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	        } 
	        else {
	            Optional<FlightOwner> ownerInfo = flightOwnerRepository.findByFlightOwnerName(username);

	            if (ownerInfo.isPresent()) {
	                return ownerInfo.map(FlightOwnerInfoUserDetails::new)
	                        .orElseThrow(() -> new UsernameNotFoundException("Owner not found: " + username));
	            } 
	            else {
	                throw new UsernameNotFoundException("User not found: " + username);
	            }
	        }
	    }
	}
}



