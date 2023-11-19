package com.hexaware.airticketbooking.services;

import java.util.List;

import com.hexaware.airticketbooking.dto.AdminDTO;
import com.hexaware.airticketbooking.entities.Admin;
import com.hexaware.airticketbooking.exceptions.AdminNotFoundException;
/*
 * Author: Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This is Service Interface of Admin. Abstract methods are declared and they are implemented in service implementation classes. */

public interface IAdminService {
	public AdminDTO addAdmin(AdminDTO adminDto);
	public AdminDTO editAdminProfile(AdminDTO adminDto);
	public void deleteAdminAccount(int adminId);
	public Admin getAdminProfileById(int adminId)throws AdminNotFoundException;
	public List<Admin> viewAllAdmin() throws AdminNotFoundException;
}
