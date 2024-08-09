package com.ding.administrator.OrderStatistics;

import java.awt.*;
import java.sql.*;
import org.jfree.chart.ChartFrame;

import com.ding.utils.DataBaseConnection;
import com.ding.utils.JFreeChartOperation;

public class StatisticsFunction3 {
	private String startDate, endDate;
	private int startYear, endYear;
	private int startMonth, endMonth;
	private boolean sameYear;
	private ChartFrame frame;
	private Connection conn;
	private Statement stat;

	public StatisticsFunction3(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		
		this.startYear = Integer.parseInt(startDate.substring(0, 4));
		this.endYear = Integer.parseInt(endDate.substring(0, 4));
		
		if (this.startDate.charAt(5) == '0')
			this.startMonth = Integer.parseInt(startDate.substring(6));
		else
			this.startMonth = Integer.parseInt(startDate.substring(5));
		
		if (this.endDate.charAt(5) == '0')
			this.endMonth = Integer.parseInt(startDate.substring(6));
		else
			this.endMonth = Integer.parseInt(endDate.substring(5));
		
		this.calcDateInterval();
	}
	
	public void calcDateInterval() {
		
		if (this.startYear == this.endYear)
			sameYear = true;
		else
			sameYear = false;
		
		
	}

	
	public void build() {
		// TODO Auto-generated method stub
				try {
					frame = this.getGraphForFunction3();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 300,
						((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 300, 600, 600);
				
				
				frame.setVisible(true);
	}
	
	public ChartFrame getGraphForFunction3() throws Exception {
		//select orderNo, createDate from `order` where year(createDate) = '2018' and month(createDate) = '01';
		ResultSet result;
		ResultSetMetaData rsmd;
		String sql = "";
		conn = DataBaseConnection.getConnection();
		stat = this.conn.createStatement();
		//select count(orderNo) from `order` where year(createDate) = '2018' and month(createdate) = '01'
		if (this.sameYear) {
			int count = this.endMonth - this.startMonth + 1;
			for (int i = 0; i < count - 1; i++)
				sql += "select count(orderNo) from `order` where year(createDate) = '" + this.startYear + "' and month(createDate) = '" + (this.startMonth + i) + "' UNION ";
			sql += "select count(orderNo) from `order` where year(createDate) = '" + this.startYear + "' and month(createDate) = '" + this.endMonth + "'";
		}
		else {
			for (int i = this.startMonth; i <= 12; i++)
				sql += "select count(orderNo) from `order` where year(createDate) = '" + this.startYear + "' and month(createDate) = '" + i + "' UNION ";
			for (int i = 1; i < this.endMonth; i++)
				sql += "select count(orderNo) from `order` where year(createDate) = '" + this.endYear + "' and month(createDate) = '" + i + "' UNION ";
			sql += "select count(orderNo) from `order` where year(createDate) = '" + this.endYear + "' and month(createDate) = '" + this.endMonth + "'";
			System.out.println(sql);
		}
		
		
		
        result = stat.executeQuery(sql);
        result.last();
        int countRow = result.getRow();
        
		int[] barData = new int[countRow];
		int j = 0;
		//result.first();
		result = stat.executeQuery(sql);
		while (result.next()) {
			barData[j] = result.getInt(1);
			j++;
		}
		
		
		//select cast(sum(oi.itemMoney) as decimal(15,2)) from `order` o, order_item oi 
		//where o.orderNo = oi.orderNo and year(o.createDate) = '2018' and month(createDate) = '1';
		
		
		sql = "";
		if (this.sameYear) {
			int count = this.endMonth - this.startMonth + 1;
			for (int i = 0; i < count - 1; i++)
				sql += "select cast(sum(oi.itemMoney) as decimal(15,2)) from `order` o, order_item oi where o.status != 'Cancelled' and o.orderNo = oi.orderNo and year(o.createDate) = '" + this.startYear + "' and month(o.createDate) = '" + (this.startMonth + i) + "' UNION ";
			sql += "select cast(sum(oi.itemMoney) as decimal(15,2)) from `order` o, order_item oi where o.status != 'Cancelled' and o.orderNo = oi.orderNo and year(o.createDate) = '" + this.startYear + "' and month(o.createDate) = '" + this.endMonth + "'";
		}
		else {
			for (int i = this.startMonth; i <= 12; i++)
				sql += "select cast(sum(oi.itemMoney) as decimal(15,2)) from `order` o, order_item oi where o.status != 'Cancelled' and o.orderNo = oi.orderNo and year(o.createDate) = '" + this.startYear + "' and month(o.createDate) = '" + i + "' UNION ";
			for (int i = 1; i < this.endMonth; i++)
				sql += "select cast(sum(oi.itemMoney) as decimal(15,2)) from `order` o, order_item oi where o.status != 'Cancelled' and o.orderNo = oi.orderNo and year(o.createDate) = '" + this.endYear + "' and month(o.createDate) = '" + i + "' UNION ";
			sql += "select cast(sum(oi.itemMoney) as decimal(15,2)) from `order` o, order_item oi where o.status != 'Cancelled' and o.orderNo = oi.orderNo and year(o.createDate) = '" + this.endYear + "' and month(o.createDate) = '" + this.endMonth + "'";
		}
		
		
		result = stat.executeQuery(sql);
        //result.last();
        //int countRow = result.getRow();
        
		double[] XYData = new double[countRow];
		int k = 0;
		while (result.next()) {
			XYData[k] = result.getDouble(1);
			k++;
		}
		
		String[] xData;
		if (this.sameYear) {
			int count = this.endMonth - this.startMonth + 1;
			xData = new String[count];
			for (int i = 0; i < count; i++)
				xData[i] = String.valueOf(this.startYear) + "年" + String.valueOf((this.startMonth + i)) + "月";
		}
		else {
			int count = 12 - this.startMonth + 1 + this.endMonth;
			xData = new String[count];
			for (int i = this.startMonth; i <= 12; i++)
				xData[i - this.startMonth] = String.valueOf(this.startYear) + "年" + String.valueOf(i) + "月";
			for (int i = 1; i <= this.endMonth; i++)
				xData[12 - this.startMonth + i] = String.valueOf(this.endYear) + "年" + String.valueOf(i) + "月";
		}
		
		ChartFrame chart = JFreeChartOperation.createXYAndBar(xData, barData, XYData);
		
		return chart;
	}

}
