package com.banking.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {
	private static Connection connection;
	
	public static Connection getConnection() {
		
		if (connection == null) { //create connection
		
			try {
				// load sensitive data from local files
				Class.forName("org.postgresql.Driver"); //might not be necessary
				
				String url = "jdbc:postgresql://lallah.db.elephantsql.com:5432/wqrvrrpg";
				String username = "wqrvrrpg";
				String password = "kGgeSLJ8Lmd4-qZjs59Y_R5tbjYcfMHZ";
					
				connection = DriverManager.getConnection(url,username,password);
				//System.out.println("Connection Established!\n\n");
	
			} catch (Exception e) {
				e.printStackTrace();
				closeConnection();
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
