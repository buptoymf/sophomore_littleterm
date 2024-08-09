package com.ding.utils;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RetrievalStore {
	private Connection conn;
	private Statement stat;

	
	public JTable getStoreTable() throws Exception {
		ResultSet result;
		ResultSetMetaData rsmd;
		DefaultTableModel tablemodel = new DefaultTableModel(); //表格模型
		//String sql = "SELECT storeNo, name, type, serviceHotline, foundDate FROM store WHERE status = true";
		String sql = "SELECT * FROM store WHERE status = true";
		
		conn = DataBaseConnection.getConnection();
		stat = this.conn.createStatement();
        result = stat.executeQuery(sql);
        rsmd = result.getMetaData();
        
        int count = rsmd.getColumnCount();                     //获得列数
        //tablemodel.addColumn("storeNo");
        //tablemodel.addColumn("name");
        //tablemodel.addColumn("type");
        //tablemodel.addColumn("service hotline");
        //tablemodel.addColumn("found date");
        for (int j = 1; j <= count; j++)                       //将各列名添加到表格模型作为标题，列序号≥1
            tablemodel.addColumn(rsmd.getColumnLabel(j));
        
        //将结果集中各行数据添加到表格模型，一次遍历
        Object[] columns = new Object[count];                //创建列对象数组，数组长度为列数
        while (result.next()) {                              //迭代遍历结果集，从前向后访问每行
            for (int j = 1; j <= columns.length; j++)            //获得每行各列值
                columns[j - 1]=result.getString(j);
            tablemodel.addRow(columns);                      //表格模型添加一行，参数指数各列值
        }
        result.close();
        stat.close();
        
        return new JTable(tablemodel);                       //创建表格，指定表格模型
	}
	
	public JTable getStoreTableWithoutModel() throws Exception {
		ResultSet result;
		ResultSetMetaData rsmd;
		String sql = "SELECT storeNo, name, type, serviceHotline, foundDate FROM store WHERE status = true";
		Object[][] rowData;
		Object[] columnNames = {"storeNo", "name", "type", "service hotline", "found date"};
		
		conn = DataBaseConnection.getConnection();
		stat = this.conn.createStatement();
        result = stat.executeQuery(sql);
        rsmd = result.getMetaData();
        
        int countColumn = rsmd.getColumnCount();
        result.last();
        int countRow = result.getRow();
        rowData = new Object[countRow][countColumn];
        result = stat.executeQuery(sql);
        
        while(result.next()) {
        	//System.out.println(result.getRow());
        	for (int i = 1; i <= countColumn; i++)
        		rowData[result.getRow() - 1][i - 1] = result.getString(i);
        }
        
        JTable table = new JTable(rowData, columnNames);
        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 10));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色
        table.setRowHeight(20);

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色

        table.setRowHeight(30);
        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 70));
        
        
        result.close();
        stat.close();

		return table;
	}
	
}
