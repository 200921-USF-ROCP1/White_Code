package com.banking.dao;

import java.sql.SQLException;

import com.banking.interfaces.AccountDAO;
import com.banking.models.Account;
import com.sun.tools.javac.util.List;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public Account create(Account t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account retrieve(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account update(Account t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Account> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllByStatus(int statusId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllByUser(int userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}	

}
