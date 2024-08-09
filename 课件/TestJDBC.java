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
		DatabaseMetaData dbmd = conn.getMetaData();        //������������ݿ��������Ϣ
	    System.out.println("JDBC��������"+dbmd.getDriverName()+"��"+dbmd.getDriverVersion()
	            +"\nJDBC URL��"+dbmd.getURL()+"\n���ݿ⣺"+dbmd.getDatabaseProductName()
	            +"���汾��"+dbmd.getDatabaseProductVersion()+"���û�����"+dbmd.getUserName()+"\n");
	    conn.close();
	 }
    
    public void showData(String sql) throws Exception
    {
    	conn = DBUtil.getConnection();
		stat = DBUtil.getStatement(conn);
		ResultSet ret = null;
  
        try {  
            ret = stat.executeQuery(sql);//ִ����䣬�õ������  
            while (ret.next()) {  
                String uid = ret.getString(1);  
                String ufname = ret.getString(2);  
                String ulname = ret.getString(3);  
                String udate = ret.getString(4);  
                System.out.println(uid + "\t" + ufname + "\t" + ulname + "\t" + udate );  
            }//��ʾ����  
            ret.close();  
            stat.close();//�ر�����  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }
    
	public static void main(String[] args) throws Exception
	{
		TestJDBC test = new TestJDBC();
		String sql = null; 
		
		sql = "select * from student";//SQL��� 
		test.showData(sql);
		test.showMetaData();		
	}
  
}
