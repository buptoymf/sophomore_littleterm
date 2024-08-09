package com.ding.administrator.StoreManagement;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.ding.utils.InsertNewStoreToDB;
import com.ding.utils.UpdateStoreToDB;

public class UpdateStore {
	private JFrame frame;
	private JLabel nameLabel, typeLabel, hotlineLabel;
	private JTextField nameText, typeText, hotlineText;
	private JButton continueButton;
	private JPanel[] panel;
	private String storeNo, newName, newType, newHotline;
	
	public UpdateStore(String storeNo) {
		this.storeNo = storeNo;
	}
	
	public void build() {
		frame = new JFrame("Store Modification Pane");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 200,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 150, 400, 300);
		Container content = frame.getContentPane();
		frame.setLayout(new GridLayout(4, 1));
		
		nameLabel = new JLabel("New name: ");
		typeLabel = new JLabel("New type: ");
		hotlineLabel = new JLabel("New hotline: ");
		
		nameText = new JTextField(20);
		typeText = new JTextField(20);
		hotlineText = new JTextField(20);
		
		continueButton = new JButton("continue");
		continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new UpdateStoreToDB(storeNo, nameText.getText(), typeText.getText(), hotlineText.getText()).update();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Updation completed.");
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
