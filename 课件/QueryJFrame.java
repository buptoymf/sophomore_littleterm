package com.yuan.testJDBC;

//import QueryJFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class QueryJFrame extends JFrame
{
	private Connection conn;                               //���ݿ����Ӷ��� 

    //���췽����driverָ��JDBC��������urlָ�����ݿ�URL��tableָ�����ݿ��б���
    public QueryJFrame(String driver, String url, String table) 
            throws ClassNotFoundException, SQLException
    {
        super(table);
        this.setBounds(300,240,700,320);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Class.forName(driver);                             //ָ��JDBC��������
        this.conn=DriverManager.getConnection(url);        //�������ݿ����Ӷ���
        JTable jtable = query(table);                      //ִ�����ݲ�ѯ���������
        this.getContentPane().add(new JScrollPane(jtable));//�������񣨰��������ӵ�������ݴ����в�
        this.setVisible(true);
    }
    
    //ִ�����ݲ�ѯ��SELECT��䣬��table�����������������ȫ��������ʾ��JTable��
    public JTable query(String table) throws SQLException
    {
        DefaultTableModel tablemodel=new DefaultTableModel(); //���ģ��
        String sql="SELECT * FROM "+table+";";
        Statement stmt=this.conn.createStatement();//1003,1007); //����������
        ResultSet rset=stmt.executeQuery(sql);             //ִ�����ݲ�ѯSELECT���
       
        //��ñ�������������������Ϊ�������ı���
        ResultSetMetaData rsmd = rset.getMetaData();       //���ر����Զ���
        int count = rsmd.getColumnCount();                 //�������
        for (int j=1; j<=count; j++)                       //����������ӵ����ģ����Ϊ���⣬����š�1
            tablemodel.addColumn(rsmd.getColumnLabel(j));

        //��������и���������ӵ����ģ�ͣ�һ�α���
        Object[] columns=new Object[count];                //�����ж������飬���鳤��Ϊ����
        while (rset.next())                                //�����������������ǰ������ÿ��
        {
            for (int j=1; j<=columns.length; j++)          //���ÿ�и���ֵ
                columns[j-1]=rset.getString(j);
            tablemodel.addRow(columns);                    //���ģ�����һ�У�����ָ������ֵ
        }
        rset.close();
        stmt.close();
        return new JTable(tablemodel);                     //�������ָ�����ģ��
    }
    
    public static void main(String args[]) throws ClassNotFoundException, SQLException
    {
        String driver = "com.mysql.jdbc.Driver";           //ָ��MySQL JDBC��������
        String url="jdbc:mysql://127.0.0.1/student?user=root&password=123456";
        new QueryJFrame(driver, url, "student");
    }
 
    public void finalize() throws SQLException             //�����������ر����ݿ�����
    {
        this.conn.close();
    }

}
