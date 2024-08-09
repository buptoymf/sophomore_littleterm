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
import javax.swing.border.TitledBorder;

public class change extends JFrame{

	private Connection conn ;
    private Statement stat ;
    
	JPanel[] jp = new JPanel[5];
	JLabel[] jl = new JLabel[6];
	
	JTextField target = new JTextField(15);
	JTextField[] jt = new JTextField[7];
	
	JButton Jfind = new JButton("Search");
	JButton JOK = new JButton("Confirm");
	JButton JClear = new JButton("Clear");
	JButton JBack = new JButton("Back");

	public change(String username)
	{
		JFrame change = new JFrame();
		
		jp[0] = new JPanel();
		jp[1] = new JPanel();
		jp[2] = new JPanel();
		//jp[3] = new JPanel();
		jp[4] = new JPanel();
		
		
		jl[0] = new JLabel("Data update: ");
		jl[0].setFont(new Font("Serif", Font.PLAIN, 25));
	
		jl[2] = new JLabel("Please input the target productNo and press 'Search' button first:");
		jl[2].setFont(new Font("Serif", Font.PLAIN, 20));
		
		//jl[3] = new JLabel("");
		//jl[3].setFont(new Font("Serif", Font.PLAIN, 18));
		
		jl[4] = new JLabel("",JLabel.CENTER);
		jl[4].setFont(new Font("Serif", Font.PLAIN, 18));
		
		jl[5] = new JLabel("",JLabel.CENTER);
		jl[5].setFont(new Font("Serif", Font.PLAIN, 18));
		
		int i;
		
		for(i=0;i<5;i++)
		{
		jt[i] = new JTextField(15);
		jt[i].setFont(new Font("Serif", Font.PLAIN, 18));
		}
		
		
		jp[0].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		jp[0].add(jl[0]);
		
		target.setFont(new Font("Serif", Font.PLAIN, 15));
		
		jp[0].add(jl[2]);
		jp[0].add(target);
		

		((JPanel)change.getContentPane()).setBorder(BorderFactory.createEmptyBorder(30,30,55,55));
		TitledBorder t = new TitledBorder("Please update these elements and then press 'Confirm' button:");
		t.setTitleFont(new Font("Serif", Font.PLAIN, 20));
		
		jp[2].setBorder(t);	
		
		jp[2].add(jl[4]);
		jp[2].add(jl[5]);
		
		for(i=0;i<5;i++)
		jp[2].add(jt[i]);

		
		jp[2].setLayout(new GridLayout(2,0,50,10));
		
		//jp[3].add(jl[3]);
		
		jp[4].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		Jfind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
					//String sql="UPDATE product SET " + content.getText() + " where productNo =\"" + target.getText() +"\";";
					

					conn = DBUtil.getConnection();
					stat = DBUtil.getStatement(conn);
					ResultSet ret = null;
			  
			        try {  
			        	ret = stat.executeQuery("select * from product p ,onsale o WHERE p.productNo = o.productNo and p.productNo = \"" + target.getText() +"\" and o.storeNo = (select storeNo from storeseller where seller_username = \"" + username + "\");");
			        	//Search first,to make sure that it exists
			        	String result = "";
			        	
			        	String[] uid = new String[10];
			        	
			        	while (ret.next()) {  
		        			
		        			int i=1;
		        			
		        			
		        			
		        			while(i<=ret.getMetaData().getColumnCount())
		        			{
		        				uid[i-1]  = ret.getString(i);  
			                    result += uid[i-1]; 
			                    
			                    i++;
		        			}
		                }
		        		

			        	
		        		if(result.equals(""))
		        		{
		        			new msgbox("Wrong input!");
		        		}
		        		else
		        		{
		        			
		        			
		        			//jl[3].setText("Please update these elements and then press 'Confirm' button:");
		        			
		        			//for(int i=0;i<9;i++)
		        			//System.out.println(uid[i]);
		        			
		        			
		        			jl[4].setText(uid[0]);
		        			jl[5].setText(uid[5]);
		        			
		        			jt[0].setText(uid[1]);
			        		jt[1].setText(uid[2]);
			        		jt[2].setText(uid[3]);
			        		//jt[3].setText(uid[6]);
			        		jt[3].setText(uid[7]);
			        		jt[4].setText(uid[8]);
		        		}
		            
		            
		        		ret.close(); 
			            stat.close();
			        } catch (SQLException e) {  
			            new msgbox("Something wrong when connecting database.");
			        }
			        
				
			}
		});
		
		JOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
					String sql="UPDATE product SET name = \""+ jt[0].getText() + "\",description = \""+ jt[1].getText() + "\",category_third_name = \""+ jt[2].getText() +"\" where productNo = \""+ target.getText() +"\";";
					String sql2="UPDATE onsale SET price = \""+ jt[3].getText() + "\",stock = \""+ jt[4].getText() +"\" where productno = \"" + target.getText() + "\";";
					//数据修改命令

					conn = DBUtil.getConnection();
					stat = DBUtil.getStatement(conn);
					//ResultSet ret = null;
			  
					try {
						
						if(target.getText().equals(""))
						{
							new msgbox("Please input the product number first!");
						}
						else
						{
		        		stat.executeUpdate(sql);
		        		stat.executeUpdate(sql2);
			        	new msgbox("Updated successfully."); 
            
						}
		        		//ret.close(); 
			            stat.close();//关闭连接  
					} catch (SQLException e) {  
			        	//e.printStackTrace();
			            new msgbox("Illegal input!Try again!");
			        }
				
			}
		});
		
		JClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					int i;
					for(i=0;i<5;i++)
					jt[i].setText("");
					
					jl[4].setText("");
					jl[5].setText("");
					
					target.setText("");
			}
		});
		
		JBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				change.dispose();
					new UI(username);
				
			}
		});
		
		Jfind.setFont(new Font("Serif", Font.PLAIN, 18));
		JOK.setFont(new Font("Serif", Font.PLAIN, 18));
		JClear.setFont(new Font("Serif", Font.PLAIN, 18));
		JBack.setFont(new Font("Serif", Font.PLAIN, 18));
		
		jp[4].add(Jfind);
		jp[4].add(JOK);
		jp[4].add(JClear);
		jp[4].add(JBack);
		
		change.setLayout(new BorderLayout(10,50));
		
		change.getContentPane().add("North",jp[0]);
		//change.getContentPane().add("North",jp[1]);
		change.getContentPane().add("Center",jp[2]);
		//change.getContentPane().add(jp[3]);	
		change.getContentPane().add("South",jp[4]);
		
		
		change.setSize(650, 500);
		change.pack();
		change.setLocationRelativeTo(null);
		change.setTitle("Update data");
		change.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		change.setVisible(true);
	}
	
	
	/*public static void main(String[] args)
	{
		change c = new change("Shelly");
	}*/
	
}

