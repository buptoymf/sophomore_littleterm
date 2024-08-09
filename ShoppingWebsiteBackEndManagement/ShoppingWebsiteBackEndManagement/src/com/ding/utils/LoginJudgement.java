package com.ding.utils;

import java.sql.*;

public class LoginJudgement {
	private String username;
	private String password;
	private String identityType;
	private int identity;
	
	private Connection conn;
    private Statement stat;
	
	public LoginJudgement(String username, String password, int identity) {
		this.username = username;
		this.password = password;
		this.identity = identity;
		if (identity == 1)
			identityType = "administrator";
		else
			identityType = "storeSeller";
	}
	
	public boolean judge() throws Exception {
		boolean isValid = false;
		ResultSet resultSet = null;
		
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		
		try {
			resultSet = stat.executeQuery("SELECT * FROM " + this.identityType);
			
			while (resultSet.next()) {
				String currentUsername = resultSet.getString(1 + identity - 1);
				String currentPassword = resultSet.getString(2 + identity - 1);
				if (username.equals(currentUsername) && password.equals(currentPassword)) {
					isValid = true;
					break;
				}
			}
			resultSet.close();
			stat.close();
			
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			conn.close();
		}
		
		return isValid;
	}
	
}
