package com.hexaware.airticketbooking.services;

import java.util.List;

import com.hexaware.airticketbooking.dto.AdminDTO;
import com.hexaware.airticketbooking.dto.UpdateAdminDTO;
import com.hexaware.airticketbooking.entities.Admin;
import com.hexaware.airticketbooking.exceptions.AdminNotFoundException;
/*
 * Author: Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This is Service Interface of Admin. Abstract methods are declared and they are implemented in service implementation classes. */

public interface IAdminService {
	public AdminDTO addAdmin(AdminDTO adminDto);// It is used to register  Admin details.
	public UpdateAdminDTO editAdminProfile(UpdateAdminDTO adminDto,int adminId);// This method can be used to edit Admin details.
	public void deleteAdminAccount(int adminId);// It is used to delete admin
	public UpdateAdminDTO getAdminProfileById(int adminId)throws AdminNotFoundException;// It can be used to edit admin detils
	public List<Admin> viewAllAdmin() throws AdminNotFoundException;//It is used to display all admin details
	 public boolean verifyadminpassword(String password,int adminId);
	 public AdminDTO changeAdminPassword(int adminId, String password);
}
