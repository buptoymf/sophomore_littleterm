package com.ouyang.storeSeller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;


public class login {

	JPanel[] jp = new JPanel[4];
	JLabel[] jl = new JLabel[3];
	JTextField username = new JTextField(15);
	JPasswordField password = new JPasswordField(15);
	JButton button;
	
	boolean isvalid=false;
	String position = null;
	
	
	
	public login()
	{
		JFrame login = new JFrame();
		
		jp[0] = new JPanel();
		jp[1] = new JPanel();
		jp[2] = new JPanel();
		jp[3] = new JPanel();

		
		jl[0] = new JLabel("Please input your username & password");
		jl[0].setFont(new Font("Serif", Font.PLAIN, 25));
		
		jl[1] = new JLabel("username:");
		jl[1].setFont(new Font("Serif", Font.PLAIN, 15));
		
		jl[2] = new JLabel("password:");
		jl[2].setFont(new Font("Serif", Font.PLAIN, 15));
		
		
		
		button = new JButton("Login");
		//button2 = new JButton("Register");
		
		Dimension size = new Dimension(120,55);
		button.setPreferredSize(size);
		button.setFont(new Font("Serif", Font.PLAIN, 30));
		

		//button2.setPreferredSize(size);
		//button2.setFont(new Font("Serif", Font.PLAIN, 30));

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
	
				
				try (FileReader reader = new FileReader("C:\\Program Files (x86)\\eclipse\\workspace\\JDBCGUI\\user.txt");
			             BufferedReader breader = new BufferedReader(reader) 
			        ) {
			             String text;
			 
			             
			             while((text = breader.readLine()) != null)
			             {
				             if(username.getText().equals(text))
				             {
				            	 text = breader.readLine();
				            	 if(password.getText().equals(text))
					             {
				            		 text = breader.readLine();
				            		 isvalid=true;
				 					position=text;
				 					
				 					//breader.close();
							         //reader.close();
				 					new UI(username.getText());
									login.dispose();
					             }
				            	 else
				            	 {	
				            		 //breader.close();
							         //reader.close();
				            		 break;
				            	 }
					             }
				             else
				             {
				            	 breader.readLine();
				            	 breader.readLine();
				             }
			             }
			             
			             if(isvalid==false)
			             {
			            	 //breader.close();
					         //reader.close();
					         new warning();
			             }
			             
			             
			             
			             
			        } catch (IOException e) {
			        	e.printStackTrace();
			        	System.out.println("Failed to read the file!");
			        	System.exit(-1);
			        }
				
			}
		});
		
		login.setLayout(new GridLayout(4, 1));
		
		
		
		jp[0].add(jl[0]);
		jp[1].add(jl[1]);
		jp[1].add(username);
		jp[2].add(jl[2]);
		jp[2].add(password);
		jp[3].add(button);
		//jp[3].add(button2);

		
		
		jp[1].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jp[2].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jp[3].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		login.getContentPane().add(jp[0]);
		login.getContentPane().add(jp[1]);
		login.getContentPane().add(jp[2]);
		login.getContentPane().add(jp[3]);
		
		login.setSize(450, 350);
		login.setLocationRelativeTo(null);
		login.setTitle("BUPT E-commerce Management System");
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);
		
		
		
	}
	
	
	
	public static void main(String[] args)
	{
		login l = new login();
	}
}

