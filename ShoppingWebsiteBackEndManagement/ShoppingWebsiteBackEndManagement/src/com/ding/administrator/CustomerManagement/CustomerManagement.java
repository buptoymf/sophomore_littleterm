package com.ding.administrator.CustomerManagement;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

import com.ding.utils.DeleteCustomerFromDB;
import com.ding.utils.InsertNewCustomerToDB;

public class CustomerManagement {
	JPanel panel, introPanel, choicePanel;
	JLabel introLabel;
	JButton[] buttonArray;
	
	
	
	public JPanel buildPanel() {
		panel = new JPanel();
		choicePanel = new JPanel();
		choicePanel.setLayout(new GridLayout(2, 2));
		panel.setBounds(200, 0, 1000, 800);
		buttonArray = new JButton[4];
		buttonArray[0] = new JButton("Insert");
		buttonArray[1] = new JButton("Delete");
		buttonArray[2] = new JButton("Modify");
		buttonArray[3] = new JButton("Search");
		
		introPanel = new JPanel();
		introLabel = new JLabel("Select an option by clicking each of the button.");
		introPanel.add(introLabel);
		panel.add(introPanel);
		
		for (int i = 0; i < 4; i++) {
			buttonArray[i].addActionListener(new OperationListener());
			choicePanel.add(buttonArray[i]);
		}
		
		panel.add(choicePanel);
		
		
		return panel;
	}
	
	class OperationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttonArray[0]) {
				String newUsername = JOptionPane.showInputDialog(null, "Please input the customer username to insert");
				InsertNewCustomerToDB insertion = new InsertNewCustomerToDB(newUsername);
				try {
					if (insertion.check() == false || newUsername == null)
						JOptionPane.showMessageDialog(null, "Invalid username! Either the username already exists or not valid.");
					else {
						insertion.insert();
						JOptionPane.showMessageDialog(null, "Insertion completed.");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			else if (e.getSource() == buttonArray[1]) {
				String usernameToDelete = JOptionPane.showInputDialog(null, "Please input the customer username to delete.");
				try {
					if (new DeleteCustomerFromDB(usernameToDelete).delete())
						JOptionPane.showMessageDialog(null, "Deletion completed.");
					else
						JOptionPane.showMessageDialog(null, "customer username " + usernameToDelete + " not exists.");
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if (e.getSource() == buttonArray[2]) {
				new UpdateCustomer().build();
			}
			
			else if (e.getSource() == buttonArray[3]) {  // unimplemented
				new SearchCustomer().build();
			}			
			
		}
		
	}
	
	
	
}
