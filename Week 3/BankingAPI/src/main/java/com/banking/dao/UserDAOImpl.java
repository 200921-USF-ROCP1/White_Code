package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.banking.interfaces.UserDAO;
import com.banking.models.User;
import com.banking.services.ConnectionService;
import com.banking.utils.ResultManager;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
	
	PreparedStatement ps;
	ResultSet rs;
	Connection connection;
	
	public UserDAOImpl() {
		connection = ConnectionService.getConnection();
	}

	public User create(User t) throws SQLException {
		//create new user in database
		ps = connection.prepareStatement("insert into users(username, password, first_name, last_name, email, role_id) values"
											+ " (?, ?, ?, ?, ?, ?) returning user_id");
		ps.setString(1, t.getUsername());
		ps.setString(2, t.getPassword());
		ps.setString(3, t.getFirstName());
		ps.setString(4, t.getLastName());
		ps.setString(5, t.getEmail());
		ps.setInt(6,t.getRole().getRoleId());
		
		ps.execute();
		rs = ps.getResultSet(); //get account id
		rs.next();
		
		t.setUserId(rs.getInt("account_id"));
		return t;
	}

	public User retrieve(int id) throws SQLException {
		// get user from database
		ps = connection.prepareStatement("select * from users"
										+ " where user_id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		User user = ResultManager.result2User(rs);
		user.setPassword(null);
		
		return user;
	}

	public void update(User t) throws SQLException {
		//send new user data to database
		ps = connection.prepareStatement("update users\r\n" + 
				"	set username = ?, password = ?, first_name = ?, last_name = ?, email = ?, role_id = ?\r\n" + 
				"	where user_id = ?");
		ps.setString(1, t.getUsername());
		ps.setString(2, t.getPassword());
		ps.setString(3, t.getFirstName());
		ps.setString(4, t.getLastName());
		ps.setString(5, t.getEmail());
		ps.setInt(6,t.getRole().getRoleId());
		ps.setInt(7, t.getUserId());
		
		ps.executeUpdate();
	}

	public void delete(int id) throws SQLException {
		//delete user and all references from database
		ps = connection.prepareStatement("delete from users where users.user_id = ?");
		ps.setInt(1, id);
		ps.execute();
		
	}

	public List<User> getAll() throws SQLException {
		List<User> users = new ArrayList<User>();
		//retrieve all accounts owned by a user
		ps = connection.prepareStatement("select u.* from users u",
										ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = ps.executeQuery();
		
		if (!rs.next()) { //empty result set
			return null;
		} else {
			rs.beforeFirst(); //move cursor back
		}
		
		User tempUsr;
		while (!rs.isLast()) {
			tempUsr = ResultManager.result2User(rs);
			tempUsr.setPassword(null);
			users.add(tempUsr);
		}
		return users;
	}

	public User getUserByUsername(String username) throws SQLException {
		// get user from database
		ps = connection.prepareStatement("select * from users"
				+ " where username = ?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		
		return ResultManager.result2User(rs);
	}


}
