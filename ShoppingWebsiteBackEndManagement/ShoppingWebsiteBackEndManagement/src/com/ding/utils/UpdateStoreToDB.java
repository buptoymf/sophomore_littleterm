package com.ding.utils;

import java.sql.*;

public class UpdateStoreToDB {
	private String storeNo, name, type, hotline;
	
	private Connection conn;
    private Statement stat;
	
	public UpdateStoreToDB(String storeNo, String name, String type, String hotline) {
		this.storeNo = storeNo;
		this.name = name;
		this.type = type;
		this.hotline = hotline;
	}
	
	public UpdateStoreToDB(String storeNo) {
		this.storeNo = storeNo;
	}
	
	public boolean check() throws SQLException {
		ResultSet allStoreNo;
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		try {
			allStoreNo = stat.executeQuery("SELECT storeNo, status FROM store");
			while (allStoreNo.next()) {
				if (storeNo.contentEquals(allStoreNo.getString(1)) && allStoreNo.getBoolean(2) == true)
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
			stat.executeUpdate("UPDATE store SET name = '" + this.name + "', type = '" + this.type + "', serviceHotline = '" + this.hotline + "' WHERE storeNo = '" + this.storeNo + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
