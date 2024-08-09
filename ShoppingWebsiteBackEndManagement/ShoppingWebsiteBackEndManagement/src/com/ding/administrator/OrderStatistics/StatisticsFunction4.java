package com.ding.administrator.OrderStatistics;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import org.jfree.chart.ChartFrame;
import org.jfree.data.category.CategoryDataset;

import com.ding.utils.DataBaseConnection;
import com.ding.utils.JFreeChartOperation;

public class StatisticsFunction4 {
	private String startDate, endDate;
	private JFrame frame;
	private Connection conn;
	private Statement stat;
	
	public StatisticsFunction4(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public void build() {
		frame = new JFrame("Function 5");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 300,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 300, 600, 600);
		Container content = frame.getContentPane();
		
		try {
			getGraphForFunction4();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		content.add(new Graph());
		
		
		frame.setVisible(true);
		
		
	}
	
	public void getGraphForFunction4() throws SQLException {
		double[][] data = new double[1][12];
		String[] rowKeys = {"订单数量"};
		String[] columnKeys = {"子时", "丑时", "寅时", "卯时", "辰时", "巳时", "午时", "未时", "申时", "酉时", "戌时", "亥时"};;;
		String sql = "";
		ResultSet result;
		
		conn = DataBaseConnection.getConnection();
		stat = this.conn.createStatement();
		//select count(orderNo) from `order` 
		//where hour(createDate) = '0' and createDate > '2016-07-01'
		
		for (int i = 0; i < 22; i += 2)
			sql += "select count(orderNo) from `order` where (status != 'Cancelled' and createDate between '" + this.startDate + "' and '" + this.endDate + "') and hour(createDate) >= '" + i + "' and hour(createDate) < '" + (i + 2) + "' UNION ";
		sql += "select count(orderNo) from `order` where (status != 'Cancelled' and createDate between '" + this.startDate + "' and '" + this.endDate + "') and hour(createDate) >= '22' and hour(createDate) < '24'";
		
		result = stat.executeQuery(sql);
		int k = 0;
		while (result.next()) {
			data[0][k] = result.getInt(1);
			k++;
		}
		
		CategoryDataset dataset = JFreeChartOperation.getBarData(data, rowKeys, columnKeys);
		JFreeChartOperation.createTimeXYChar(this.startDate + "至" + this.endDate + "间订单量24小时统计折线图", "时间(时)", "订单量(单)", dataset, "function4.png");
		
		
	}
	
	class Graph extends JPanel {
		public void paintComponent(Graphics g) {
			Image i = new ImageIcon("/Users/ding/eclipse-workspace/ShoppingWebsiteBackEndManagement/function4.png").getImage();
			g.drawImage(i, 20, 20, this);
		}
	}

}
