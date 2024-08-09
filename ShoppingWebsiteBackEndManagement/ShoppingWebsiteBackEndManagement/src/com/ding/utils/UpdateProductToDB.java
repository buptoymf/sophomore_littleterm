package com.ding.utils;

import java.sql.*;

public class UpdateProductToDB {
	private String productNo;
	private String name, descr, catg_III;
	
	private Connection conn;
    private Statement stat;
	
	public UpdateProductToDB(String productNo) {
		this.productNo = productNo;
	}
	
	public UpdateProductToDB(String productNo, String name, String descr, String catg_III) {
		this.productNo = productNo;
		this.name = name;
		this.descr = descr;
		this.catg_III = catg_III;
	}
	
	public boolean check() throws SQLException {
		ResultSet allProductNo;
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		try {
			allProductNo = stat.executeQuery("SELECT productNo, status FROM product");
			while (allProductNo.next()) {
				if (productNo.contentEquals(allProductNo.getString(1)) && allProductNo.getBoolean(2) == true)
					return true;
			}
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void update() throws SQLException {
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		try {
			stat.executeUpdate("UPDATE product SET name = '" + this.name + "', description = '" + this.descr + "', category_third_name = '" + this.catg_III + "' where productNo = '" + this.productNo + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
