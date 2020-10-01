package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	static Connection connection;
	
	
	
	public void startup() {
	
		final String connectionString = "jdbc:postgresql://lallah.db.elephantsql.com:5432/wqrvrrpg",
				username = "wqrvrrpg",
				password = "kGgeSLJ8Lmd4-qZjs59Y_R5tbjYcfMHZ";
	
		try {
			//Class.forName("org.postgresql.Driver"); //might not be nessesary
			
			connection = DriverManager.getConnection(connectionString,username,password);
			System.out.println("Connection Established!");
						
			executeSQL(connection);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null){
					//should be closed at end
					connection.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void executeSQL(PreparedStatement ps) throws SQLException {
		
		ps = connection.prepareStatement("select * from residents where id = ?"); //vehicle for running statements
		ps.setInt(1,3); //(question mark number, value)
		
		ResultSet rs = ps.executeQuery(); //contain rows returned by query
		
		// result set has an internal iterator that starts at the row above the first row
		// no next row returns false
		while (rs.next()) {
			System.out.println(rs.getString("first_name")); //can also get by column number
		}
	}
}
