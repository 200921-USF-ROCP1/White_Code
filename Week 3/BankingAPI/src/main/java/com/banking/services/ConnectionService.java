package com.banking.services;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionService {
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
				System.out.println("Connection Established!\n\n");
	
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

	public void finalize() {
		closeConnection();
	}
}
