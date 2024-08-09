package com.ding.administrator.OrderStatistics;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import org.jfree.data.category.CategoryDataset;

import com.ding.utils.DataBaseConnection;
import com.ding.utils.JFreeChartOperation;

public class StatisticsFunction1 {
	private String startDate, endDate;
	private JFrame frame;
	private Connection conn;
	private Statement stat;
	private static final String CHART_PATH = "/Users/ding/Desktop/";

	public StatisticsFunction1(String startDate, String endDate) {
		// TODO Auto-generated constructor stub
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public void build() {
		frame = new JFrame("Function 2");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 300,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 300, 600, 600);
		Container content = frame.getContentPane();
		
		try {
			getGraphForFunction1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		content.add(new Graph());
		
		
		frame.setVisible(true);
	}
	
	public void getGraphForFunction1() throws Exception {
		ResultSet result;
		ResultSetMetaData rsmd;
		String sql = "select storeNo, count(distinct orderNo), sum(itemMoney) from order_item  where orderNo in (select orderNo from ShoppingWebsiteBackEndDB.`order` where status != 'Cancelled' and createDate between '" + this.startDate + "' and '" + this.endDate + "') group by storeNo order by count(orderNo) desc limit 10";
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
        
        while(result.next()) {
        	//System.out.println(result.getRow());
        	for (int i = 1; i <= countColumn; i++)
        		rowData[result.getRow() - 1][i - 1] = result.getString(i);
        }
        
        double[][] data = new double[1][countRow];
        
        
        String[] columnKeys = new String[countRow];
        String[] rowKeys = {"销售量"};
        for (int i = 0; i < countRow; i++) {
        	columnKeys[i] = rowData[i][0].toString();
        	data[0][i] = Integer.parseInt(rowData[i][1].toString());
        }
        
        CategoryDataset dataset = JFreeChartOperation.getBarData(data, rowKeys, columnKeys);
        JFreeChartOperation.createBarChart(dataset, "商店编号", "销售量", this.startDate + "至" + this.endDate + "时间段内销售量前十", "function1.png");
    	
	}
	
    
    
	class Graph extends JPanel {
		public void paintComponent(Graphics g) {
			Image i = new ImageIcon("/Users/ding/eclipse-workspace/ShoppingWebsiteBackEndManagement/function1.png").getImage();
			g.drawImage(i, 20, 20, this);
		}
	}

}
