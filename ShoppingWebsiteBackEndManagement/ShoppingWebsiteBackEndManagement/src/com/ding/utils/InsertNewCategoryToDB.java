package com.ding.utils;

import java.sql.*;

public class InsertNewCategoryToDB {
	private String catg_I, catg_II, catg_III;
	private int insertType; // 1 means insert a I; 2 means insert a II; 3 means insert a III.
	private Connection conn;
    private Statement stat;
	
	public InsertNewCategoryToDB(String catg_I) {
		this.catg_I = catg_I;
		this.insertType = 1;
	}
	
	public InsertNewCategoryToDB(String catg_I, String catg_II) {
		this.catg_I = catg_I;
		this.catg_II = catg_II;
		this.insertType = 2;
	}
	
	public InsertNewCategoryToDB(String catg_I, String catg_II, String catg_III) {
		this.catg_I = catg_I;
		this.catg_II = catg_II;
		this.catg_III = catg_III;
		this.insertType = 3;
	}
	
	public boolean check() throws SQLException {
		ResultSet result = null;
		String sql;
		boolean isValid = true;
		
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		switch(this.insertType) {
			case 1:
				sql = "SELECT category_first_name FROM category_first";
				try {
					result = stat.executeQuery(sql);
					while (result.next()) {
						if (this.catg_I.equals(result.getString(1)))
							isValid = false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				sql = "SELECT category_second_name FROM category_second WHERE category_first_name = '" + this.catg_I + "'";
				try {
					result = stat.executeQuery(sql);
					while (result.next()) {
						if (this.catg_II.equals(result.getString(1)))
							isValid = false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				sql = "SELECT category_third_name FROM category_third WHERE category_second_name = '" + this.catg_II + "'";
				try {
					result = stat.executeQuery(sql);
					while (result.next()) {
						if (this.catg_III.equals(result.getString(1)))
							isValid = false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
		return isValid;
	}
	
	public void insert() throws SQLException {
		conn = DataBaseConnection.getConnection();
		String sql;
		
		switch (this.insertType) {
			case 1:
				sql = "INSERT INTO category_first VALUES ('" + this.catg_I + "')";
				try {
					stat = conn.createStatement();
					stat.executeUpdate(sql);
					stat.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				sql = "INSERT INTO category_second VALUES ('" + this.catg_I + "', '" + this.catg_II + "')";
				try {
					stat = conn.createStatement();
					stat.executeUpdate(sql);
					stat.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				sql = "INSERT INTO category_third VALUES ('" + this.catg_III + "', '" + this.catg_II + "')";
				try {
					stat = conn.createStatement();
					stat.executeUpdate(sql);
					stat.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
		
	}
	
}
