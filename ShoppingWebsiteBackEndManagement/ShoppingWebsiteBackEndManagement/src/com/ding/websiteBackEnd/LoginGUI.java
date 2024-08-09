package com.ding.websiteBackEnd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.ouyang.storeSeller.UI;

public class LoginGUI implements ActionListener {
	public static final int LENGTH_OF_USERNAME = 15;
	public static final int LENGTH_OF_MAX_PASSWORD = 15;
	private int identity;
	private JFrame frame;
	private JPanel usernamePanel, passwordPanel, continuePanel;
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameText;
	private JPasswordField passwordText;
	private JButton continueButton;

	public LoginGUI(int identity) {
		this.identity = identity;
	}

	public void build() {
		if (identity == 1)  // Administrator
			frame = new JFrame("Administrator Login");
		else                // Store Seller
			frame = new JFrame("Store Seller Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 250,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 100, 500, 200);
		frame.setLayout(new GridLayout(3, 1));
		Container content = frame.getContentPane();
		
		
		usernameLabel = new JLabel("Userame: ", JLabel.RIGHT);
		usernameText = new JTextField(LENGTH_OF_USERNAME);
		usernamePanel = new JPanel();
		usernamePanel.setLayout(new FlowLayout());
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameText);
		
		passwordLabel = new JLabel("Password: ", JLabel.CENTER);
		passwordText = new JPasswordField(LENGTH_OF_MAX_PASSWORD);
		passwordPanel = new JPanel();
		passwordPanel.setLayout(new FlowLayout());
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordText);
		
		continueButton = new JButton("Continue");
		continueButton.addActionListener(this);
		continuePanel = new JPanel();
		continuePanel.add(continueButton);
		
		content.add(usernamePanel);
		content.add(passwordPanel);
		content.add(continuePanel);
		frame.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		com.ding.utils.LoginJudgement judgement = new com.ding.utils.LoginJudgement(usernameText.getText(), String.valueOf(passwordText.getPassword()), this.identity);
		try {
			if (judgement.judge()) {  // Success
				//JOptionPane.showConfirmDialog(null, "Correct", "title", JOptionPane.YES_NO_OPTION);
				if (identity == 1) {
					frame.dispose();
					new com.ding.administrator.AdministratorGUI(usernameText.getText()).build();
				}
				else {
					frame.dispose();
					new UI(usernameText.getText());
				}
			}
			else {  // Failed
				JOptionPane.showConfirmDialog(null, "Permission denied. Please check your username or password again!");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
	}

}
