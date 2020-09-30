package com.managment;

import com.accounts.Account;
import com.users.User;

public class AdminTransactions extends EmployeeTransactions{

	// register a new user
	public static void register(String username, String password, String first_name, String last_name, String email, int roleId) {
		//create new user in database
	}
	
	// deposits, withdrawals and transactions
	public static boolean moveMoney(int accId, double amount) {
		
		// get account based off of account id
		Account account = getAccount(accId);
		
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
		
	public static boolean moveMoney(int wAccId, int dAccId, double amount) {
		// transfer between accounts

		if (moveMoney(wAccId, -amount)) { //if withdrawal is successful
			Boolean depositSuccess = moveMoney(dAccId, amount);
			return depositSuccess ; //transaction completed
		} else {
			return false; //withdraw failed (not enough money)
		}
	}
			
	// update a user
	public static User updateUser(User oldUser, String username, String password, String first_name, String last_name, String email, int roleId) {
		// update all fields of user
	}
	
	//update account
	public static Account updateAccount(Account account) {
		//send new account data to database
	}
	
	//Close account
	public static void closeAccount(int accId) {
		
		Account acc = getAccount(accId);
		
		// check if balance is zero
		if (acc.getBalance() > 0) {
			System.out.println("This account must be empty before cancelation.");
			//maybe break out?
			return;
		}
		
		//delete account and all references from database
	}

}
