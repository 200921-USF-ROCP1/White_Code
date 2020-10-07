package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.accounts.Account;
import com.users.User;

import utils.ConnectionManager;
import utils.Results;

public class UserDAO {
	PreparedStatement ps;
	Connection connection;
	
	public UserDAO() {
		connection = ConnectionManager.getConnection();
	}
	
	
	// -- create --
	// register a new user
	public void createUser(String username, String password, String first_name, String last_name, String email, int roleId) throws SQLException {
		//create new user in database
		ps = connection.prepareStatement("insert into users(username, password, first_name, last_name, email, role_id) values"
											+ " (?, ?, ?, ?, ?, ?)");
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, first_name);
		ps.setString(4, last_name);
		ps.setString(5, email);
		ps.setInt(6,roleId);
		
		ps.execute();
	}
	
	// -- retrieve -- 
	//get user
	public User getUser(int userId) throws SQLException {
		// get user from database
		ps = connection.prepareStatement("select * from users"
				+ " where user_id = ?");
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		
		return Results.result2User(rs);
	}
	public User getUserByUsername(String username) throws SQLException {
		// get user by username
		ps = connection.prepareStatement("select * from users"
										+ " where username = ?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		
		return Results.result2User(rs);
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
