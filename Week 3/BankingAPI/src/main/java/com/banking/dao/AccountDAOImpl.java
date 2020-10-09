package com.banking.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import com.banking.interfaces.AccountDAO;
import com.banking.interfaces.UserDAO;
import com.banking.models.Account;
import com.banking.models.User;
import com.banking.services.ConnectionService;
import com.banking.utils.ResultManager;

public class AccountDAOImpl implements AccountDAO {
	PreparedStatement ps;
	ResultSet rs;
	Connection connection;
	UserDAO uDAO = new UserDAOImpl();
	
	public AccountDAOImpl() {
		connection = ConnectionService.getConnection();
	}

	public Account create(Account t) throws SQLException {
		// create new account in db
			ps = connection.prepareStatement("insert into accounts(balance, status_id, type_id) values"
					+ " (?, ?, ?) returning account_id");
			ps.setDouble(1, t.getBalance());
			ps.setInt(2, t.getStatus().getStatusId());
			ps.setInt(3, t.getType().getTypeId());

			ps.execute();
			rs = ps.getResultSet(); //get account id
			rs.next();
			
			t.setAccountId(rs.getInt("account_id"));
			//return new account id
			return t;
	}

	public Account retrieve(int id) throws SQLException {
		// get account from database
		ps = connection.prepareStatement("select * from accounts where account_id = ?");
		ps.setInt(1, id);
		rs = ps.executeQuery();
		Account acc = ResultManager.result2Account(rs);
		
		return acc;
	}

	public void update(Account t) throws SQLException {
		//send new account data to database
		ps = connection.prepareStatement("update accounts "
											+ "set balance = ?, status_id = ?, type_id = ? "
											+ "where accounts.account_id = ?");
		ps.setDouble(1, t.getBalance());
		ps.setInt(2, t.getStatus().getStatusId());
		ps.setInt(3, t.getType().getTypeId());
		ps.setInt(4, t.getId());
		
		ps.executeUpdate();
	}

	public void delete(int id) throws SQLException {
		//delete account and all references from database
		ps = connection.prepareStatement("delete from accounts where accounts.account_id = ?");
		ps.setInt(1, id);
		ps.execute();
	}

	public List<Account> getAll() throws SQLException {
		List<Account> accounts = new ArrayList<Account>();
		//retrieve all accounts owned by a user
		ps = connection.prepareStatement("select a.* from accounts a",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = ps.executeQuery();
		
		if (!rs.next()) { //empty result set
			return null;
		} else {
			rs.beforeFirst(); //move cursor back
		}
		
		Account tempAcc;
		while (!rs.isLast()) {
			tempAcc = ResultManager.result2Account(rs);
			accounts.add(tempAcc);
		}
		return accounts;
	}

	public List<Account> getAllByStatus(int statusId) throws SQLException {
		List<Account> accounts = new ArrayList<Account>();
		//retrieve all accounts owned by a user
		ps = connection.prepareStatement("select a.* from accounts a\r\n" + 
											" where a.status_id = ?",
										ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ps.setInt(1, statusId);
		rs = ps.executeQuery();
		
		if (!rs.next()) { //empty result set
			return null;
		} else {
			rs.beforeFirst(); //move cursor back
		}
		
		Account tempAcc;
		while (!rs.isLast()) {
			tempAcc = ResultManager.result2Account(rs);
			accounts.add(tempAcc);
		}
		return accounts;
	}

	public List<Account> getAllByUser(int userId) throws SQLException {
		List<Account> accounts = new ArrayList<Account>();
		//retrieve all accounts owned by a user
		ps = connection.prepareStatement("select a.* from user_accounts ua\r\n" + 
				"	join accounts a on ua.account_id = a.account_id\r\n" + 
				"	natural join account_status s\r\n" + 
				"	where ua.user_id = ?",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ps.setInt(1, userId);
		rs = ps.executeQuery();
		
		if (!rs.next()) { //empty result set
			return null;
		} else {
			rs.beforeFirst(); //move cursor back
		}
		
		Account tempAcc;
		while (!rs.isLast()) {
			tempAcc = ResultManager.result2Account(rs);
			accounts.add(tempAcc);
		} 
		return accounts;
	}

	public void addUserToAccount(int userId, int accId) throws SQLException {
		//add user with the correct userId to accounts/user table
		ps = connection.prepareStatement("insert into user_accounts values (?,?)");
		ps.setInt(1, userId);
		ps.setInt(2, accId);
		
		ps.execute();
		
	}	

}
