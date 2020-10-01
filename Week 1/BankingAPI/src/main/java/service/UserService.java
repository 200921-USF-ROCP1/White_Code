package service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.dao.AccountDAO;
import com.dao.UserDAO;
import com.users.User;

public class UserService {
	Scanner sc = App.sc;
	UserDAO uDAO = new UserDAO();
	AccountDAO aDAO = new AccountDAO();
	
	public User login(String username, String password) {
		User user;
		
		try {
			user = uDAO.getUserByUsername(username);
		} catch (SQLException se){
			se.printStackTrace();
			return null;
		}

		if (user != null) {
			if (user.getPassword().equals(password)) {
				System.out.println("Login Successful!");
				return user;
			}
		}
		System.out.println("Invalid username and password combination.\n");
		return null;
	}
	
	public User logout() {
		return null;
	}
	public void register() {
		
		System.out.println("Registering a new user...");
		
		//Username
		String username;
		do {
		System.out.print("Username: ");
		username = sc.nextLine();
		User unTest = null;
		
		try {
			unTest = uDAO.getUserByUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		if (unTest == null)
			break; //not a duplicate username
		else 
			System.out.println("Username already exists.");
		} while (true);
		
		//Password
		System.out.print("Password: ");
		String password = sc.nextLine();
		
		//First name
		System.out.print("First Name: ");
		String firstName = sc.nextLine();
		
		//Last name
		System.out.print("Last Name: ");
		String lastName = sc.nextLine();
		
		//Email
		String email;
		do {
			System.out.print("Email: ");
			email = sc.nextLine();
			if (email.contains("@") && email.endsWith(".com")) {
				break;
			} else
				System.out.println("Invalid email address");
		} while (true);
		
		//Role
		List<String> allRoles = Arrays.asList("admin","employee","standard","premium");
		String role;
		do {
			System.out.print("Role: ");
			role = sc.nextLine();
			if (allRoles.contains(role.toLowerCase())) {
				break;
			} else
				System.out.println("Not a valid role");
					
		} while (true);
		
		//Converting to roleid
		int roleId;
		switch (role.toLowerCase()) {
			case "admin": {
				roleId = 1;
				break;
			}
			case "employee": {
				roleId = 2;
				break;
			}
			case "standard": {
				roleId = 3;
				break;
			}
			case "premium": {
				roleId = 4;
				break;
			} 
			default: {
				roleId = 3;
				break;
			}
		}
		// Checking for correct entry
		while (true) {
			System.out.printf("Create new user %s %s? [Y/N]",firstName,lastName);
			String input = sc.nextLine();
			if (input.toLowerCase().equals("y")) {
				break;
			} else if (input.toLowerCase().equals("n")) {
				System.out.println("User creation aborted.\n");
				return;
			}
		}
		
		//Commiting changes
		try {
			uDAO.createUser(username, password, firstName, lastName, email, roleId);
			System.out.println("User created!\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
