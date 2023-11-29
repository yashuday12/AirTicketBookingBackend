package com.hexaware.airticketbooking.dto;

public class RoleIdDTO {
   private String role;
   private int id;
public RoleIdDTO() {
	super();
}
public RoleIdDTO(String role, int id) {
	super();
	this.role = role;
	this.id = id;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
   
   
}
