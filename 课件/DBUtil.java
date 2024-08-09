package com.yuan.testJDBC;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.Statement;

public class DBUtil {
	 public static final String url = "jdbc:mysql://127.0.0.1/student";  
	 public static final String driver = "com.mysql.jdbc.Driver";  
	 public static final String user = "root";  
	 public static final String password = "123456";  
	  
	 public static Connection getConnection() {
		 Connection conn = null;  
		 try {  
			    Class.forName(driver);//指定连接类型  
	            conn = DriverManager.getConnection(url, user, password);//获取连接  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
		 return conn ;
	    }  
	 public static Statement getStatement(Connection conn){
		 try {
			 return conn.createStatement();
		 }catch (Exception e) {  
	      e.printStackTrace();
	      return null ;
		 }
	 }

}
