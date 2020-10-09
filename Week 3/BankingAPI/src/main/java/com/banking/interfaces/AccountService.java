package com.banking.interfaces;

import com.banking.models.Account;
import java.util.List;

public interface AccountService {
	
	public boolean moveMoney(int accId, double amount);
	
	public boolean moveMoney(int withdrawId, int depositId, double amount);
	
	public List<Account> getAllAccounts();
	
	public Account getAccountById(int accId);
	
	public List<Account> getAccountsByStatus(int statusId);
	
	public List<Account> getAccountsByUser(int userId);
	
	public Account openAccount(Account acc);
	
	public Account openAccount(Account acc, int userId);
	
	public Account updateAccount(Account acc);
	
}
