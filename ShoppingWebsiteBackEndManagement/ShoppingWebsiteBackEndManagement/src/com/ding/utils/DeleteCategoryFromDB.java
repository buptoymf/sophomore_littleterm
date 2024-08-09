package com.ding.utils;

import java.sql.*;

public class DeleteCategoryFromDB {
	private int type;
	private String catg_I, catg_II, catg_III;
	
	private Connection conn;
    private Statement stat;
	
	public DeleteCategoryFromDB(String catg_I) {
		this.catg_I = catg_I;
		this.type = 1;
	}
	
	public DeleteCategoryFromDB(String catg_I, String catg_II) {
		this.catg_I = catg_I;
		this.catg_II = catg_II;
		this.type = 2;
	}
	
	public DeleteCategoryFromDB(String catg_I, String catg_II, String catg_III) {
		this.catg_I = catg_I;
		this.catg_II = catg_II;
		this.catg_III = catg_III;
		this.type = 3;
	}
	
	public boolean check() throws SQLException {
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		ResultSet result = null;
		String sql;
		boolean isValid = false;
		
		switch (this.type) {
			case 1:
				sql = "SELECT productNo FROM product WHERE category_third_name IN (SELECT category_third_name FROM category_third WHERE category_second_name IN (SELECT category_second_name FROM category_second WHERE category_first_name = '" + this.catg_I + "'))";
				result = stat.executeQuery(sql);
				if (!result.next())
					isValid = true;
				break;
			case 2:
				sql = "SELECT productNo FROM product WHERE category_third_name IN (SELECT category_third_name FROM category_third WHERE category_second_name = '" + this.catg_II + "')";
				result = stat.executeQuery(sql);
				if (!result.next())
					isValid = true;
				break;
			case 3:
				sql = "SELECT productNo FROM product WHERE category_third_name = '" + this.catg_III + "'";
				result = stat.executeQuery(sql);
				if (!result.next())
					isValid = true;
				break;
			default:
				break;
				
		}
		
		return isValid;
	}
	
	public void delete() throws SQLException {
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		String sql;
		
		switch (this.type) {
			case 1:
				sql = "DELETE FROM category_first WHERE category_first_name = '" + this.catg_I + "'";
				stat.executeUpdate(sql);
				break;
			case 2:
				sql = "DELETE FROM category_second WHERE category_second_name = '" + this.catg_II + "'";
				stat.executeUpdate(sql);
				break;
			case 3:
				sql = "DELETE FROM category_third WHERE category_third_name = '" + this.catg_III + "'";
				break;
			default:
				break;
		}
		
		
	}
	
	
}
