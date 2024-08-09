package com.ding.administrator.OrderStatistics;

import java.awt.*;
import javax.swing.*;

import com.ding.utils.DataBaseConnection;

import java.sql.*;

public class StatisticsFunction5 {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private Connection conn;
	private Statement stat;

	public void build() {
		frame = new JFrame("Function 6");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 300,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 200, 600, 400);
		Container content = frame.getContentPane();
		
		try {
			table = this.getTableForFunction5();
			scrollPane = new JScrollPane(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		content.add(scrollPane);
		
		
		frame.setVisible(true);
	}
	
	
	public JTable getTableForFunction5() throws Exception {
		ResultSet result;
		ResultSetMetaData rsmd;
		String sql = "select * from `order`";
		Object[][] rowData;
		Object[] columnNames = {"orderNo", "customer username", "status", "create date", "delivery date"};
		
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
        table.setRowHeight(15);

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色

        table.setRowHeight(30);
        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 200));
        
        
        result.close();
        stat.close();

		return table;
	}
}
