package com.ding.administrator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.ding.administrator.CategoryManagement.CategoryManagement;
import com.ding.administrator.CustomerManagement.CustomerManagement;
import com.ding.administrator.OrderStatistics.OrderStatistics;
import com.ding.administrator.OrderStatistics.StatisticsFunction0;
import com.ding.administrator.ProductManagementForAdmin.ProductManagement;
import com.ding.administrator.StoreManagement.StoreManagement;

public class AdministratorGUI {
	private String username;
	private JFrame frame;
	private JRadioButton product, customer, category, store, statistics;
	private ButtonGroup group;
	private JLabel introLabel;
	private JButton exit;
	private JPanel introPanel, choicePanel, mainPanel;
	
	public AdministratorGUI(String username) {
		this.username = username;
	}
	
	public void build() {
		frame = new JFrame("Administrator Back End Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 600,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 400, 1200, 800);
		Container content = frame.getContentPane();
		
		
		introLabel = new JLabel("Welcome, " + this.username + "!");
		introLabel.setFont(new Font("Bold", Font.BOLD, 15));
		introPanel = new JPanel();
		introPanel.add(introLabel);
		introPanel.setBounds(0, 0, 100, 100);
		content.add(introPanel);
		//content.add(BorderLayout.NORTH, introLabel);
		
		
		choicePanel = new JPanel();
		choicePanel.setLayout(new GridLayout(5, 1));
		group = new ButtonGroup();
		
		
		product = new JRadioButton("Product Management");
		customer = new JRadioButton("Customer Management");
		category = new JRadioButton("Category Management");
		store = new JRadioButton("Store Management");
		statistics = new JRadioButton("Order Statistics");
		
		product.addItemListener(new ChoiceListener());
		customer.addItemListener(new ChoiceListener());
		category.addItemListener(new ChoiceListener());
		store.addItemListener(new ChoiceListener());
		statistics.addItemListener(new ChoiceListener());
		
		group.add(product);
		group.add(customer);
		group.add(category);
		group.add(store);
		group.add(statistics);
		
		
		choicePanel.add(product);
		choicePanel.add(customer);
		choicePanel.add(category);
		choicePanel.add(store);
		choicePanel.add(statistics);
		choicePanel.setBounds(0, 200, 200, 400);
		content.add(choicePanel);
		//content.add(BorderLayout.CENTER, choicePanel);
		
		
		exit = new JButton("EXIT");
		exit.addActionListener(new ExitListener());
		exit.setBounds(0, 700, 200, 50);
		content.add(exit);
		
		
		//mainPanel = new JPanel();
		//mainPanel.setBounds(200, 0, 1000, 800);
		
		//content.add(mainPanel);
		
		
		frame.setVisible(true);
	}
	
	class ChoiceListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == product) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					mainPanel = new ProductManagement().buildPanel();
					frame.getContentPane().add(mainPanel);
				}
				else if (e.getStateChange() == ItemEvent.DESELECTED) {
					mainPanel.setVisible(false);
				}
				
			}
			
			else if (e.getSource() == customer) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					mainPanel = new CustomerManagement().buildPanel();
					frame.getContentPane().add(mainPanel);
				}
				else if (e.getStateChange() == ItemEvent.DESELECTED) {
					mainPanel.setVisible(false);
				}
			}
			
			else if (e.getSource() == category) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					mainPanel = new CategoryManagement().buildPanel();
					frame.getContentPane().add(mainPanel);
				}
				else if (e.getStateChange() == ItemEvent.DESELECTED) {
					mainPanel.setVisible(false);
				}
			}
			
			else if (e.getSource() == store) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					mainPanel = new StoreManagement().buildPanel();
					frame.getContentPane().add(mainPanel);
				}
				else if (e.getStateChange() == ItemEvent.DESELECTED) {
					mainPanel.setVisible(false);
				}
			}
			
			else if (e.getSource() == statistics) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					mainPanel = new OrderStatistics().buildPanel();
					frame.getContentPane().add(mainPanel);
					/*
					JPanel choicePanel, contentPanel;
					JRadioButton[] radioButtonArray;
					ButtonGroup group;
					
					mainPanel = new JPanel();
					mainPanel.setBounds(200, 0, 1000, 800);
					mainPanel.setLayout(new GridLayout(2, 1));
					choicePanel = new JPanel();
					contentPanel = new JPanel();
					//contentPanel.add(new JButton("test"));
					choicePanel.setLayout(new GridLayout(2, 3));
					
					radioButtonArray = new JRadioButton[6];
					radioButtonArray[0] = new JRadioButton("按商家统计某段时间的订单数和总金额");
					radioButtonArray[1] = new JRadioButton("查询某段时间销量排名前十的商家");
					radioButtonArray[2] = new JRadioButton("查询某段时间下单金额前十的用户");
					radioButtonArray[3] = new JRadioButton("统计每个月的订单数量和金额");
					radioButtonArray[4] = new JRadioButton("查询24小时的订单数量分布情况");
					radioButtonArray[5] = new JRadioButton("查询库中所有订单");
					
					group = new ButtonGroup();
					for (int i = 0; i < 6; i++) {
						group.add(radioButtonArray[i]);
						radioButtonArray[i].addItemListener(new ItemListener() {

							@Override
							public void itemStateChanged(ItemEvent e) {
								if (e.getSource() == radioButtonArray[0]) {
									//JButton testButton = new JButton("test");
									if (e.getStateChange() == ItemEvent.SELECTED) {
										//radioButtonArray[0].setText("selected");
										//testButton.setVisible(true);
										//contentPanel.add(new JButton("ts"));
										JTable func0Table = null;
										JScrollPane scrollPane = null;
										StatisticsFunction0 func0 = new StatisticsFunction0("2019-01-01", "2029-01-01");
										try {
											func0Table = func0.getTableForFunction0();
											scrollPane = new JScrollPane(func0Table);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										if (func0Table == null)
											JOptionPane.showMessageDialog(null, "Cannot get data! Please check again.");
										else
											contentPanel.add(scrollPane);
										
										contentPanel.setVisible(true);
										mainPanel.add(contentPanel);
									}
									else if (e.getStateChange() == ItemEvent.DESELECTED) {
										//radioButtonArray[0].setText("deselected");
										contentPanel.removeAll();
										//contentPanel.removeAll();
										//testButton.setVisible(false);
									}
								}
								
								else if (e.getSource() == radioButtonArray[1]) {
									if (e.getStateChange() == ItemEvent.SELECTED) {
										contentPanel.add(new JLabel("b"));
										mainPanel.add(contentPanel);
									}
									else if (e.getStateChange() == ItemEvent.DESELECTED) {
										contentPanel.removeAll();
									}
								}
								
								else if (e.getSource() == radioButtonArray[2]) {
									
								}
								
								else if (e.getSource() == radioButtonArray[3]) {
									
								}
								
								else if (e.getSource() == radioButtonArray[4]) {
									
								}
								
								else if (e.getSource() == radioButtonArray[5]) {
									
								}
								
							}
							
						});
					}
					
					for (int i = 0; i < 6; i++)
						choicePanel.add(radioButtonArray[i]);
					
					mainPanel.add(choicePanel);
					
					frame.getContentPane().add(mainPanel);
					*/
					
					/*
					JPanel choicePanel, contentPanel;
					JButton[] buttonArray;
					
					mainPanel = new JPanel();
					mainPanel.setBounds(200, 0, 1000, 800);
					mainPanel.setLayout(new GridLayout(2, 1));
					choicePanel = new JPanel();
					//choicePanel.setLayout(new GridLayout(2, 3));
					contentPanel = new JPanel();
					
					buttonArray = new JButton[6];
					buttonArray[0] = new JButton("按商家统计某段时间的订单数和总金额");
					buttonArray[1] = new JButton("查询某段时间销量排名前十的商家");
					buttonArray[2] = new JButton("查询某段时间下单金额前十的用户");
					buttonArray[3] = new JButton("统计每个月的订单数量和金额");
					buttonArray[4] = new JButton("查询24小时的订单数量分布情况");
					buttonArray[5] = new JButton("查询库中所有订单");
					
					for (int i = 0; i < 6; i++)
						buttonArray[i].addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getSource() == buttonArray[0]) {
									String startDate = JOptionPane.showInputDialog(null, "Start Date (yyyy-mm-dd): ");
									String endDate = JOptionPane.showInputDialog(null, "End Date (yyyy-mm-dd): ");
									JTable func0Table = null;
									JScrollPane scrollPane = null;
									StatisticsFunction0 func0 = new StatisticsFunction0(startDate, endDate);
									try {
										func0Table = func0.getTableForFunction0();
										scrollPane = new JScrollPane(func0Table);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									if (func0Table == null)
										JOptionPane.showMessageDialog(null, "Cannot get data! Please check again.");
									else {
										System.out.print("tag");
										contentPanel.add(scrollPane);
									}
									
									mainPanel.add(contentPanel);
									mainPanel.repaint();
									
									
								}
								
								else if (e.getSource() == buttonArray[1]) {
									
								}
								
								else if (e.getSource() == buttonArray[2]) {
									
								}
								
								else if (e.getSource() == buttonArray[3]) {
									
								}
								
								else if (e.getSource() == buttonArray[4]) {
									
								}
								
								else if (e.getSource() == buttonArray[5]) {
									
								}
								
							}
							
						});
					
					
					for (int i = 0; i < 6; i++)
						choicePanel.add(buttonArray[i]);
					
					mainPanel.add(choicePanel);
					
					frame.getContentPane().add(mainPanel);
					
					*/
					
					
				}
				else if (e.getStateChange() == ItemEvent.DESELECTED) {
					mainPanel.setVisible(false);
				}
			}
			
			
			
			
			
		}
		
	}
	
	
	
	class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(1);
		}
		
	}


	
	
	
}
