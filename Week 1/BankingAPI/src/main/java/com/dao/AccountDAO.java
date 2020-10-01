package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.accounts.Account;
import com.users.User;

public class AccountDAO {
	
	//create
	// create new account entry
	private void createAccount(Account acc) {
		// create new account in db
	}
	
	//Add account to user
	public Account openAccount(User user, int typeId, double startBalance) {
		
		
		Account acc = new Account(startBalance,typeId,2); //create an open account
		
		//generate new account in database
		createAccount(acc);
		return acc;
	}
	public Account openAccount(int userId, int typeId, double startBalance) {
		
		Account acc = new Account(startBalance,typeId,2); //create an open account
		
		//generate new account in database
		createAccount(acc);
		return acc;
	}
	
	//Add user to account
	public void addUserToAccount(int username, Account acc) {
		//add user with the correct username to accounts/user table
	}
	
	
	
	//retrieve
	//get account
	public Account getAccount(int accId) {
		// get account from database
	}
	
	//get accounts by user
	public List<Account> getUserAccounts(int userId) { //use list instead
		List<Account> accounts = new ArrayList<Account>();
		//retrieve all accounts owned by a user
		User user = getUser(userId);
		
	}
	
	//update
	public void updateAccount(Account account) {
		//send new account data to database
	}
	
	
	//delete
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
	// admin version
	public void closeAccount(int accId) {
			
		Account acc = getAccount(accId);
		
		//delete account and all references from database
	}
}
