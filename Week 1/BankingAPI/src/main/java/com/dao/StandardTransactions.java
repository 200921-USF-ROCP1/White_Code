package com.dao;

import com.accounts.Account;
import com.users.User;

public class StandardTransactions {

	//get account from database
	public static Account[] getUserAccounts(User user) {
		//retrieve all accounts owned by a user
		Account[] accounts = null; //***placeholder***
		
		return accounts;
	}
	
	
	
	// update a user
	public static void updateUser(User oldUser, String username, String password, String first_name, String last_name, String enmail) {
		// update all fields of user except role
	}
	
	
	public static void logout(User user) {
		System.out.printf("You have successfully logged out %s.",user.getUsername());
	}
	
	//user login
	public static User login(String username, String password) {
		// check if username password combo exists
		
		// get data
		
		User currentUser = new User();
		//return user object?
		return currentUser;
	}
	
	// deposits, withdrawals and transactions
	public boolean moveMoney(User user, Account account, double amount) {
		// check user can access account (maybe not necessary)
		
		// get current balance
		double balance = account.getBalance();
		
		// check amount exists if withdrawing (negative amount)
		if (balance + amount >= 0) {
			balance += amount;
		
			// update account information
			account.setBalance(balance);
			updateAccount(account);
			if (amount>0) {
				System.out.printf("%.2f has been deposited in account #%d",amount,account.getId());
			} else {
				System.out.printf("%.2f has been withdrawn from account #%d",-amount,account.getId());
			}
			return true; //transaction completed
		} else
			System.out.printf("Not enough money in account #%d.",account.getId());
		return false;
	}
	
	public boolean moveMoney(User user, Account withdrawAcc, Account depositAcc, double amount) {
		// transfer between accounts

		if (moveMoney(user, withdrawAcc, -amount)) { //if witdrawl is successful
			Boolean depositSuccess = moveMoney(user, depositAcc, amount);
			return depositSuccess ; //transaction completed
		} else {
			return false; //withdraw failed (not enough money)
		}
	}
	
	
	
	//Close account
	public void closeAccount(Account acc) {
		// check if balance is zero
		if (acc.getBalance() > 0) {
			System.out.println("This account must be empty before cancelation.");
			//maybe break out?
			return;
		}
		
		//delete account and all references from database
	}
}
