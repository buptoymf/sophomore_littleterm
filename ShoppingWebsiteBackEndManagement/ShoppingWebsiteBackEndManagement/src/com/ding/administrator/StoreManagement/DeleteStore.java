package com.ding.administrator.StoreManagement;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import com.ding.utils.DeleteStoreFromDB;
import com.ding.utils.RetrievalStore;

public class DeleteStore {
	private JFrame frame;
	private JPanel tablePanel, inputPanel, continuePanel;
	private JTable storeTable;
	private JLabel label;
	private JTextField text;
	private JButton continueButton;
	private JScrollPane scrollPane;

	
	public void build() {
		frame = new JFrame("Store Deletion Pane");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 300,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 150, 600, 300);
		Container content = frame.getContentPane();
		frame.setLayout(new GridLayout(3, 1));
		
		tablePanel = new JPanel();
		
		try {
			//storeTable = new RetrievalStore().getStoreTable();
			storeTable = new RetrievalStore().getStoreTableWithoutModel();
			// 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
	        scrollPane = new JScrollPane(storeTable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tablePanel = new JPanel();
		tablePanel.add(scrollPane);
		
		label = new JLabel("Please input the storeNo to delete: ");
		text = new JTextField(10);
		
		inputPanel = new JPanel();
		inputPanel.add(label);
		inputPanel.add(text);
		
		continueButton = new JButton("continue");
		continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteStoreFromDB deletion = new DeleteStoreFromDB(text.getText());
				try {
					if (!deletion.check())
						JOptionPane.showMessageDialog(null, "storeNo " + text.getText() + " not found!");
					else {
						deletion.delete();
						JOptionPane.showMessageDialog(null, "Deletion completed.");
						frame.setVisible(false);
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
			
		});
		
		continuePanel = new JPanel();
		continuePanel.add(continueButton);
		
		//frame.setContentPane(tablePanel);
		content.add(tablePanel);
		content.add(inputPanel);
		content.add(continuePanel);
		
		
		frame.setVisible(true);
	}
	
}
