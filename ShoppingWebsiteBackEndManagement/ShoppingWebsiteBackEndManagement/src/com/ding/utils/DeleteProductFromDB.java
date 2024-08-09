package com.ding.utils;

import java.sql.*;

public class DeleteProductFromDB {
	String productNoToDelete;
	
	private Connection conn;
    private Statement stat;
	
	public DeleteProductFromDB(String productNo) {
		this.productNoToDelete = productNo;
	}
	
	public boolean delete() throws SQLException {
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		try {
			if (stat.executeUpdate("UPDATE product SET status = false WHERE productNo = '" + this.productNoToDelete + "'") != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
