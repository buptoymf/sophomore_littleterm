package com.ding.administrator.ProductManagementForAdmin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

import com.ding.utils.DataBaseConnection;

public class SearchProduct {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JTextField text;
	private JButton continueButton;
	private JPanel tablePanel, searchPanel;
	private Connection conn;
    private Statement stat;
	

	public void build() {
		frame = new JFrame("Store Searching Pane");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 450,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 200, 900, 400);
		Container content = frame.getContentPane();
		frame.setLayout(new GridLayout(1, 2));
		
		String sql = "select * from product";
		searchPanel = new JPanel();
		label = new JLabel("Please input the ambiguous store name to filter");
		text = new JTextField(10);
		
		continueButton = new JButton("continue");
		continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tablePanel.remove(scrollPane);
				tablePanel.repaint();
				try {
					table = getTableForProduct("select * from product where name like '%" + text.getText() + "%'");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				scrollPane = new JScrollPane(table);
				tablePanel.add(scrollPane);
				content.validate();
			}
			
		});
		
		searchPanel.add(label);
		searchPanel.add(text);
		searchPanel.add(continueButton);
		
		
		tablePanel = new JPanel();
		try {
			table = this.getTableForProduct(sql);
			scrollPane = new JScrollPane(table);
			tablePanel.add(scrollPane);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		content.add(tablePanel);
		content.add(searchPanel);
		
		
		frame.setVisible(true);
	}
	
	public JTable getTableForProduct(String sql) throws Exception {
		ResultSet result;
		ResultSetMetaData rsmd;
		//String sql = "select * from product";
		Object[][] rowData;
		Object[] columnNames = {"productNo", "name", "description", "category III", "status"};
		
		conn = DataBaseConnection.getConnection();
		stat = this.conn.createStatement();
        result = stat.executeQuery(sql);
        //if (result.getRow() == 0)
        //	return null;
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
        table.setPreferredScrollableViewportSize(new Dimension(300, 300));
        
        
        result.close();
        stat.close();

		return table;
		
		
		
	}

}
