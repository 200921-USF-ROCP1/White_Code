package com.managment;

import java.util.Scanner;

import com.accounts.Account;
import com.users.User;

public class UserTransactions {
	// this class holds all of the methods for creating, updating, and removing user data
	Scanner sc = new Scanner(System.in);
	
	//update account
	private static void updateAccount(Account account) {
		//send new account to database
	}
	
	//update user info
	
	
	// register a new user
	public static User register(String username, String password, String first_name, String last_name, String enmail, int roleId) {
		
		
		return login(username, password);
	}
	
	public static User login(String username, String password) {
		// check if username password combo exists
		
		// get data
		
		User currentUser = new User();
		//return user object?
		return currentUser;
	}
	
	public static void moveMoney(User user, Account account, double amount) {
		// check user can access account
		
		// get current balance
		double balance = account.getBalance();
		
		// check amount exists if withdrawing (negative amount)
		if (balance + amount >= 0)
			balance += amount;
		
		// update account information
		account.setBalance(balance);
	}
	
	
	
	

}
