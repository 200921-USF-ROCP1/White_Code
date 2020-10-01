package utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionManager {
	private static Connection connection;
	
	public static Connection getConnection() {
		
		if (connection == null) { //create connection
		
			try {
				// load sensitive data from local files
				FileInputStream fis = new FileInputStream("connection.properties");
				Properties prop = new Properties();
				prop.load(fis);
				//Class.forName("org.postgresql.Driver"); //might not be necessary
				
				connection = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
				System.out.println("Connection Established!");
	
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return connection;
	}
	
	public static void closeConnection() {
		try {
			if (connection != null){
				//should be closed at end
				connection.close();
				connection = null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
