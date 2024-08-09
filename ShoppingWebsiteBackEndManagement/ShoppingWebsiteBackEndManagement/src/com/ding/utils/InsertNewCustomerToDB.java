package com.ding.utils;

import java.sql.*;

public class InsertNewCustomerToDB {
	private String username;
	
	private Connection conn;
    private Statement stat;
	
	public InsertNewCustomerToDB(String username) {
		this.username = username;
	}
	
	public boolean check() throws Exception{
		ResultSet allCustomerUsername = null;
		String sql = "SELECT customer_userName from customer";
		boolean isValid = true;
		
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		try {
			allCustomerUsername = stat.executeQuery(sql);
			while (allCustomerUsername.next()) {
				if (this.username.equals(allCustomerUsername.getString(1)))
					isValid = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isValid;
	}
	
	public void insert() throws Exception {
		conn = DataBaseConnection.getConnection();
		String sql = "INSERT INTO customer VALUES ('" + this.username + "', true)";
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
