
package com.hexaware.airticketbooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hexaware.airticketbooking.filter.JwtAuthFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	JwtAuthFilter authFilter;

    @Bean
    public UserDetailsService userDetailsService() {       
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {      //normal spring security+JWT
        return http.csrf().disable()
        		 .cors()
        		 .and()
        		 .authorizeHttpRequests()
                .requestMatchers("/api/v1/admin/authenticate","/api/v1/admin/addadmin","/api/v1/user/adduser","/api/v1/user/sendmailonregistration/{userId}","/api/v1/login/adminlogin","/api/v1/flight/addflight/{flightOwnerId}","/api/v1/login/userlogin","/api/v1/login/flightownerlogin","/api/v1/login/getrole/{name}","/api/v1/passenger/fetchbookedseats/{travelDate}/{flightId}","/api/v1/flight/searchflight/{source}/{destination}","/api/v1/login/getid/{name}", "/v3/api-docs/**", "/swagger-ui/**","/swagger-resources/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/api/v1/user/**","/api/v1/admin/**","/api/v1/flightowner/**","/api/v1/flight/**","/api/v1/passenger/**","/api/v1/ticket/**","/api/v1/payment/**")
                .authenticated().and() 
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter,UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){   
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    
    
    
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    	
    	return config.getAuthenticationManager();
    	
    }
}