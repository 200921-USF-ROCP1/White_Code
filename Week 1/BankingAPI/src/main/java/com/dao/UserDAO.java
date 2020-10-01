package com.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.accounts.Account;
import com.users.User;

import utils.ConnectionManager;

public class UserDAO {
	
	
	public UserDAO() {
		Connection connection = ConnectionManager.getConnection();
	}
	
	
	// -- create --
	// register a new user
	public void createUser(String username, String password, String first_name, String last_name, String email, int roleId) {
		//create new user in database
	}
	
	// -- retrieve -- 
	//get user
	public User getUser(int userId) {
		// get user from database
		
	}
	
	//get all users
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		
		
		return users;
	}
	
	// -- update -- 
	// update a user
	private void updateUser(User user) {
		// update all fields of user except role and id
	}
	
	// -- delete --
	public void deleteUser(int userId) {
		//delete user
	}
}
