package com.ouyang.storeSeller;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.SQLException; 


public class insertdata {

	private Connection conn ;
    private Statement stat ;
	
	public insertdata()
	{
		conn = DBUtil.getConnection();
		stat = DBUtil.getStatement(conn);//Get connected to database 
		//ResultSet ret = null;
	
		
		
	    try {  
	    	
	    	//插数据部分
	    	
	    		stat.executeUpdate("Insert into category_first values(\"thing\");");
	    		
	    	//插数据部分	
	    		
	    		new msgbox("Add new data successfully.");
	       
	     //   ret.close();  
	        stat.close();//Turn off the connection  
	    } catch (SQLException e) {  
	       e.printStackTrace();
	    }
	}

	
	public static void main(String[] args)
	{
		new insertdata();
	}
}

