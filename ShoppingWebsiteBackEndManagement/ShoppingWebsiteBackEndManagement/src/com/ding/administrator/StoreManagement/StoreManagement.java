package com.ding.administrator.StoreManagement;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import com.ding.utils.UpdateStoreToDB;

public class StoreManagement {
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
				new InsertStore().build();
			}
			
			else if (e.getSource() == buttonArray[1]) {
				new DeleteStore().build();
			}
			
			else if (e.getSource() == buttonArray[2]) {
				String storeNo = JOptionPane.showInputDialog(null, "Please input the storeNo to update.");
				UpdateStoreToDB updateDB = new UpdateStoreToDB(storeNo);
				try {
					if (!updateDB.check())
						JOptionPane.showMessageDialog(null, "Invalid input! Either store not exists or storeNo is not valid.");
					else
						new UpdateStore(storeNo).build();
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			else if (e.getSource() == buttonArray[3]) {
				new SearchStore().build();
			}
			
			
			
		}
		
	}
	
	
	
	
	
}
