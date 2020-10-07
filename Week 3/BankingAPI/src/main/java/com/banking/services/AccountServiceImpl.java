package com.banking.services;

import com.banking.interfaces.AccountService;
import com.banking.models.Account;
import com.sun.tools.javac.util.List;

public class AccountServiceImpl implements AccountService {

	@Override
	public Account moveMoney(Account acc, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account moveMoney(Account withdrawAcc, Account depositAcc, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccountById(int accId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByStatus(int statusId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account openAccount(Account acc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateAccount(Account acc) {
		// TODO Auto-generated method stub
		return null;
	}

}
