package com.managment;

import com.accounts.Account;
import com.users.User;

public class EmployeeTransactions extends StandardTransactions{
	
	//get account
	public static Account getAccount(int accId) {
		// get account from database
	}
	
	//get accounts by user
	public static Account[] getUserAccounts(int userId) {
		//retrieve all accounts owned by a user
		User user = getUser(userId);
		
	}
	//get accounts by status
	public static Account[] getAccountsByStatus(int statusId) {
		//retrieve all accounts with a specified status
		
	}
	
	//get user
	public static User getUser(int userId) {
		// get user from database
	}
	
	//get all users
	protected static User[] getAllUsers() {
		
	}
}