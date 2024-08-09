package com.ding.utils;

import java.sql.*;

public class DeleteStoreFromDB {
	private Connection conn;
    private Statement stat;
    
    private String storeNoToDelete;
    
    public DeleteStoreFromDB(String storeNoToDelete) {
    	this.storeNoToDelete = storeNoToDelete;
    }

	public void delete() throws Exception {
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		String sql = "UPDATE store SET status = false WHERE storeNo = '" + this.storeNoToDelete + "'";
		
		
		try {
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean check() throws SQLException {
		boolean isValid = false;
		conn = DataBaseConnection.getConnection();
		ResultSet allStoreNo = null;
		
		try {
			stat = conn.createStatement();
			allStoreNo = stat.executeQuery("SELECT storeNo FROM store WHERE status = true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		while(allStoreNo.next()) {
			if (allStoreNo.getString(1).equals(this.storeNoToDelete))
				isValid = true;
		}
		
		return isValid;
	}
	
}
