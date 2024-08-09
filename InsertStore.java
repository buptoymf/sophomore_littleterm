package com.ding.administrator.StoreManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.ding.utils.InsertNewStoreToDB;

import java.util.Date;
import java.text.SimpleDateFormat;

public class InsertStore {
	private JFrame frame;
	private JLabel nameLabel, typeLabel, hotlineLabel;
	private JTextField nameText, typeText, hotlineText;
	private JButton continueButton;
	private JPanel[] panel;
	private String newName, newType, newHotline, newFoundDate;
	
	
	public void build() {
		frame = new JFrame("Store Insertion Pane");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 200,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 150, 400, 300);
		Container content = frame.getContentPane();
		frame.setLayout(new GridLayout(4, 1));
		
		nameLabel = new JLabel("Name: ");
		typeLabel = new JLabel("Type: ");
		hotlineLabel = new JLabel("Hotline: ");
		
		nameText = new JTextField(20);
		typeText = new JTextField(20);
		hotlineText = new JTextField(20);
		
		continueButton = new JButton("continue");
		continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newName = nameText.getText();
				newType = typeText.getText();
				newHotline = hotlineText.getText();
				newFoundDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				new InsertNewStoreToDB(newName, newType, newHotline, newFoundDate).insert();
				JOptionPane.showMessageDialog(null, "Insertion completed.");
				frame.setVisible(false);
			}
			
		});
		
		panel = new JPanel[4];
		for (int i = 0; i < 4; i++)
			panel[i] = new JPanel();
		
		panel[0].add(nameLabel);
		panel[0].add(nameText);
		
		panel[1].add(typeLabel);
		panel[1].add(typeText);
		
		panel[2].add(hotlineLabel);
		panel[2].add(hotlineText);
		
		panel[3].add(continueButton);
		
		
		for (int i = 0; i < 4; i++)
			content.add(panel[i]);
		
		frame.setVisible(true);
	}
}
