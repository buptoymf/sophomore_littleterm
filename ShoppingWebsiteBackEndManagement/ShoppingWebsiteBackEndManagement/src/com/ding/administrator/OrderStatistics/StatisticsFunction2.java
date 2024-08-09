package com.ding.administrator.OrderStatistics;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import org.jfree.data.category.CategoryDataset;

import com.ding.utils.DataBaseConnection;
import com.ding.utils.JFreeChartOperation;

public class StatisticsFunction2 {
	private String startDate, endDate;
	private JFrame frame;
	private Connection conn;
	private Statement stat;
	//private static final String CHART_PATH = "/Users/ding/Desktop/";

	public StatisticsFunction2(String startDate, String endDate) {
		// TODO Auto-generated constructor stub
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public void build() {
		// TODO Auto-generated method stub
		frame = new JFrame("Function 2");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 300,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 300, 600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container content = frame.getContentPane();
		
		try {
			getGraphForFunction2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		content.add(new Graph());
		
		
		frame.setVisible(true);
	}

	private void getGraphForFunction2() throws Exception {
		ResultSet result;
		ResultSetMetaData rsmd;
		String sql = "select o.customer_userName, sum(itemMoney), oi.orderNo from order_item oi, `order` o where o.orderNo = oi.orderNo and o.orderNo in (select orderNo from ShoppingWebsiteBackEndDB.`order` where status != 'Cancelled' and createDate between '" + this.startDate + "' and '" + this.endDate + "') group by orderNo order by sum(itemMoney) desc limit 10";
		//String sql = "select o.customer_userName, sum(itemMoney), oi.orderNo from order_item oi, `order` o where orderNo in (select orderNo from ShoppingWebsiteBackEndDB.`order` where createDate between '" + this.startDate + "' and '" + this.endDate + "') and o.orderNo = oi.orderNo group by storeNo order by count(orderNo) desc limit 10";
		Object[][] rowData;
		
		conn = DataBaseConnection.getConnection();
		stat = this.conn.createStatement();
        result = stat.executeQuery(sql);
        rsmd = result.getMetaData();
        
        
        int countColumn = rsmd.getColumnCount();
        result.last();
        int countRow = result.getRow();
        rowData = new Object[countRow][countColumn];
        result = stat.executeQuery(sql);
        
        //System.out.print(result.getRow());
        while(result.next()) {
        	//System.out.println(result.getRow());
        	for (int i = 1; i <= countColumn; i++)
        		rowData[result.getRow() - 1][i - 1] = result.getString(i);
        }
        
        double[][] data = new double[1][countRow];
        
        
        String[] columnKeys = new String[countRow];
        String[] rowKeys = {"订单金额"};
        for (int i = 0; i < countRow; i++) {
        	columnKeys[i] = rowData[i][0].toString();
        	data[0][i] = Double.parseDouble(rowData[i][1].toString());
        }
        
        CategoryDataset dataset = JFreeChartOperation.getBarData(data, rowKeys, columnKeys);
        JFreeChartOperation.createBarChart(dataset, "用户名", "订单金额", this.startDate + "至" + this.endDate + "时间段用户下单金额前十", "function2.png");
		
	}
	
	class Graph extends JPanel {
		public void paintComponent(Graphics g) {
			Image i = new ImageIcon("/Users/ding/eclipse-workspace/ShoppingWebsiteBackEndManagement/function2.png").getImage();
			g.drawImage(i, 20, 20, this);
		}
	}

}
