package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.accounts.Account;
import com.users.User;

import utils.ConnectionManager;
import utils.Results;

public class AccountDAO {
	PreparedStatement ps;
	ResultSet rs;
	Connection connection;
	UserDAO uDAO = new UserDAO();
	
	private static final String password = null;
	public AccountDAO() {
		connection = ConnectionManager.getConnection();
	}
	
	// --- create ---
	// create new account entry
	public int createAccount(double balance, int statusId, int typeId) throws SQLException{
		// create new account in db
		ps = connection.prepareStatement("insert into accounts(balance, status_id, type_id) values"
				+ " (?, ?, ?) returning account_id");
		ps.setDouble(1, balance);
		ps.setInt(2, statusId);
		ps.setInt(3, typeId);

		ps.execute();
		rs = ps.getResultSet(); //get account id
		rs.next();
		
		//return new account id
		return rs.getInt("account_id");
	}
	
	
	//Add user to account
	public void addUserToAccount(String username, int accId) throws SQLException {
		//add user with the correct username to accounts/user table
		User newUser = uDAO.getUserByUsername(username);
		addUserToAccount(newUser.getUserId(),accId);	
	}
	public void addUserToAccount(int userId, int accId) throws SQLException { //for ease of admin use
		//add user with the correct userId to accounts/user table
		ps = connection.prepareStatement("insert into user_accounts values (?,?)");
		ps.setInt(1, userId);
		ps.setInt(2, accId);
		
		ps.execute();
	}
	
	
	// --- retrieve ---
	//get account
	public Account getAccount(int accId) throws SQLException {
		// get account from database
		ps = connection.prepareStatement("select * from accounts a"
											+ "where a.account_id = ?");
		ps.setInt(1, accId);
		rs = ps.executeQuery();
		Account acc = Results.result2Account(rs);
		
		return null;
	}
	
	//get account from database
	public List<Account> getUserAccounts(int userId) throws SQLException { //use list instead
		List<Account> accounts = new ArrayList<Account>();
		//retrieve all accounts owned by a user
		ps = connection.prepareStatement("select a.*,s.status,t.type from user_accounts ua\r\n" + 
				"	join accounts a on ua.account_id = a.account_id\r\n" + 
				"	natural join account_type t\r\n" + 
				"	natural join account_status s\r\n" + 
				"	where ua.user_id = ?\r\n" + 
				"	order by t.type, s.status;");
		ps.setInt(1, userId);
		rs = ps.executeQuery();
		
		Results.printAccounts(rs);
		
		Account tempAcc;
		while (rs.next()) {
			tempAcc = Results.result2Account(rs);
			accounts.add(tempAcc);
		}
		return accounts;
	}

	//get accounts by status
	public List<Account> getAccountsByStatus(int statusId) throws SQLException {
		List<Account> accounts = new ArrayList<Account>();
		//retrieve all accounts with a specified status
		ps = connection.prepareStatement("select a.*,s.status,t.type from user_accounts ua"
											+ "natural join account_type t"
											+ "natural join account_status s"
											+ "group by t.type, s.status"
											+ "where s.status_id = ?");	
		ps.setInt(1, statusId);
		rs = ps.executeQuery();
		
		Results.printAccounts(rs);
		
		Account tempAcc;
		while (rs.next()) {
			tempAcc = Results.result2Account(rs);
			accounts.add(tempAcc);
		}
		return accounts;
	}
	
	// --- update ---
	private void updateAccount(Account acc) throws SQLException {
		//send new account data to database
		ps = connection.prepareStatement("update table accounts(balance, status_id, type_id) values (?,?,?)"
											+ "where accounts.account_id = ?");
		ps.setDouble(1, acc.getBalance());
		ps.setInt(2, acc.getStatusId());
		ps.setInt(3, acc.getTypeId());
		
		ps.executeQuery();
	}
	
	public Account moveMoney(Account account, double amount) throws SQLException {
		// get current balance
		double balance = account.getBalance();
		balance += amount;
		account.setBalance(balance);
		updateAccount(account);
		
		return account;
	}
	
	// transfer between accounts
	public Account moveMoney(Account withdrawAcc, Account depositAcc, double amount) throws SQLException {
		
		Account withdrawAccAfter = moveMoney(withdrawAcc, -amount);
		Account depositAccAfter = moveMoney(depositAcc, amount);
		
		return withdrawAccAfter;
	}
	
	
	
	//delete
	//Close account
	public void closeAccount(Account acc) throws SQLException {
		// check if balance is zero
		if (acc.getBalance() > 0) {
			System.out.println("This account must be empty before cancelation.");
			//maybe break out?
			return;
		}
		
		//delete account and all references from database
		ps = connection.prepareStatement("delete from accounts where accounts.account_id = ?");
		ps.setInt(1, acc.getId());
		ps.execute();
	}
	// admin version
	public void closeAccount(int accId) throws SQLException {
		//delete account and all references from database
		Account acc = getAccount(accId);
		closeAccount(acc);
	}
}
