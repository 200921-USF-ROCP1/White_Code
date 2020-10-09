package com.banking.services;

import com.banking.dao.UserDAOImpl;
import com.banking.interfaces.UserDAO;
import com.banking.interfaces.UserService;
import com.banking.models.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
	private static UserDAO uDAO = new UserDAOImpl();

	@Override
	public User login(String username, String password) {
		User user;
		
		try {
			user = uDAO.getUserByUsername(username); //find user
		} catch (SQLException se){
			se.printStackTrace();
			return null;
		}

		if (user != null) { //check password if user is found
			if (user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User register(User user) {
		try {
			return uDAO.create(user);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		try {
			return uDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getUserById(int userId) {
		try {
			return uDAO.retrieve(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getUserByUsername(String username) {
		try {
			return uDAO.getUserByUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User updateUser(User user) {
		try {
			uDAO.update(user);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

}
