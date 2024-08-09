package com.ding.utils;

import java.sql.*;

public class InsertNewStoreToDB {
	private String name, type, hotline, storeNo, foundDate, status;
	private Connection conn;
    private Statement stat;

	public InsertNewStoreToDB(String name, String type, String hotline, String foundDate) {
		int number = this.countnewStore();
		this.storeNo = "newSt00" + Integer.valueOf(number).toString(); // newSt010 needs to be fixed
		this.name = name;
		this.type = type;
		this.hotline = hotline;
		this.foundDate = foundDate;
		this.status = "true";
	}
	
	public int countnewStore() {
		int count = 0;
    	ResultSet allNewProduct = null;
    	conn = DataBaseConnection.getConnection();
    	String sql = "SELECT storeNo FROM store WHERE storeNo LIKE 'newSt%'";
    	try {
    		stat = conn.createStatement();
    		allNewProduct = stat.executeQuery(sql);
    		allNewProduct.last();
    		count = allNewProduct.getRow();
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return count;
	}
	
	public void insert() {
		conn = DataBaseConnection.getConnection();
		try {
			stat = conn.createStatement();
			stat.executeUpdate("INSERT INTO store VALUES ('" + this.storeNo + "', '" + this.name + "', '" + this.type + "', '" + this.hotline +"', '" + this.foundDate + "', true)");
			stat.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
