
package com.hexaware.airticketbooking.controller;


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

import com.hexaware.airticketbooking.dto.AdminDTO;
import com.hexaware.airticketbooking.dto.UpdateAdminDTO;

import com.hexaware.airticketbooking.entities.Admin;
import com.hexaware.airticketbooking.exceptions.AdminNotFoundException;
import com.hexaware.airticketbooking.services.IAdminService;

import jakarta.validation.Valid;
/*
 * Author: UdayKiran
 * LastModifiedDate: 19-11-2023
 * Description: This class represents an Admin Rest Controller, which handles HTTP requests related to admin.
 */
@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins="http://localhost:4200")
public class AdminRestController {
	private IAdminService adminService;
    Logger logger = LoggerFactory.getLogger(AdminRestController.class);
    
	public AdminRestController(IAdminService adminService) {
		super();
		this.adminService = adminService;
	}
   
	@PostMapping("/addadmin")
	public AdminDTO addAdmin(@RequestBody @Valid AdminDTO adminDto) {
        logger.info("Received request to add admin: {}", adminDto.getAdminName());
		return adminService.addAdmin(adminDto);
	}
    
	@PutMapping("/updateadmin/{adminId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public UpdateAdminDTO editAdminProfile(@RequestBody @Valid UpdateAdminDTO adminDto,@PathVariable int adminId) {
        logger.info("Received request to update admin profile for adminId: {}", adminDto.getAdminId());
        return adminService.editAdminProfile(adminDto,adminId);
	}
   
	@DeleteMapping("/deleteadmin/{adminId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteAdminAccount(@PathVariable int adminId) {
        logger.info("Received request to delete admin with adminId: {}", adminId);
        adminService.deleteAdminAccount(adminId);
		return new ResponseEntity<>("admin deleted sucessfully", HttpStatus.ACCEPTED);
	}
   
	@GetMapping("/getadminbyid/{adminId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public UpdateAdminDTO getAdminProfileById(@PathVariable int adminId) {
        logger.info("Received request to get admin profile for adminId: {}", adminId);

		UpdateAdminDTO admin= adminService.getAdminProfileById(adminId);
		if(admin.getAdminId()!=adminId) {
            logger.info("Admin not found for adminId: {}", adminId);
            // Throw an AdminNotFoundException if the admin is not found
            	throw new AdminNotFoundException(HttpStatus.NOT_FOUND,"admin with adminId:"+adminId+" notfound");
		}
		return admin;	
	}
   
	@GetMapping("/getalladmin")
	public List<Admin> getAllAdmin(){
        logger.info("Received request to get all admins");
        List<Admin> admin=adminService.viewAllAdmin();
		if(admin.isEmpty()) {
            logger.info("Zero admins found");
            // Throw an AdminNotFoundException if no admins are found

			throw new AdminNotFoundException(HttpStatus.NO_CONTENT,"Zero Admins");	
		}
		return admin;
	}
	@GetMapping("/verifyadminpassword/{adminId}/{password}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public boolean verifyUserPassword(@PathVariable int adminId, @PathVariable String password) {
		return adminService.verifyadminpassword(password, adminId);
	}
	@PutMapping("/changeadminpassword/{adminId}/{password}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public AdminDTO changeUserPassword(@PathVariable int adminId,@PathVariable String password) {
        logger.info("Received request to change password for adminId: {}", adminId);
        return adminService.changeAdminPassword(adminId, password);
		
	}
	
}
