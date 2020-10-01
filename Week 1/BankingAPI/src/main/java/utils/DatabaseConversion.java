package utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.accounts.Account;
import com.users.User;

public class DatabaseConversion {
	
	// Convert a result set into a user object
	public static User result2User(ResultSet rs) throws SQLException {
		
		if (rs.next()) {
			User user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), 
					rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getInt("role_id"));
			return user;
		} else 
			return null;

	}
	
	// convert a result set into an account object
	public static Account result2Account(ResultSet rs) throws SQLException {

		if (rs.next()) {
			Account acc = new Account(rs.getInt("account_id"), rs.getDouble("balance"), rs.getInt("status_id"), 
					rs.getInt("type_id"));
			return acc;
		} else 
			return null;
	}
}

