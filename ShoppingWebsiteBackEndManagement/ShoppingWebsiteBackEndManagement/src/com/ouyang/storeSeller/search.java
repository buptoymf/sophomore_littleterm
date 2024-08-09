package com.ouyang.storeSeller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DatabaseMetaData; 
import java.sql.ResultSet;  
import java.sql.SQLException; 

import javax.swing.*;
import javax.swing.table.*;

public class search extends JFrame{

	private Connection conn ;
    private Statement stat ;
    
	JPanel[] jp = new JPanel[3];
	JLabel[] jl = new JLabel[2];
	JTextField content = new JTextField(15);

	JButton JAll = new JButton("Show all products");
	JButton JOK = new JButton("Confirm");
	JButton JClear = new JButton("Clear");
	JButton JBack = new JButton("Back");
	
	JRadioButton byno,byname;
	
	int mode=1;
	
	public search(String username)
	{
		JFrame search = new JFrame();
		
		jp[0] = new JPanel();
		jp[1] = new JPanel();
		jp[2] = new JPanel();
		
		
		jl[0] = new JLabel("Please input the target to search:");
		jl[0].setFont(new Font("Serif", Font.PLAIN, 25));
	
	
		byno = new JRadioButton("Search by No.");
		byname = new JRadioButton("Search by name");
		
		
		byno.setFont(new Font("Serif", Font.PLAIN, 18));
		byname.setFont(new Font("Serif", Font.PLAIN, 18));
		
		content.setFont(new Font("Serif", Font.PLAIN, 18));
		
		byno.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
		
				mode=1;
			}
		});
		
		byname.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
		
				mode=2;
			}
		});
		
		ButtonGroup group=new ButtonGroup();
		group.add(byno);
		group.add(byname);
		byno.setSelected(true);
		
		
		jp[0].add(jl[0]);
		
		jp[1].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		jp[1].add(byno);
		jp[1].add(byname);
		jp[1].add(content);
		
	 	jp[2].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
	 	JAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					
				String sql;
				
					
					sql="select p.productno as ProductNo,p.name as ProductName,p.description as Website,p.category_third_name as Category ,p.status as Product_status ,o.price,o.stock ,o.status as OnSale_status from product p, onsale o WHERE p.productNo = o.productNo and  o.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");";
					

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
			        		new msgbox("Product not found!");
			        		else
			        		{
			        			 try {
									new QueryJFrame(DBUtil.driver, DBUtil.url, sql,"Search result");
								} catch (ClassNotFoundException e) {
									//e.printStackTrace();
								}
			        			
			        			//new msgbox("productNo:" + uid[1] + " storeNo:" + uid[2] + " name:" + uid[3] + " price:" + uid[4] + " description:" + uid[5] + " category:" + uid[6]);
			        		}
			        		
			            
			            ret.close();  
			            stat.close();
			        } catch (SQLException e) {  
			        	//e.printStackTrace();
			            new msgbox("Something wrong when connecting database.");
			        }
			        
				
			}
		});
	 	
	 	
		JOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					
				String sql;
				
					if(mode==1)
					sql="select p.productno as ProductNo,p.name as ProductName,p.description as Website,p.category_third_name as Category ,p.status as Product_status ,o.price,o.stock ,o.status as OnSale_status from product p, onsale o WHERE p.productNo = o.productNo and p.productNo= \"" + content.getText() +"\" and o.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");";
					else
					sql="select p.productno as ProductNo,p.name as ProductName,p.description as Website,p.category_third_name as Category ,p.status as Product_status ,o.price,o.stock ,o.status as OnSale_status from product p, onsale o WHERE p.productNo = o.productNo and p.name = \"" + content.getText() +"\" and storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");";
						
					

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
			        		new msgbox("Product not found!");
			        		else
			        		{
			        			try {
			        	        	
									new QueryJFrame(DBUtil.driver, DBUtil.url, sql,"Search result");
								} catch (ClassNotFoundException e) {
									//e.printStackTrace();
								}
			        			
			        			//new msgbox("productNo:" + uid[1] + " storeNo:" + uid[2] + " name:" + uid[3] + " price:" + uid[4] + " description:" + uid[5] + " category:" + uid[6]);
			        		}
			        		
			            
			            ret.close();  
			            stat.close();
			        } catch (SQLException e) {  
			        	//e.printStackTrace();
			            new msgbox("Something wrong when connecting database.");
			        }
			        
				
			}
		});
		
		JClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					content.setText("");
				
			}
		});
		
		JBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				search.dispose();
					new UI(username);
				
			}
		});
		
		JAll.setFont(new Font("Serif", Font.PLAIN, 18));
		JOK.setFont(new Font("Serif", Font.PLAIN, 18));
		JClear.setFont(new Font("Serif", Font.PLAIN, 18));
		JBack.setFont(new Font("Serif", Font.PLAIN, 18));
		
		jp[2].add(JAll);
		jp[2].add(JOK);
		jp[2].add(JClear);
		jp[2].add(JBack);
		
		search.setLayout(new BorderLayout(40,50));

		search.getContentPane().add("North",jp[0]);
		search.getContentPane().add("Center",jp[1]);
		search.getContentPane().add("South",jp[2]);
	
	
		search.setSize(650, 300);
		search.setLocationRelativeTo(null);
		search.setTitle("Search data");
		search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		search.setVisible(true);
	}
	
	
	/*public static void main(String[] args)
	{
		search s = new search("Shelly");
	}*/
	
}

