package com.ding.administrator.OrderStatistics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class OrderStatistics {
	private JPanel panel, choicePanel, contentPanel;
	private JButton[] buttonArray;
	private ButtonGroup group;
	
	
	public JPanel buildPanel() {
		panel = new JPanel();
		panel.setBounds(200, 0, 1000, 800);
		choicePanel = new JPanel();
		contentPanel = new JPanel();
		choicePanel.setLayout(new GridLayout(2, 3));
		
		buttonArray = new JButton[6];
		buttonArray[0] = new JButton("按商家统计某段时间的订单数和总金额");
		buttonArray[1] = new JButton("查询某段时间销量排名前十的商家");
		buttonArray[2] = new JButton("查询某段时间下单金额前十的用户");
		buttonArray[3] = new JButton("统计每个月的订单数量和金额");
		buttonArray[4] = new JButton("查询24小时的订单数量分布情况");
		buttonArray[5] = new JButton("查询库中所有订单");
		
		group = new ButtonGroup();
		for (int i = 0; i < 6; i++) {
			group.add(buttonArray[i]);
			buttonArray[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (e.getSource() == buttonArray[0]) {
						String startDate = JOptionPane.showInputDialog(null, "Start Date (yyyy-mm-dd): ");
						String endDate = JOptionPane.showInputDialog(null, "End Date (yyyy-mm-dd): ");
						StatisticsFunction0 func0 = new StatisticsFunction0(startDate, endDate);
						func0.build();
					}
					
					else if (e.getSource() == buttonArray[1]) {
						String startDate = JOptionPane.showInputDialog(null, "Start Date (yyyy-mm-dd): ");
						String endDate = JOptionPane.showInputDialog(null, "End Date (yyyy-mm-dd): ");
						StatisticsFunction1 func1 = new StatisticsFunction1(startDate, endDate);
						func1.build();
					}
					
					else if (e.getSource() == buttonArray[2]) {
						String startDate = JOptionPane.showInputDialog(null, "Start Date (yyyy-mm-dd): ");
						String endDate = JOptionPane.showInputDialog(null, "End Date (yyyy-mm-dd): ");
						StatisticsFunction2 func2 = new StatisticsFunction2(startDate, endDate);
						func2.build();
					}
					
					else if (e.getSource() == buttonArray[3]) {
						String startDate = JOptionPane.showInputDialog(null, "Start Month (yyyy-mm): ");
						String endDate = JOptionPane.showInputDialog(null, "End Month (yyyy-mm): ");
						StatisticsFunction3 func3 = new StatisticsFunction3(startDate, endDate);
						func3.build();
					}
					
					else if (e.getSource() == buttonArray[4]) {
						String startDate = JOptionPane.showInputDialog(null, "Start Date (yyyy-mm-dd): ");
						String endDate = JOptionPane.showInputDialog(null, "End Date (yyyy-mm-dd): ");
						StatisticsFunction4 func4 = new StatisticsFunction4(startDate, endDate);
						func4.build();
						
					}
					
					else if (e.getSource() == buttonArray[5]) {
						StatisticsFunction5 func5 = new StatisticsFunction5();
						func5.build();
					}
				}
				
			});
		}
		
		for (int i = 0; i < 6; i++)
			choicePanel.add(buttonArray[i]);
		
		panel.add(choicePanel);
		
		return panel;
	}
	
	/*
	class ChoiceListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == radioButtonArray[0]) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					contentPanel.add(new JLabel("k"));
					panel.add(contentPanel);
				}
				else if (e.getStateChange() == ItemEvent.DESELECTED) {
					contentPanel.setVisible(false);
				}
			}
			
			else if (e.getSource() == radioButtonArray[1]) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					contentPanel.add(new JLabel("b"));
					panel.add(contentPanel);
				}
				else if (e.getStateChange() == ItemEvent.DESELECTED) {
					contentPanel.setVisible(false);
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
		
	}
	*/
	
}
