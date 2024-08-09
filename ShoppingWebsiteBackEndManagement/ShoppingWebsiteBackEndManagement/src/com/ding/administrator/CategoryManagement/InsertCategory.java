package com.ding.administrator.CategoryManagement;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import com.ding.utils.InsertNewCategoryToDB;
import com.ding.utils.RetrievalCategory;

public class InsertCategory {
	private String type;
	private JFrame frame;
	private JTextField catgText;
	private JLabel catg_I_Label, catg_II_Label, catg_III_Label;
	private JComboBox<String> catg_I_Box, catg_II_Box;
	private JButton continueButton;
	private JPanel contentPanel, continuePanel;
	private JPanel catg_I_Panel, catg_II_Panel, catg_III_Panel;
	private String[] catg_I;
	private String[] catg_II;
	private String catg_I_Selected;
	
	public InsertCategory(Object type) {
		this.type = type.toString();
	}
	
	public void build() throws Exception {
		frame = new JFrame("Category Insertion Pane");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 150,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 100, 300, 200);
		frame.setLayout(new GridLayout(2, 1));
		Container content = frame.getContentPane();
		
		contentPanel = new JPanel();
		continuePanel = new JPanel();
		
		continueButton = new JButton("continue");
		continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				switch (type) {
					case "First":
						InsertNewCategoryToDB insertion1 = new InsertNewCategoryToDB(catgText.getText());
						try {
							if (!insertion1.check())
								JOptionPane.showMessageDialog(null, "Category " + catgText.getText() + "already exists.");
							else {
								insertion1.insert();
								JOptionPane.showMessageDialog(null, "Insertion completed.");
								frame.setVisible(false);
							}
						} catch (HeadlessException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case "Second":
						InsertNewCategoryToDB insertion2 = new InsertNewCategoryToDB(catg_I_Box.getSelectedItem().toString(), catgText.getText());
						try {
							if (!insertion2.check())
								JOptionPane.showMessageDialog(null, "Category " + catgText.getText() + "already exists.");
							else {
								insertion2.insert();
								JOptionPane.showMessageDialog(null, "Insertion completed.");
								frame.setVisible(false);
							}
						} catch (HeadlessException | SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						break;
					case "Third":
						InsertNewCategoryToDB insertion3 = new InsertNewCategoryToDB(catg_I_Box.getSelectedItem().toString(), catg_II_Box.getSelectedItem().toString(), catgText.getText());
						try {
							if (!insertion3.check())
								JOptionPane.showMessageDialog(null, "Category " + catgText.getText() + "already exists.");
							else {
								insertion3.insert();
								JOptionPane.showMessageDialog(null, "Insertion completed.");
								frame.setVisible(false);
							}
						} catch (HeadlessException | SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						break;
					default:
						break;
				}
			}
			
		});
		continuePanel.add(continueButton);
		
		switch (this.type) {
			case "First":
				catg_I_Label = new JLabel("CATG_I: ");
				catgText = new JTextField(10);
				contentPanel.add(catg_I_Label);
				contentPanel.add(catgText);
				break;
			case "Second":
				contentPanel.setLayout(new GridLayout(2, 1));
				catg_I_Panel = new JPanel();
				catg_II_Panel = new JPanel();
				catg_I_Label = new JLabel("CATG_I: ");
				catg_II_Label = new JLabel("New CATG_II: ");
				
				RetrievalCategory cat1 = new RetrievalCategory();
				catg_I = cat1.getCategoryI();
				catg_I_Box = new JComboBox<> (catg_I);
				catgText = new JTextField(10);
				
				catg_I_Panel.add(catg_I_Label);
				catg_I_Panel.add(catg_I_Box);
				contentPanel.add(catg_I_Panel);
				
				catg_II_Panel.add(catg_II_Label);
				catg_II_Panel.add(catgText);
				contentPanel.add(catg_II_Panel);
				break;
			case "Third":
				contentPanel.setLayout(new GridLayout(3, 1));
				catg_I_Panel = new JPanel();
				catg_II_Panel = new JPanel();
				catg_III_Panel = new JPanel();
				catg_I_Label = new JLabel("CATG_I: ");
				catg_II_Label = new JLabel("CATG_II: ");
				catg_III_Label = new JLabel("New CATG_III: ");
				
				RetrievalCategory cat2 = new RetrievalCategory();
				catg_I = cat2.getCategoryI();
				catg_I_Box = new JComboBox<> (catg_I);
				catg_II_Box = new JComboBox<> ();
				catg_I_Box.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						if (catg_II_Box.getItemCount() != 0) {
							catg_II_Box.removeAllItems();
						}
						catg_I_Selected = e.getItem().toString();
						try {
							catg_II = cat2.getCategoryII(catg_I_Selected);
							for (int i = 0 ; i < catg_II.length; i++)
								catg_II_Box.addItem(catg_II[i]);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				});
				
				catgText = new JTextField(10);
				catg_I_Panel.add(catg_I_Label);
				catg_I_Panel.add(catg_I_Box);
				contentPanel.add(catg_I_Panel);
				
				catg_II_Panel.add(catg_II_Label);
				catg_II_Panel.add(catg_II_Box);
				contentPanel.add(catg_II_Panel);
				
				catg_III_Panel.add(catg_III_Label);
				catg_III_Panel.add(catgText);
				contentPanel.add(catg_III_Panel);
				break;
			default:
				break;
		}
		
		content.add(contentPanel);
		content.add(continuePanel);
		
		
		frame.setVisible(true);
	}
	
	
	
}
