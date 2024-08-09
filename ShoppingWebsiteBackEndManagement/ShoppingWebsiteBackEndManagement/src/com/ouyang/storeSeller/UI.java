package com.ouyang.storeSeller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class UI extends JFrame{

	JPanel[] jp = new JPanel[3];
	JLabel[] jl = new JLabel[2];
	
	JButton zeng,shan,gai,cha,ordermanage,ordersearch;
	JButton exit;
	
	
	public UI(String username)
	{

	
		
	JFrame UI = new JFrame();
		
	jp[0] = new JPanel();
	jp[1] = new JPanel();
	jp[2] = new JPanel();

	
	jl[0] = new JLabel("Welcome,storeseller " + username +".");
	jl[0].setFont(new Font("Serif", Font.PLAIN, 25));
	jl[1] = new JLabel("Please choose an operation：");
	jl[1].setFont(new Font("Serif", Font.PLAIN, 25));
	
	zeng = new JButton("Add data");
	shan = new JButton("Delete data");
	gai = new JButton("Update data");
	cha = new JButton("Search data");
	ordermanage = new JButton("Order management");
	ordersearch = new JButton("Search Order");
	
	exit = new JButton("Logout");
	
	zeng.setFont(new Font("Serif", Font.PLAIN, 30));
	shan.setFont(new Font("Serif", Font.PLAIN, 30));
	gai.setFont(new Font("Serif", Font.PLAIN, 30));
	cha.setFont(new Font("Serif", Font.PLAIN, 30));
	ordermanage.setFont(new Font("Serif", Font.PLAIN, 30));
	ordersearch.setFont(new Font("Serif", Font.PLAIN, 30));
	
	exit.setFont(new Font("Serif", Font.PLAIN, 20));
	
	zeng.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
				new add(username);
				UI.dispose();
			
		}
	});
	
	shan.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
				new delete(username);
				UI.dispose();
			
		}
	});
	
	gai.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
				new change(username);
				UI.dispose();
			
		}
	});
	
	cha.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
				new search(username);
				UI.dispose();
			
		}
	});
	
	ordermanage.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
				new ordermanage(username);
				UI.dispose();
			
		}
	});
	
	
	ordersearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
				new ordersearch(username);
				UI.dispose();
			
		}
	});
	
	exit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
				new login();
				UI.dispose();
			
		}
	});
	
	
	
	jp[0].add(jl[0]);
	jp[0].add(jl[1]);
	
	
	jp[1].add(zeng);
	jp[1].add(shan);
	jp[1].add(gai);
	jp[1].add(cha);
	jp[1].add(ordermanage);
	jp[1].add(ordersearch);
	
	jp[1].setLayout(new GridLayout(3,0,40,50));
	
	jp[2].add(exit);
	
	UI.setLayout(new BorderLayout(40,40));

	UI.getContentPane().add("North",jp[0]);
	UI.getContentPane().add("Center",jp[1]);
	UI.getContentPane().add("South",jp[2]);
	
	//UI.pack();
	UI.setSize(650, 500);
	UI.setLocationRelativeTo(null);
	UI.setTitle("BUPT E-commerce Management System");
	UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	UI.setVisible(true);
	
	}
	
	public static void main(String[] args)
	{
		UI u = new UI("12668351");
	}


}

