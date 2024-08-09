package com.ding.administrator.CategoryManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CategoryManagement {

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
				Object[] selectionValues = new Object[] {"First", "Second", "Third"};
				Object selectedType = JOptionPane.showInputDialog(null, "Please choose one category level to insert.", "Category Insertion Pane", JOptionPane.PLAIN_MESSAGE, null, selectionValues, selectionValues[0]);
				try {
					new InsertCategory(selectedType).build();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if (e.getSource() == buttonArray[1]) {
				Object[] selectionValues = new Object[] {"First", "Second", "Third"};
				Object selectedType = JOptionPane.showInputDialog(null, "Please choose one category level to delete.", "Category Deletion Pane", JOptionPane.PLAIN_MESSAGE, null, selectionValues, selectionValues[0]);
				try {
					new DeleteCategory(selectedType).build();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if (e.getSource() == buttonArray[2]) {
				Object[] selectionValues = new Object[] {"First", "Second", "Third"};
				Object selectedType = JOptionPane.showInputDialog(null, "Please choose one category level to update.", "Category Deletion Pane", JOptionPane.PLAIN_MESSAGE, null, selectionValues, selectionValues[0]);
				try {
					new UpdateCategory(selectedType).build();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if (e.getSource() == buttonArray[3]) {
				try {
					new SearchCategory().build();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
}
