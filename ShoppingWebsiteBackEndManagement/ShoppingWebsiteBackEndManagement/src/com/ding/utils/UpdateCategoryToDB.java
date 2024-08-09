package com.ding.utils;

import java.sql.*;

public class UpdateCategoryToDB {
	private String newCatg_I, newCatg_II, newCatg_III;
	private String oldCatg_I, oldCatg_II, oldCatg_III;
	private int type;
	
	private Connection conn;
    private Statement stat;
	
	public UpdateCategoryToDB(String oldCatg_I, String newCatg_I) {
		this.oldCatg_I = oldCatg_I;
		this.newCatg_I = newCatg_I;
		this.type = 1;
	}
	
	public UpdateCategoryToDB(String oldCatg_I, String oldCatg_II, String newCatg_I, String newCatg_II) {
		this.oldCatg_I = oldCatg_I;
		this.oldCatg_II = oldCatg_II;
		this.newCatg_I = newCatg_I;
		this.newCatg_II = newCatg_II;
		this.type = 2;
	}
	
	public UpdateCategoryToDB(String oldCatg_I, String oldCatg_II, String oldCatg_III, String newCatg_I, String newCatg_II, String newCatg_III) {
		this.oldCatg_I = oldCatg_I;
		this.oldCatg_II = oldCatg_II;
		this.oldCatg_III = oldCatg_III;
		this.newCatg_I = newCatg_I;
		this.newCatg_II = newCatg_II;
		this.newCatg_III = newCatg_III;
		this.type = 3;
	}
	
	public boolean check() throws SQLException {
		ResultSet result = null;
		String sql;
		boolean isValid = true;
		
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		switch (this.type) {
			case 1:
				sql = "SELECT category_first_name FROM category_first";
				result = stat.executeQuery(sql);
				while (result.next()) {
					if (this.newCatg_I.equals(result.getString(1)))
						isValid = false;
				}
				break;
			case 2:
				sql = "SELECT category_second_name FROM category_second";
				result = stat.executeQuery(sql);
				while (result.next()) {
					if (this.newCatg_II.equals(result.getString(1)))
						isValid = false;
				}
				break;
			case 3:
				sql = "SELECT category_third_name FROM category_third";
				result = stat.executeQuery(sql);
				while (result.next()) {
					if (this.newCatg_III.equals(result.getString(1)))
						isValid = false;
				}
				break;
			default:
				break;
		}
		
		
		return isValid;
	}
	
	public void update() throws SQLException {
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		String sql;
		
		switch(this.type) {
			case 1:
				sql = "UPDATE category_first SET category_first_name = '" + this.newCatg_I + "' WHERE category_first_name = '" + this.oldCatg_I + "'";
				stat.executeUpdate(sql);
				break;
			case 2:
				sql = "UPDATE category_second SET category_second_name = '" + this.newCatg_II + "' WHERE category_second_name = '" + this.oldCatg_II + "' AND category_first_name = '" + this.oldCatg_I + "'";
				stat.executeUpdate(sql);
				break;
			case 3:
				sql = "UPDATE category_third SET category_third_name = '" + this.newCatg_III + "' WHERE category_third_name = '" + this.oldCatg_III + "'";
				stat.executeUpdate(sql);
				break;
			default:
				break;
		}
		
	}
	
	
}
