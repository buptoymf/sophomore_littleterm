package com.ding.utils;

import java.sql.*;

public class RetrievalCategory {
	Connection conn;
    Statement stat;
    
	public String[] getCategoryI() throws Exception {
		String[] result;
		ResultSet resultSet = null;
		int countCategory = 0;
		int index = 0;
		
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		try {
			resultSet = stat.executeQuery("SELECT * FROM category_first");
			resultSet.last();
			countCategory = resultSet.getRow();
			resultSet = stat.executeQuery("SELECT * FROM category_first");
			result = new String[countCategory];
			while (resultSet.next()) {
				result[index] = resultSet.getString(1);
				index++;
			}
			return result;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return new String[] {"Failed"};
	}
	
	public String[] getCategoryII(String catg_I) throws Exception {
		String[] result;
		ResultSet resultSet = null;
		int countCategory = 0;
		int index = 0;
		
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		
		
		try {
			resultSet = stat.executeQuery("SELECT * FROM category_second WHERE category_first_name = '" + catg_I + "'");
			resultSet.last();
			countCategory = resultSet.getRow();
			resultSet = stat.executeQuery("SELECT * FROM category_second WHERE category_first_name = '" + catg_I + "'");
			result = new String[countCategory];
			while (resultSet.next()) {
				result[index] = resultSet.getString(2);
				index++;
			}
			return result;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return new String[] {"Failed"};
	}
	
	public String[] getCategoryIII(String catg_II) throws Exception {
		String[] result;
		ResultSet resultSet = null;
		int countCategory = 0;
		int index = 0;
		
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		
		
		try {
			resultSet = stat.executeQuery("SELECT * FROM category_third WHERE category_second_name = '" + catg_II + "'");
			resultSet.last();
			countCategory = resultSet.getRow();
			resultSet = stat.executeQuery("SELECT * FROM category_third WHERE category_second_name = '" + catg_II + "'");
			result = new String[countCategory];
			while (resultSet.next()) {
				result[index] = resultSet.getString(1);
				index++;
			}
			return result;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return new String[] {"Failed"};
	}
	
	public static void main(String[] args) throws Exception {
		RetrievalCategory test = new RetrievalCategory();
		System.out.println(test.getCategoryII(test.getCategoryI()[2])[1]);
	}
}
