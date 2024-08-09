package com.yuan.testJDBC;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DatabaseMetaData; 
import java.sql.ResultSet;  
import java.sql.SQLException; 

public class TestJDBC {
    private Connection conn ;
    private Statement stat ;
    
    public void showMetaData() throws Exception
	 {
    	conn = DBUtil.getConnection();
		DatabaseMetaData dbmd = conn.getMetaData();        //获得所连接数据库的属性信息
	    System.out.println("JDBC驱动程序："+dbmd.getDriverName()+"，"+dbmd.getDriverVersion()
	            +"\nJDBC URL："+dbmd.getURL()+"\n数据库："+dbmd.getDatabaseProductName()
	            +"，版本："+dbmd.getDatabaseProductVersion()+"，用户名："+dbmd.getUserName()+"\n");
	    conn.close();
	 }
    
    public void showData(String sql) throws Exception
    {
    	conn = DBUtil.getConnection();
		stat = DBUtil.getStatement(conn);
		ResultSet ret = null;
  
        try {  
            ret = stat.executeQuery(sql);//执行语句，得到结果集  
            while (ret.next()) {  
                String uid = ret.getString(1);  
                String ufname = ret.getString(2);  
                String ulname = ret.getString(3);  
                String udate = ret.getString(4);  
                System.out.println(uid + "\t" + ufname + "\t" + ulname + "\t" + udate );  
            }//显示数据  
            ret.close();  
            stat.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }
    
	public static void main(String[] args) throws Exception
	{
		TestJDBC test = new TestJDBC();
		String sql = null; 
		
		sql = "select * from student";//SQL语句 
		test.showData(sql);
		test.showMetaData();		
	}
  
}
