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
		this.setRoleId(roleId);
		
		switch (roleId) {
		case 1:
			this.role = "Admin";
			break;
		case 2:
			this.role = "Employee";
			break;
			
		case 3:
			this.role = "Premium";
			break;
			
		case 4:
			this.role = "Standard";
			break;

		default:
			setRole(4);
			break;
		}
	}
	
	public void setRole(String role) {
		// set role based on roleId key
		this.role = role;
		
		switch (role) {
		case "Admin":
			this.roleId = 1;
			break;
		case "Employee":
			this.roleId = 2;
			break;
			
		case "Premium":
			this.roleId = 3;
			break;
			
		case "Standard":
			this.roleId = 4;
			break;

		default:
			setRole(4);
			break;
		}
	
	}
	
	public String getRole() {
		return role;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
