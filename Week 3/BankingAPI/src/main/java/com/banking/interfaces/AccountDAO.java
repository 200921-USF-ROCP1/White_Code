package com.banking.interfaces;

import java.sql.SQLException;

import com.banking.models.Account;
import java.util.List;

public interface AccountDAO extends GenericDAO<Account>{
	
	public List<Account> getAllByStatus(int statusId) throws SQLException;
	
	public List<Account> getAllByUser(int userId) throws SQLException;
	
	public void addUserToAccount(int userId, int accId) throws SQLException;
	
	public void addInterest(double savingsAmount, double checkingAmount) throws SQLException;

}
