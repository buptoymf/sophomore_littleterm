package com.ouyang.storeSeller;


//import QueryJFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.sql.*;


import java.util.Vector;


public class QueryJFrame extends JFrame
{
	private Connection conn;                               //数据库连接对象 

  //构造方法，driver指定JDBC驱动程序，url指定数据库URL，table指定数据库中表名
  public QueryJFrame(String driver, String url,String sql, String table) 
          throws ClassNotFoundException, SQLException
  {
      super(table);
      this.setSize(650, 300);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      Class.forName(driver);                             //指定JDBC驱动程序
      this.conn=DriverManager.getConnection(url);        //返回数据库连接对象
      JTable jtable = query(table,sql);                      //执行数据查询，创建表格
      this.getContentPane().add(new JScrollPane(jtable));//滚动窗格（包含表格）添加到框架内容窗格中部
      this.setVisible(true);
  }
  
  //执行数据查询的SELECT语句，将table表的所有列名及其中全部数据显示在JTable中
  public JTable query(String table,String sql) throws SQLException
  {
      DefaultTableModel tablemodel=new DefaultTableModel(); //表格模型
      
      Statement stmt=this.conn.createStatement();//1003,1007); //创建语句对象
      ResultSet rset=stmt.executeQuery(sql);             //执行数据查询SELECT语句
     
      //获得表中列数及各列名，作为表格组件的标题
      ResultSetMetaData rsmd = rset.getMetaData();       //返回表属性对象
      int count = rsmd.getColumnCount();  
      
      /*Vector<Integer> columncount = new Vector<Integer>();
		
		for (int i = 0; i < count; i++) {
			columncount.add(i+1);
		}*/
		
      tablemodel.addColumn("Count");
      
      for (int j=1; j<=count; j++)                       //将各列名添加到表格模型作为标题，列序号≥1
          tablemodel.addColumn(rsmd.getColumnLabel(j));
      int k=1;
      //将结果集中各行数据添加到表格模型，一次遍历
      Object[] columns=new Object[count+1];                //创建列对象数组，数组长度为列数
      while (rset.next())                                //迭代遍历结果集，从前向后访问每行
      {
      	columns[0]=k;
      	
          for (int j=2; j<=columns.length; j++)          //获得每行各列值
          	columns[j-1]=rset.getString(j-1);
          
          tablemodel.addRow(columns);      
          k++;//表格模型添加一行，参数指数各列值
      }
      rset.close();
      stmt.close();
      
      DefaultTableCellRenderer r = new DefaultTableCellRenderer();
 
      r.setHorizontalAlignment(JLabel.CENTER);
      JTable mytable = new JTable(tablemodel);
      mytable.setDefaultRenderer(Object.class, r);//设置表格内容居中
      
      mytable.setForeground(Color.BLACK);                   
      mytable.setFont(new Font(null, Font.PLAIN, 10));     
      mytable.setSelectionForeground(Color.DARK_GRAY);      
      mytable.setSelectionBackground(Color.LIGHT_GRAY);    
      mytable.setGridColor(Color.GRAY);                    
      mytable.setRowHeight(15);

      
      mytable.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  
      mytable.getTableHeader().setForeground(Color.RED);              
      mytable.setPreferredScrollableViewportSize(new Dimension(400, 200));
      
      mytable.setRowHeight(30);
      
      
      return mytable;                     //创建表格，指定表格模型
  }
  
 /* public static void main(String args[]) throws ClassNotFoundException, SQLException
  {
  	String sql="SELECT * FROM product;";
      String driver = "com.mysql.jdbc.Driver";           //指定MySQL JDBC驱动程序
      String url="jdbc:mysql://127.0.0.1/shoppingwebsitebackenddb?user=root&password=2540be3ff&useSSL=false";
      new QueryJFrame(driver, url,sql, "Search result");
  }*/

  public void finalize() throws SQLException             //析构方法，关闭数据库连接
  {
      this.conn.close();
  }

}

