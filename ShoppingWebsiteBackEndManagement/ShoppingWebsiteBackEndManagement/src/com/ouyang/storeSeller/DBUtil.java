package com.ouyang.storeSeller;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.Statement;

public class DBUtil {
	 public static String url = "jdbc:mysql://127.0.0.1/shoppingwebsitebackenddb?user=root&password=Dings0551root&useSSL=false";  
	 public static String driver = "com.mysql.cj.jdbc.Driver";  
	 public static String user = "root";  
	 public static String password = "Dings0551root";  
	 
	 public static Connection getConnection() {
		 Connection conn = null;  
		 try {  
			    Class.forName(driver);//ָ����������  
	            conn = DriverManager.getConnection(url, user, password);//��ȡ����  
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
