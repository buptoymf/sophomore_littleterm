package com.ouyang.storeSeller;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ordermanage {

	private Connection conn ;
    private Statement stat ;
    
	JPanel[] jp = new JPanel[4];
	JLabel[] jl = new JLabel[1];
    JButton JAll,JOK,JBack;
    
    JTextField target = new JTextField(15);
    
    
	public ordermanage(String username)
	{
		JFrame om = new JFrame();

		jp[0] = new JPanel();
		jp[1] = new JPanel();
		jp[2] = new JPanel();
		jp[3] = new JPanel();
		
		jl[0] = new JLabel("Please input the target orderNo:");
		jl[0].setFont(new Font("Serif", Font.PLAIN, 25));

		jp[0].add(jl[0]);
		
		target.setFont(new Font("Serif", Font.PLAIN, 25));
		
		jp[1].add(target);
		
		
		
		//JButton Jfind = new JButton("Search");
		JButton JAll = new JButton("Show all orders");
		JButton JOK = new JButton("Confirm delivery");
		JButton JBack = new JButton("Back");

		JAll.setFont(new Font("Serif", Font.PLAIN, 25));
		JOK.setFont(new Font("Serif", Font.PLAIN, 25));
		JBack.setFont(new Font("Serif", Font.PLAIN, 25));
		
		

		JAll.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				
				
				String sql;
				
				
				sql="select o.OrderNo,o.Customer_username,o.Status, DATE_FORMAT(o.CreateDate,'%Y-%m-%d %H:%i:%s') as CreateDate,DATE_FORMAT(o.DeliveryDate,'%Y-%m-%d %H:%i:%s') as DeliveryDate from `order` o, order_item oi WHERE o.orderNo = oi.orderno  and oi.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");";
				
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
						}
	        			
	        			//new msgbox("productNo:" + uid[1] + " storeNo:" + uid[2] + " name:" + uid[3] + " price:" + uid[4] + " description:" + uid[5] + " category:" + uid[6]);
	        		}
	        		
	        		ret.close(); 
		            stat.close();
	        		
		            
		            
		        }
			 catch (SQLException e) {  
				 //e.printStackTrace();
				 new msgbox("Something wrong when connecting database.");
	        }
				
		       
					 
				
			}
		});
		
		
		JOK.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				
				conn = DBUtil.getConnection();
				stat = DBUtil.getStatement(conn);
				ResultSet ret = null;
				ResultSet ret2 = null;
				
				
		        try {  
		        	ret = stat.executeQuery("select o.status from `order` o, order_item oi WHERE o.orderNo = oi.orderno and o.orderno=\"" + target.getText() +"\" and oi.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");");
		        
		        	
		        	String result = "";
		        	
		        	String[] uid = new String[10];
		        	
		        	while (ret.next()) {  

		                    result += uid[1]; 
	                }
	        		
	
		        	
		        	
	        		if(result.equals(""))
	        		{
	        			throw new SQLException();
	        		}
	            
	        		String newFoundDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					
	        		String sql ="update `order` set status = \"Delivered\", Deliverydate =\""+ newFoundDate +"\" where orderno=\"" + target.getText() +"\";";
	        		String check ="select o.status from `order` o, order_item oi WHERE o.orderNo = oi.orderno and o.orderno=\"" + target.getText() +"\" and oi.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");";
			        
	        		
	        		ret2=stat.executeQuery(check);
        			ret2.next();
        			
        			if(ret2.getString(1).equals("Ordered"))
        			{
        				stat.executeUpdate(sql);			
        				new msgbox("Updated successfully. Status of order \""+ target.getText() +"\" has been \"Delivered\"."); 
        		        
        			}
        			else
        			{
        				new msgbox("This order is already delivered or cancelled.");
        			}
        			
	        	
	        		ret.close(); 
		            stat.close();
	        		
		            
		            
		        }
			 catch (SQLException e) {  
				// e.printStackTrace();
	            new msgbox("Wrong input!");
	        }
				
		       
					 
				
			}
		});
		
		
		JBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					om.dispose();
					new UI(username);
				
			}
		});
		
		
		jp[2].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); 
		
		jp[2].add(JAll);
		jp[2].add(JOK);
		jp[2].add(JBack);
		
		
		om.setLayout(new BorderLayout(40,40));

		om.getContentPane().add("North",jp[0]);
		om.getContentPane().add("Center",jp[1]);
		om.getContentPane().add("South",jp[2]);
		
		om.setSize(600, 300);
		//om.pack();
		om.setLocationRelativeTo(null);
		om.setTitle("Order management");
		om.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		om.setVisible(true);
		
	}
	
	
	/*public static void main(String[] args)
	{
		ordermanage d = new ordermanage("Shelly");
	}*/
}

