package com.ding.administrator.CustomerManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.ding.utils.UpdateCustomerToDB;

public class UpdateCustomer {
	private JFrame frame;
	private JLabel previousLabel, currentLabel;
	private JTextField previousText, currentText;
	private JPanel[] panel;
	private JButton continueButton;
	
	
	public void build() {
		frame = new JFrame("Customer Update Pane");
		frame.setLayout(new GridLayout(3, 1));
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 200,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 100, 400, 200);
		Container content = frame.getContentPane();
		
		previousLabel = new JLabel("Previous username: ");
		previousText = new JTextField(20);
		
		currentLabel = new JLabel("Current username: ");
		currentText = new JTextField(20);
		
		continueButton = new JButton("continue");
		continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateCustomerToDB updateClass = new UpdateCustomerToDB(previousText.getText(), currentText.getText());
				try {
					if (updateClass.check(previousText.getText()) && !updateClass.check(currentText.getText())) {
						updateClass.update();
						JOptionPane.showMessageDialog(null, "Updation completed.");
						frame.setVisible(false);
					}
					else
						JOptionPane.showMessageDialog(null, "Updation Failed. Either previous username doesn't exist or current username is invalid.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		panel = new JPanel[3];
		for (int i = 0; i < 3; i++)
			panel[i] = new JPanel();
		
		panel[0].add(previousLabel);
		panel[0].add(previousText);
		
		panel[1].add(currentLabel);
		panel[1].add(currentText);
		
		panel[2].add(continueButton);
		
		for (int i = 0; i < 3; i++)
			content.add(panel[i]);
		
		
		frame.setVisible(true);
	}
}
