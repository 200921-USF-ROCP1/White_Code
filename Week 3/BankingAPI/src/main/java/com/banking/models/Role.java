package com.banking.models;

public class Role {
	// Tracks user permissions (Admin, Employee, Standard, or Premium)
	//					roleId:	1		2			3			4
	private int roleId; // primary key
	private String role; // not null, unique
	
	// Constructors
	public Role() {
		setRole(3);
	}
	public Role(int roleId) {
		setRole(roleId);
		
	}
	
	// Set role
	public void setRole(int roleId) {
		// set role based on roleId key
		this.roleId = roleId;
		
		switch (roleId) {
		case 1:
			this.role = "Admin";
			break;
		case 2:
			this.role = "Employee";
			break;
			
		case 3:
			this.role = "Standard";
			break;
			
		case 4:
			this.role = "Premium";
			break;

		default:
			setRole(3);
			break;
		}
		
	}
	
	public String getRole() {
		return role;
	}
}
