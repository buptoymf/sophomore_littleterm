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

public class delete extends JFrame{

	private Connection conn ;
    private Statement stat ;
    
	JPanel[] jp = new JPanel[3];
	JLabel[] jl = new JLabel[2];
	JTextField content = new JTextField(30);
	
	JButton JOK = new JButton("Confirm");
	JButton JClear = new JButton("Clear");
	JButton JBack = new JButton("Back");

	public delete(String username)
	{
		JFrame delete = new JFrame();
		
		jp[0] = new JPanel();
		jp[1] = new JPanel();
		jp[2] = new JPanel();
		
		
		jl[0] = new JLabel("Please input the productNo of the product to be deleted:");
		jl[0].setFont(new Font("Serif", Font.PLAIN, 25));
	
	
		
		jp[0].add(jl[0]);
		
		content.setFont(new Font("Serif", Font.PLAIN, 25));
		jp[1].add(content);
		
		jp[2].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				
					String sql="UPDATE onsale SET status = false WHERE productNo = \"" + content.getText() +"\" and storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");";
					//Delete product by setting its ONSALE status to 0.

					String check ="select status from onsale WHERE productNo = \"" + content.getText() +"\" and storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");";
					
					conn = DBUtil.getConnection();
					stat = DBUtil.getStatement(conn);
					ResultSet ret = null;
					ResultSet ret2 = null;
					
					try {  
			        	ret = stat.executeQuery("select * from product p, onsale o  WHERE p.productNo = o.productNo and p.productNo = \"" + content.getText() +"\" and o.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");");
			        	//Search for the target product to prove that it exists.
			        	
			        	String result = "";
			        	
			        	while (ret.next()) {  
		        			
		        			int i=1;
		        			
		        			
		        			
		        			while(i<=ret.getMetaData().getColumnCount())
		        			{
		        				String uid = ret.getString(i);  
			                    result += uid; 
			                    
			                    i++;
		        			}
		                }
		        		
		        		if(result.equals(""))
		        		{
		        			new msgbox("Product not found!");
		        		}
		        		else
		        		{
		        			ret2=stat.executeQuery(check);
		        			ret2.next();
		        			
		        			//System.out.println(ret2.getString(1));
		        			
		        			if(ret2.getString(1).equals("1"))
		        			{
		        				stat.executeUpdate(sql);			
				        		new msgbox("Deleted successfully.");
		        			}
		        			else
		        			{
		        				new msgbox("This product is already deleted.");
		        			}
		        			
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
				delete.dispose();
					new UI(username);
				
			}
		});
		
		JOK.setFont(new Font("Serif", Font.PLAIN, 18));
		JClear.setFont(new Font("Serif", Font.PLAIN, 18));
		JBack.setFont(new Font("Serif", Font.PLAIN, 18));
		
		jp[2].add(JOK);
		jp[2].add(JClear);
		jp[2].add(JBack);
		
		delete.setLayout(new BorderLayout(40,50));

		delete.getContentPane().add("North",jp[0]);
		delete.getContentPane().add("Center",jp[1]);
		delete.getContentPane().add("South",jp[2]);
	
	
		
		delete.setSize(650, 300);
		delete.setLocationRelativeTo(null);
		delete.setTitle("Delete data");
		delete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		delete.setVisible(true);
	}
	
	
	/*public static void main(String[] args)
	{
		delete d = new delete("Shelly");
	}
	*/
}

