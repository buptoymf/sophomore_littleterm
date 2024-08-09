package com.ding.utils;

import java.sql.*;

public class UpdateCustomerToDB {
	private String previousUsername;
	private String currentUsername;
	
	private Connection conn;
    private Statement stat;
	
	public UpdateCustomerToDB(String previousUsername, String currentUsername) {
		this.previousUsername = previousUsername;
		this.currentUsername = currentUsername;
	}
	
	public boolean check(String username) throws Exception {
		ResultSet allCustomerUsername;
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		try {
			allCustomerUsername = stat.executeQuery("SELECT customer_userName, status FROM customer");
			while (allCustomerUsername.next()) {
				if (username.contentEquals(allCustomerUsername.getString(1)) && allCustomerUsername.getBoolean(2) == true)
					return true;
			}
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void update() throws Exception {
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		try {
			stat.executeUpdate("UPDATE customer SET customer_userName = '" + this.currentUsername + "' WHERE customer_userName = '" + this.previousUsername + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
