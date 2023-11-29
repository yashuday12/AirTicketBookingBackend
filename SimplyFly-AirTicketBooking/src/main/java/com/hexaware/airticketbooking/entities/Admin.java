package com.hexaware.airticketbooking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
/*
 * Author: Yashwanth
 * LastModifiedDate:19-11-2023
 * Description: This class represents the entity for admin. It defines the structure of the
 * Admin objects stored in the database.
 */
@Entity
public class Admin {
 
	@SequenceGenerator(name="admin_seq",initialValue=1,allocationSize=1)
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="admin_seq")
	@Id
	private int adminId;
	private String adminName;
	private String adminPassword;
	private final String ROLES="ROLE_ADMIN";
	public Admin() {
		super();
	}
	public Admin(int adminId, String adminName, String password) {
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
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPassword=" + adminPassword + "]";
	}
	public String getRoles() {
		return  ROLES;
	}
	
	
	
}
