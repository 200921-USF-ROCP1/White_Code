package com.banking.interfaces;

import com.banking.models.Account;
import com.sun.tools.javac.util.List;

public interface AccountService {
	
	public Account moveMoney(Account acc, double amount);
	
	public Account moveMoney(Account withdrawAcc, Account depositAcc, double amount);
	
	public List<Account> getAllAccounts();
	
	public Account getAccountById(int accId);
	
	public List<Account> getAccountsByStatus(int statusId);
	
	public List<Account> getAccountsByUser(int userId);
	
	public Account openAccount(Account acc);
	
	public Account updateAccount(Account acc);
	
}
