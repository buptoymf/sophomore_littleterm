package com.ding.utils;

import java.sql.*;

public class InsertNewProductToDB {
	private String productNo;
	private String name;
	private String descr;
	private String catg_III;
	private String price;
	
	private Connection conn;
    private Statement stat;
    
    public int countNewPr() {
    	int count = 0;
    	ResultSet allNewProduct = null;
    	conn = DataBaseConnection.getConnection();
    	String sql = "SELECT productNo FROM product WHERE productNo LIKE 'newPr%'";
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
	
	public InsertNewProductToDB(String name, String descr, String catg_III, String price) {
		int number = this.countNewPr();
		this.productNo = "newPr00" + Integer.valueOf(number).toString(); // newPr010 needs to be fixed
		this.name = name;
		this.descr = descr;
		this.catg_III = catg_III;
		this.price = price;
	}
	
	public void insert() {
		conn = DataBaseConnection.getConnection();
		try {
			stat = conn.createStatement();
			stat.executeUpdate("INSERT INTO product VALUES ('" + this.productNo + "', '" + this.name + "', '" + this.descr + "', '" + this.catg_III + "', true)");
			stat.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
