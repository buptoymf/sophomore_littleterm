package com.ding.administrator.ProductManagementForAdmin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProductManagement {
	JButton[] buttonArray;
	
	
	
	public JPanel buildPanel() {
		JPanel panel = new JPanel();
		JPanel choicePanel = new JPanel();
		JPanel introPanel = new JPanel();
		choicePanel.setLayout(new GridLayout(2, 2));
		
		buttonArray = new JButton[4];
		buttonArray[0] = new JButton("Insert");
		buttonArray[1] = new JButton("Delete");
		buttonArray[2] = new JButton("Modify");
		buttonArray[3] = new JButton("Search");
		
		JLabel introLabel = new JLabel("Select an option by clicking each of the button.");
		introPanel.add(introLabel);
		panel.add(introPanel);
		
		
		
		
		for (int i = 0; i < 4; i++) {
			buttonArray[i].addActionListener(new OperationListener());
			choicePanel.add(buttonArray[i]);
		}
		panel.add(choicePanel);
		
		panel.setBounds(200, 0, 1000, 800);
		
		return panel;
	}
	
	class OperationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttonArray[0]) {
				try {
					new InsertProduct().build();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == buttonArray[1]) {
				new DeleteProduct().build();
			}
			else if (e.getSource() == buttonArray[2]) {
				new UpdateProduct().build();
			}
			else if (e.getSource() == buttonArray[3]) {
				new SearchProduct().build();
			}
			
			
		}
		
	}
	
}
