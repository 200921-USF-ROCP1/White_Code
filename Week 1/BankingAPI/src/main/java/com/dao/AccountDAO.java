package com.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.accounts.Account;
import com.users.User;

import utils.ConnectionManager;

public class AccountDAO {
	
	public AccountDAO() {
		Connection connection = ConnectionManager.getConnection();
	}
	
	// --- create ---
	// create new account entry
	private void createAccount(Account acc) {
		// create new account in db
	}
	
	//open new account with a user
	public Account openAccount(User user, int typeId, double startBalance) {
		
		
		Account acc = new Account(startBalance,typeId,2); //create an open account
		
		//generate new account in database
		createAccount(acc);
		addUserToAccount(user.getUsername(), acc);
		return acc;
	}
	
	//Add user to account
	public void addUserToAccount(String username, Account acc) {
		//add user with the correct username to accounts/user table
	}
	public void addUserToAccount(int userId, Account acc) { //for ease of admin use
		//add user with the correct userId to accounts/user table
	}
	
	
	// --- retrieve ---
	//get account
	public Account getAccount(int accId) {
		// get account from database
	}
	
	//get account from database
	public List<Account> getUserAccounts(int userId) { //use list instead
		List<Account> accounts = new ArrayList<Account>();
		//retrieve all accounts owned by a user
		
		
		return accounts;
	}

	//get accounts by status
	public List<Account> getAccountsByStatus(int statusId) {
		List<Account> accounts = new ArrayList<Account>();
		//retrieve all accounts with a specified status
		
		
		return accounts;
	}
	
	// --- update ---
	private void updateAccount(Account account) {
		//send new account data to database
	}
	
	public Account moveMoney(Account account, double amount) {
		// get current balance
		double balance = account.getBalance();
		balance += amount;
		account.setBalance(balance);
		updateAccount(account);
		
		return account;
	}
	
	// transfer between accounts
	public Account moveMoney(Account withdrawAcc, Account depositAcc, double amount) {
		
		Account withdrawAccAfter = moveMoney(withdrawAcc, -amount);
		Account depositAccAfter = moveMoney(depositAcc, amount);
		
		return withdrawAccAfter;
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
		//delete account and all references from database
	}
}
