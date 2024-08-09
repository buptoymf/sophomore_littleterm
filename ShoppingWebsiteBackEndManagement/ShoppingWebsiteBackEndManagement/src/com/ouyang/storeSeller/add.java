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

public class add extends JFrame{

	private Connection conn ;
    private Statement stat ;
    
	JPanel[] jp = new JPanel[3];
	JLabel[] jl = new JLabel[2];
	JTextField[] jt = new JTextField[6];
	
	

	
	JButton JOK = new JButton("Confirm");
	JButton JClear = new JButton("Clear");
	JButton JBack = new JButton("Back");

	public add(String username)
	{
		JFrame add = new JFrame();
		
		jp[0] = new JPanel();
		jp[1] = new JPanel();
		jp[2] = new JPanel();
		
		
		jl[0] = new JLabel("Please input each of the elements,then press 'Confirm' :");
		jl[0].setFont(new Font("Serif", Font.PLAIN, 25));
	
		int i;
		
		for(i=0;i<6;i++)
		{
			jt[i] = new JTextField(15);
			jt[i].setFont(new Font("Serif", Font.PLAIN, 25));
		}
		
		jt[0].setText("Input productNo:");
		jt[1].setText("Input name:");
		jt[2].setText("Input description:");
		jt[3].setText("Input category:");
		jt[4].setText("Input price:");
		jt[5].setText("Input stock:");
		
		jp[0].add(jl[0]);
		
		
		
		((JPanel)add.getContentPane()).setBorder(BorderFactory.createEmptyBorder(30,30,25,25));
		TitledBorder t = new TitledBorder("Add data to its corresponding label:");
		t.setTitleFont(new Font("Serif", Font.PLAIN, 20));
		
		jp[1].setBorder(t);		
		
		for(i=0;i<6;i++)
		jp[1].add(jt[i]);
		
		jp[1].setLayout(new GridLayout(3,0,30,30));
		
		jp[2].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
					String sql="Insert into product values(\"" + jt[0].getText() +"\",\""+ jt[1].getText() +"\",\""+  jt[2].getText() +"\",\""+ jt[3].getText() +"\",true);";//数据添加命令
					String sql2= "Insert into onsale values((select storeNo from storeseller where seller_username =\"" + username +"\"),\""+ jt[0].getText() + "\",\""+ jt[4].getText() +"\",\""+ jt[5].getText() +"\",true);";

					conn = DBUtil.getConnection();
					stat = DBUtil.getStatement(conn);//Get connected to database 
					//ResultSet ret = null;
			  
			        try {  
			        		stat.executeUpdate(sql);
			        		stat.executeUpdate(sql2);//Implement the queries
			        		new msgbox("Add new data successfully.");
			           
			         //   ret.close();  
			            stat.close();//Turn off the connection  
			        } catch (SQLException e) {  
			            new msgbox("The product exists or illegal input!");
			        }
			        
				
			}
		});
		
		JClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					int i;
					for(i=0;i<6;i++)
					jt[i].setText("");
				
			}
		});
		
		JBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					add.dispose();
					new UI(username);
				
			}
		});
		
		JOK.setFont(new Font("Serif", Font.PLAIN, 18));
		JClear.setFont(new Font("Serif", Font.PLAIN, 18));
		JBack.setFont(new Font("Serif", Font.PLAIN, 18));
		
		jp[2].add(JOK);
		jp[2].add(JClear);
		jp[2].add(JBack);
		
		add.setLayout(new BorderLayout(40,50));

		add.getContentPane().add("North",jp[0]);
		add.getContentPane().add("Center",jp[1]);
		add.getContentPane().add("South",jp[2]);
	
	
	add.setSize(650, 500);
	add.setLocationRelativeTo(null);
	add.setTitle("Add data");
	add.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	add.setVisible(true);
	}
	
	
	/*public static void main(String[] args)
	{
		add a = new add("Shelly");
	}*/
	
}

