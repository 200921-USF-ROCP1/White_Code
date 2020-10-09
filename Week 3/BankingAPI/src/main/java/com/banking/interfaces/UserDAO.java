package com.banking.interfaces;

import java.sql.SQLException;

import com.banking.models.User;

public interface UserDAO extends GenericDAO<User> {
	
	public User getUserByUsername(String username) throws SQLException;
	
}
