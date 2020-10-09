package com.banking.services;

import com.banking.dao.AccountDAOImpl;
import com.banking.interfaces.AccountDAO;
import com.banking.interfaces.AccountService;
import com.banking.models.Account;

import java.sql.SQLException;
import java.util.List;

public class AccountServiceImpl implements AccountService {
	private static AccountDAO aDAO = new AccountDAOImpl();

	public boolean moveMoney(int accId, double amount) {
		Account acc;
		try {
			acc = aDAO.retrieve(accId); //get account to move money into/from
			acc.setBalance(acc.getBalance() + amount); //set new account balance
			
			if (acc.getBalance() >= 0) {
				aDAO.update(acc);
				return true;
				
			} else return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean moveMoney(int withdrawId, int depositId, double amount) {
		//withdraw from first account
		boolean success = moveMoney(withdrawId,-amount);
		if (success) { //deposit in second account
			success = moveMoney(depositId,amount);
		} else return false;
		return success;
		
	}
	
	public List<Account> getAllAccounts() {
		try {
			return aDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Account getAccountById(int accId) {
		try {
			return aDAO.retrieve(accId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Account> getAccountsByStatus(int statusId) {
		try {
			return aDAO.getAllByStatus(statusId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Account> getAccountsByUser(int userId) {
		try {
			return aDAO.getAllByUser(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Account openAccount(Account acc) {
		//create account
		try {
			return aDAO.create(acc);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Account openAccount(Account acc, int userId) {
		Account account = openAccount(acc);
		try {
			aDAO.addUserToAccount(userId, account.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	public Account updateAccount(Account acc) {
		try {
			aDAO.update(acc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acc;
	}
}
