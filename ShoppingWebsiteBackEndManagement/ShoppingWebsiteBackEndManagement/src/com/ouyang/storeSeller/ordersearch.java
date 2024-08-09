package com.ouyang.storeSeller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ordersearch {

	JPanel[] jp = new JPanel[5];
	JPanel[] innerjp = new JPanel[9];
	
	JLabel[] jl = new JLabel[6];
	
    JButton Jdate,Jcat,Jdate2,Jpop,Jrate,JBack;
    
    JTextField[] jt = new JTextField[6];
    
	private Connection conn ;
    private Statement stat ;
    
	public ordersearch(String username)
	{
		JFrame os = new JFrame();

		jp[0] = new JPanel();
		jp[1] = new JPanel();
		jp[2] = new JPanel();
		
		for(int i=0;i<9;i++)
		innerjp[i] = new JPanel();

		
		jl[0] = new JLabel("There are several functions of queries in this page.");
		jl[0].setFont(new Font("Serif", Font.PLAIN, 25));
		
		jl[1] = new JLabel("From:");
		jl[1].setFont(new Font("Serif", Font.PLAIN, 18));
		
		jl[2] = new JLabel("To:");
		jl[2].setFont(new Font("Serif", Font.PLAIN, 18));
		
		jl[3] = new JLabel("Category:");
		jl[3].setFont(new Font("Serif", Font.PLAIN, 18));
		
		jl[4] = new JLabel("From:");
		jl[4].setFont(new Font("Serif", Font.PLAIN, 18));
		
		jl[5] = new JLabel("To:");
		jl[5].setFont(new Font("Serif", Font.PLAIN, 18));
		
		for(int i=0;i<6;i++)
		{
			jt[i]= new JTextField(15);
			jt[i].setFont(new Font("Serif", Font.PLAIN, 18));
		}
		
		
		
		
		
		JButton Jdate = new JButton("Show total sum of orders between these dates");
		JButton Jcat = new JButton("Show total sum of orders in category");
		JButton Jdate2 = new JButton("Search top 10 sales between these dates");
		JButton Jpop = new JButton("View most popular top 10");
		JButton Jrate = new JButton("View highest rate top 10");
		JButton Jholiday = new JButton("View orders ordered in holiday");
		JButton JBack = new JButton("Back");

		
		Jdate.setFont(new Font("Serif", Font.PLAIN, 25));
		Jcat.setFont(new Font("Serif", Font.PLAIN, 25));
		Jdate2.setFont(new Font("Serif", Font.PLAIN, 25));
		Jpop.setFont(new Font("Serif", Font.PLAIN, 25));
		Jrate.setFont(new Font("Serif", Font.PLAIN, 25));
		Jholiday.setFont(new Font("Serif", Font.PLAIN, 25));
		JBack.setFont(new Font("Serif", Font.PLAIN, 25));
		
		
		
		Jdate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				
				String sql;
				
				if(jt[0].getText().compareTo(jt[1].getText())>0)		
				{
					new SQLException();
				}
				
				sql="select count(o.orderno) as TotalOrder, sum(number_of_product) as ProductSold,CAST(sum(itemMoney)AS decimal(10,2)) as TotalPrice from `order`o, order_item oi WHERE o.orderNo = oi.orderno and o.createdate  between\"" + jt[0].getText() +"\" and \""+ jt[1].getText() +"\" and oi.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");";
				
					

					conn = DBUtil.getConnection();
					stat = DBUtil.getStatement(conn);
					ResultSet ret = null;
			  
			        try {  
			        		ret = stat.executeQuery(sql);
			        		
			        		String result = "";
			        		
			        		int i=1;
			        		
			        		String[] uid = new String[10];
			        		
			        		while (ret.next()) {  

			        			while(i<=ret.getMetaData().getColumnCount())
			        			{
			        				uid[i-1] = ret.getString(i);  
				                    result += uid[i-1]; 
			        			
				                    i++;
			        			}
			        			
			                }
			        		
			        		if(result.equals(""))
			        		new msgbox("Order not found!");
			        		else
			        		{
			        			 try {
									new QueryJFrame(DBUtil.driver, DBUtil.url, sql,"Search result");
								} catch (ClassNotFoundException e) {
									//e.printStackTrace();
									new msgbox("Something wrong when connecting database.");
								}
			        			
			        			//new msgbox("productNo:" + uid[1] + " storeNo:" + uid[2] + " name:" + uid[3] + " price:" + uid[4] + " description:" + uid[5] + " category:" + uid[6]);
			        		}
			        		
			            
			            ret.close();  
			            stat.close(); 
			        } catch (SQLException e) {  
			        	//e.printStackTrace();
			        	new msgbox("Illegal input!");
			        }
			        
				
			}
			
		});
		
		
		Jcat.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				
				String sql;
				
					sql="select count(o.orderno) as TotalOrders,sum(number_of_product) as ProductSold,CAST(sum(itemMoney)AS decimal(10,2)) as TotalPrice from `order`o, order_item oi, onsale os,product p WHERE o.orderNo = oi.orderno and oi.productno=os.productno and oi.storeno=os.storeno and os.productno=p.productno and p.category_third_name =\"" + jt[2].getText() +"\" and oi.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");";
					
					

					conn = DBUtil.getConnection();
					stat = DBUtil.getStatement(conn);
					ResultSet ret = null;
			  
			        try {  
			        		ret = stat.executeQuery(sql);
			        		
			        		String result = "";
			        		
			        		int i=1;
			        		
			        		String[] uid = new String[10];
			        		
			        		while (ret.next()) {  

			        			while(i<=ret.getMetaData().getColumnCount())
			        			{
			        				uid[i-1] = ret.getString(i);  
				                    result += uid[i-1]; 
			        			
				                    i++;
			        			}
			        			
			                }
			        		
			        		if(result.equals(""))
			        		new msgbox("Order not found!");
			        		else
			        		{
			        			 try {
									new QueryJFrame(DBUtil.driver, DBUtil.url, sql,"Search result");
								} catch (ClassNotFoundException e) {
									//e.printStackTrace();
									new msgbox("Something wrong when connecting database.");
								}
			        			
			        			//new msgbox("productNo:" + uid[1] + " storeNo:" + uid[2] + " name:" + uid[3] + " price:" + uid[4] + " description:" + uid[5] + " category:" + uid[6]);
			        		}
			        		
			             
			            ret.close();  
			            stat.close(); 
			        } catch (SQLException e) {  
			        	//e.printStackTrace();
			        	new msgbox("Illegal input!");
			        }
			        
				
			}
		});
		
		
		Jdate2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				
				String sql;
				
				if(jt[3].getText().compareTo(jt[4].getText())>0)		
				{
					new SQLException();
				}
				
				sql="select p.productNo, p.name as productName, sum(oi.number_of_product) as SalesAmount  from `order`o, order_item oi,onsale os,product p WHERE o.orderNo = oi.orderno and oi.storeNo = os.storeno and os.productno = oi.productno and os.productno = p.productno and o.createdate  between\"" + jt[3].getText() +"\" and \""+ jt[4].getText() +"\" and oi.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\")  group by p.productNo order by sum(oi.number_of_product) desc limit 10;";
					

					conn = DBUtil.getConnection();
					stat = DBUtil.getStatement(conn);
					ResultSet ret = null;
			  
			        try {  
			        		ret = stat.executeQuery(sql);
			        		
			        		String result = "";
			        		
			        		int i=1;
			        		
			        		String[] uid = new String[10];
			        		
			        		while (ret.next()) {  

			        			while(i<=ret.getMetaData().getColumnCount())
			        			{
			        				uid[i-1] = ret.getString(i);  
				                    result += uid[i-1]; 
			        			
				                    i++;
			        			}
			        			
			                }
			        		
			        		if(result.equals(""))
			        		new msgbox("Order not found!");
			        		else
			        		{
			        			try {
									new QueryJFrame(DBUtil.driver, DBUtil.url, sql,"Search result");
								} catch (ClassNotFoundException e) {
									//e.printStackTrace();
									new msgbox("Something wrong when connecting database.");
								}
			        			
			        			//new msgbox("productNo:" + uid[1] + " storeNo:" + uid[2] + " name:" + uid[3] + " price:" + uid[4] + " description:" + uid[5] + " category:" + uid[6]);
			        		}
			        		
			            
			            ret.close();  
			            stat.close(); 
			        } catch (SQLException e) {  
			        	//e.printStackTrace();
			        	new msgbox("Illegal input!");
			        }
			        
				
			}
			
		});
		
		
		Jpop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				
				String sql;
				
				sql="select p.productNo, p.name as productName, sum(oi.number_of_product) as SalesAmount from `order`o, order_item oi,onsale os,product p,comment_rating c WHERE o.orderNo = oi.orderno and oi.storeNo = os.storeno and os.productno = oi.productno and os.productno = p.productno and oi.orderno = c.order_item_orderno and oi.storeno = c.order_item_storeno and oi.productno = c.order_item_productno and oi.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\")  group by p.productNo order by sum(oi.number_of_product)  desc limit 10;";
				

					conn = DBUtil.getConnection();
					stat = DBUtil.getStatement(conn);
					ResultSet ret = null;
			  
			        try {  
			        		ret = stat.executeQuery(sql);
			        		
			        			
			                try {
									new QueryJFrame(DBUtil.driver, DBUtil.url, sql,"Search result");
								} catch (ClassNotFoundException e) {
									//e.printStackTrace();
									new msgbox("Something wrong when connecting database.");
								}
			        			
			        			//new msgbox("productNo:" + uid[1] + " storeNo:" + uid[2] + " name:" + uid[3] + " price:" + uid[4] + " description:" + uid[5] + " category:" + uid[6]);
			        		
			        		
			             
			            ret.close();  
			            stat.close(); 
			        } catch (SQLException e) {  
			        	//e.printStackTrace();
			        	new msgbox("Something wrong when connecting database.");
			        }
			        
				
			}
		});
		
		Jrate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				
				String sql;
				
				sql="select p.productNo, p.name as productName, CAST(sum(rating)/count(rating) AS decimal(5,2))  as AvgRate from `order`o, order_item oi,onsale os,product p,comment_rating c WHERE o.orderNo = oi.orderno and oi.storeNo = os.storeno and os.productno = oi.productno and os.productno = p.productno and oi.orderno = c.order_item_orderno and oi.storeno = c.order_item_storeno and oi.productno = c.order_item_productno and oi.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\")  group by p.productNo order by AvgRate desc limit 10;";
				

					conn = DBUtil.getConnection();
					stat = DBUtil.getStatement(conn);
					ResultSet ret = null;
			  
			        try {  
			        		ret = stat.executeQuery(sql);
			        		
			        			
			                
			        		 try {
									new QueryJFrame(DBUtil.driver, DBUtil.url, sql,"Search result");
								} catch (ClassNotFoundException e) {
									//e.printStackTrace();
									new msgbox("Something wrong when connecting database.");
								}
			        			
			        			//new msgbox("productNo:" + uid[1] + " storeNo:" + uid[2] + " name:" + uid[3] + " price:" + uid[4] + " description:" + uid[5] + " category:" + uid[6]);
			        		
			        		
			             
			            ret.close();  
			            stat.close(); 
			        } catch (SQLException e) {  
			        	//e.printStackTrace();
			        	new msgbox("Something wrong when connecting database.");
			        }
			        
				
			}
		});
		
		Jholiday.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				
				
				String sql;
				
				
				sql="select o.OrderNo,o.Customer_username,o.Status, DATE_FORMAT(o.CreateDate,'%Y-%m-%d %H:%i:%s') as CreateDate,DATE_FORMAT(o.DeliveryDate,'%Y-%m-%d %H:%i:%s') as DeliveryDate ,dayname(o.createdate) as Dayname from `order` o, order_item oi WHERE dayname(o.createdate)like \"S%\" and o.orderNo = oi.orderno  and oi.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");";
				
				conn = DBUtil.getConnection();
				stat = DBUtil.getStatement(conn);
				ResultSet ret = null;
				
		        try {  
		        	ret = stat.executeQuery(sql);
		        
		        	String result = "";
	        		
	        		int i=1;
	        		
	        		String[] uid = new String[10];
	        		
	        		while (ret.next()) {  

	        			while(i<=ret.getMetaData().getColumnCount())
	        			{
	        				uid[i-1] = ret.getString(i);  
		                    result += uid[i-1]; 
	        			
		                    i++;
	        			}
	        			
	        			
	                }
	        		
	        		if(result.equals(""))
	        		new msgbox("Order not found!");
	        		else
	        		{
	        			 try {
							new QueryJFrame(DBUtil.driver, DBUtil.url, sql,"Search result");
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
	        			
	        			//new msgbox("productNo:" + uid[1] + " storeNo:" + uid[2] + " name:" + uid[3] + " price:" + uid[4] + " description:" + uid[5] + " category:" + uid[6]);
	        		}
	        		
	        		ret.close(); 
		            stat.close();
	        		
		            
		            
		        }
			 catch (SQLException e) {  
				// e.printStackTrace();
				 new msgbox("Something wrong when connecting database.");
	        }
				
		       
					 
				
			}
		});
		
		
		JBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				os.dispose();
					new UI(username);
				
			}
		});
		
		
		jp[0].add(jl[0]);
		
		jp[1].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		((JPanel)os.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		TitledBorder t = new TitledBorder("Please input the range of dates(YYYY-MM-DD):");
		t.setTitleFont(new Font("Serif", Font.PLAIN, 20));
		
		innerjp[0].setBorder(t);	
		
		innerjp[0].add(jl[1]);
		innerjp[0].add(jt[0]);
		innerjp[0].add(jl[2]);
		innerjp[0].add(jt[1]);
		
		innerjp[1].add(Jdate);
		
		((JPanel)os.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		t = new TitledBorder("Please input the category of product:");
		t.setTitleFont(new Font("Serif", Font.PLAIN, 20));
		
		innerjp[2].setBorder(t);
		
		innerjp[2].add(jl[3]);
		innerjp[2].add(jt[2]);
		
		innerjp[3].add(Jcat);
		
		((JPanel)os.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		t = new TitledBorder("Please input the range of dates(YYYY-MM-DD):");
		t.setTitleFont(new Font("Serif", Font.PLAIN, 20));
		
		innerjp[4].setBorder(t);
		
		innerjp[4].add(jl[4]);
		innerjp[4].add(jt[3]);
		innerjp[4].add(jl[5]);
		innerjp[4].add(jt[4]);
		innerjp[5].add(Jdate2);
		
		innerjp[6].add(Jpop);
		innerjp[7].add(Jrate);
		innerjp[8].add(Jholiday);
		
		for(int i=0;i<9;i++)
		{
			jp[1].add(innerjp[i]);
		}
		//jp[2].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		//jp[2].add(Jcat);
	
		
		
		
		
		
		jp[2].add(JBack);
		
		
		os.setLayout(new BorderLayout(5, 5));
		
		os.getContentPane().add("North",jp[0]);
		os.getContentPane().add("Center",jp[1]);
		os.getContentPane().add("South",jp[2]);
		
		
		os.setSize(600, 820);
		//om.pack();
		os.setLocationRelativeTo(null);
		os.setTitle("Order search");
		os.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		os.setVisible(true);
		
	}
	
	
	/*public static void main(String[] args)
	{
		ordersearch d = new ordersearch("Shelly");
	}*/
}

