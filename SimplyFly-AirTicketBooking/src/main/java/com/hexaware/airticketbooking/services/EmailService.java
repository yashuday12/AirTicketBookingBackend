package com.hexaware.airticketbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	    @Autowired
	    private JavaMailSender mailSender;

	    public void sendEmailOnRegistration(String to, String subject, String body) {
	       
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	message.setTo(to);
	        message.setSubject(subject);
	        message.setText(body);

	        mailSender.send(message);
	    }
	    public void sendEmailForBooking(String email,String text,String subject) {
			SimpleMailMessage message = new SimpleMailMessage();
		    System.out.println(email);
		    System.out.println(text);
		    System.out.println(subject);
		    
			message.setTo(email);
			message.setSubject(subject);
			message.setText(text);
			mailSender.send(message);

		}
}
