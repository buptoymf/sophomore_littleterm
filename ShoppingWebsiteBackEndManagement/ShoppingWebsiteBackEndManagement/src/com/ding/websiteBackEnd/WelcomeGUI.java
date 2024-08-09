package com.ding.websiteBackEnd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomeGUI implements ActionListener {
	
	private JFrame frame;
	private JLabel label;
	private JPanel introPanel, choicePanel, continuePanel;
	private JRadioButton admin, seller;
	private ButtonGroup group;
	private JButton continueButton;
	
	public void build() {
		frame = new JFrame();
		frame.setTitle("E-commerce Website Back End Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 250,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 100, 500, 200);
		Container content = frame.getContentPane();
		
		/*
		 * Using BorderLayout to put introPanel in the north.
		 */
		introPanel = new JPanel();
		label = new JLabel("Welcome to the management system! You are: ");
		label.setFont(new Font("Bold", Font.BOLD, 15)); // Set the font of query text.
		introPanel.add(label);
		content.add(BorderLayout.NORTH, introPanel);
		
		/*
		 * Using BorderLayout to put choicePanel in the center.
		 */
		choicePanel = new JPanel();
		group = new ButtonGroup();
		
		admin = new JRadioButton("Administrator");
		seller = new JRadioButton("Store Seller");
		
		group.add(admin);
		group.add(seller);
		
		choicePanel.add(admin);
		choicePanel.add(seller);
		
		content.add(BorderLayout.CENTER, choicePanel);
		
		/*
		 * Using BorderLayout to put continuePanel in the south.
		 */
		continuePanel = new JPanel();
		continueButton = new JButton("Continue");
		
		continuePanel.add(continueButton);
		continueButton.addActionListener(this);
		
		content.add(BorderLayout.SOUTH, continuePanel);
		
		
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int identity = 0;  // 1 means administrator; 2 means seller.
		
		if (admin.isSelected())
			identity = 1;
		else if (seller.isSelected())
			identity = 2;
		
		frame.setVisible(false); // Close the current GUI.
		LoginGUI login = new LoginGUI(identity);
		login.build();
		
	}
	

}
