package com.banking.utils;

import com.banking.models.Role;

public class Authentication {
	private static Role role = new Role();
	private static SessionManager sm = new SessionManager();
	
	public static boolean canAccess(String LowestRole) {
		
		role.setRole(LowestRole); //valid permission cap
		int userRoleId = sm.getUser().getRole().getRoleId(); //user permission
		
		if (userRoleId <= role.getRoleId()) { //if user has permission
			return true;
		} else return false;
	}

}
