package com.banking.services;

import com.banking.dao.UserDAOImpl;
import com.banking.interfaces.AccountService;
import com.banking.interfaces.UserDAO;
import com.banking.interfaces.UserService;
import com.banking.models.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
	private static UserDAO uDAO = new UserDAOImpl();
	private static AccountService aServ = new AccountServiceImpl();

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

	public User upgradeToPremium(User user, int accId) {
		double price = 200; //upgrade price
		
		// check if account has enough money
		if (aServ.moveMoney(accId,-price)) {
			try {
				user.setRole(3); //set role to premium
				uDAO.update(user);
				return user;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}

	public boolean deleteUser(int userId) {
		try {
			uDAO.delete(userId);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
