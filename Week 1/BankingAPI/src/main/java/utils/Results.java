package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.accounts.Account;
import com.users.User;

public class Results {
	
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
	
	// Print a accounts result array to the screen
	public static void printAccounts(ResultSet rs) throws SQLException { //must be joined to both type and status tables

		int itr = 0;
		while (rs.next()) {
			System.out.println(String.format("%d) %s account #%d: $%.2f\t(%s)", 
					++itr, rs.getString("type"), rs.getInt("account_id"), rs.getDouble("balance"), rs.getString("status")));
		}
		if (itr == 0) {
			System.out.println("No accounts found.");
		}
	}
}


