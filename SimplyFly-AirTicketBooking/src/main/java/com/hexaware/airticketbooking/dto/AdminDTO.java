package com.hexaware.airticketbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/*
 * Author: Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This is DTO class for Admin. It is completely used for data transfer between controller and service layers
 */
public class AdminDTO {

	private int adminId;
    @NotBlank(message = "Admin name is required")
    @Size(min = 2, max = 50, message = "Admin name must be between 2 and 50 characters")
	private String adminName;
    @NotBlank(message = "Admin password is required")
    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}$")
    private String adminPassword;
   
	public AdminDTO() {
		super();
	}
	public AdminDTO(int adminId, String adminName, String password) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = password;
	
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "AdminDTO [adminId=" + adminId + ", adminName=" + adminName + ", adminPassword=" + adminPassword + "]";
	}
	
}
