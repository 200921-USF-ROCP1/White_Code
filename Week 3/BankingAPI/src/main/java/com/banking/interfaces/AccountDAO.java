package com.banking.interfaces;

import java.sql.SQLException;

import com.banking.models.Account;
import com.sun.tools.javac.util.List;

public interface AccountDAO extends GenericDAO<Account>{
	
	public List<Account> getAllByStatus(int statusId) throws SQLException;
	
	public List<Account> getAllByUser(int userId) throws SQLException;;

}
