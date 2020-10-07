package service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.accounts.Account;
import com.dao.AccountDAO;
import com.dao.UserDAO;
import com.users.User;

public class AccountService {
	Scanner sc = App.sc;
	UserDAO uDAO = new UserDAO();
	AccountDAO aDAO = new AccountDAO();

	//open new account with a user
	public Account openAccount(User user) {
		
		System.out.print("Opening Balance: ");
		double startBalance = sc.nextDouble();
		
		int typeId;
		System.out.println("Account Type: ");
		System.out.println("   1) Checking");
		System.out.println("   2) Savings");
		do {
		typeId = sc.nextInt();
		if (typeId == 1 || typeId == 2) {
			break;
		} else
			System.out.println("Please select an account type.");
		} while (true);
		
		// Checking for correct entry
		String input;
		while (true) {
			System.out.println("Create new account [Y/N]");
			 input = sc.nextLine();
			if (input.toLowerCase().equals("y")) {
				break;
			} else if (input.toLowerCase().equals("n")) {
				System.out.println("Account opening aborted.\n");
				return null;
			}
		}
		
		//generate new account in database
		int statusId = 1; //generate pending account
		try {
			int accId = aDAO.createAccount(startBalance,statusId,typeId);
			Account acc = new Account(accId,startBalance,typeId,2); //create a pending account
			aDAO.addUserToAccount(user.getUserId(), acc.getId());
			System.out.println("Account Opened!");
			return acc;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	// admin version 
	public Account openAccount(int userId) {
		User user;
		try {
			user = uDAO.getUser(userId);
			Account acc = openAccount(user);
			
			
			return acc;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void listUserAccounts(User user) {
		try {
			aDAO.getUserAccounts(user.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void listUserAccounts(int userId) {
		try {
			aDAO.getUserAccounts(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void listAccountsByStatus() {
		System.out.println("Choose a status:");
		System.out.println("   1) Pending");
		System.out.println("   2) Open");
		System.out.println("   3) Closed");
		System.out.println("   4) Denied");
		
		int input;
		do {
			input = sc.nextInt();
			if (input <= 4 && input > 0) {
				break;
			} else {
				System.out.println("Invalid status number.");
			}
		} while(true);
		
		try {
			aDAO.getAccountsByStatus(input);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addUserToAccount(User user, String newUsername, int accId) {
		
	}
}
