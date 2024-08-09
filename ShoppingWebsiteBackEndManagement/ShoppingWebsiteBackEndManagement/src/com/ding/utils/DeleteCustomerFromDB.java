package com.ding.utils;

import java.sql.*;

public class DeleteCustomerFromDB {
	private String username;
	
	private Connection conn;
    private Statement stat;
	
	public DeleteCustomerFromDB(String username) {
		this.username = username;
	}
	
	public boolean delete() throws SQLException {
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		try {
			if (stat.executeUpdate("UPDATE customer SET status = false WHERE customer_userName = '" + this.username + "'") != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
