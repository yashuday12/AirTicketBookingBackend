package com.hexaware.airticketbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateAdminDTO {
	private int adminId;
    @NotBlank(message = "Admin name is required")
    @Size(min = 2, max = 50, message = "Admin name must be between 2 and 50 characters")
	private String adminName;
	public UpdateAdminDTO() {
		super();
	}
	public UpdateAdminDTO(int adminId,String adminName) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
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
    
}
