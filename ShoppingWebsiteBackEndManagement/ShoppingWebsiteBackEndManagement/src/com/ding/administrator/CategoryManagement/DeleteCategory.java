package com.ding.administrator.CategoryManagement;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import com.ding.utils.DeleteCategoryFromDB;
import com.ding.utils.RetrievalCategory;

public class DeleteCategory {
	private String type;
	private JFrame frame;
	private JComboBox<String> catg_I_Box, catg_II_Box, catg_III_Box;
	private JButton continueButton;
	private String[] catg_I;
	private String[] catg_II;
	private String[] catg_III;
	private String catg_I_Selected;
	private String catg_II_Selected;
	private JLabel introLabel;
	private JPanel introPanel, contentPanel, continuePanel;

	public DeleteCategory(Object selectedType) {
		this.type = selectedType.toString();
	}

	public void build() throws Exception {
		frame = new JFrame("Category Deletion Pane");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 200,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 100, 400, 200);
		frame.setLayout(new GridLayout(3, 1));
		Container content = frame.getContentPane();
		
		introLabel = new JLabel("Please choose the category below.");
		introPanel = new JPanel();
		introPanel.add(introLabel);
		
		contentPanel = new JPanel();
		RetrievalCategory cat = new RetrievalCategory();
		catg_I = cat.getCategoryI();
		catg_I_Box = new JComboBox<> (catg_I);
		catg_II_Box = new JComboBox<> ();
		catg_III_Box = new JComboBox<> ();
		
		switch (this.type) {
			case "First":
				contentPanel.add(catg_I_Box);
				break;
			case "Second":
				contentPanel.setLayout(new GridLayout(2, 1));
				contentPanel.add(catg_I_Box);
				catg_I_Box.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							if (catg_II_Box.getItemCount() != 0) {
								catg_II_Box.removeAllItems();
							}
							catg_I_Selected = e.getItem().toString();
							try {
								catg_II = cat.getCategoryII(catg_I_Selected);
								for (int i = 0 ; i < catg_II.length; i++)
									catg_II_Box.addItem(catg_II[i]);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						
					}
					
				});
				contentPanel.add(catg_II_Box);
				break;
			case "Third":
				contentPanel.setLayout(new GridLayout(3, 1));
				contentPanel.add(catg_I_Box);
				catg_I_Box.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							if (catg_II_Box.getItemCount() != 0) {
								catg_II_Box.removeAllItems();
							}
							catg_I_Selected = e.getItem().toString();
							try {
								catg_II = cat.getCategoryII(catg_I_Selected);
								for (int i = 0 ; i < catg_II.length; i++)
									catg_II_Box.addItem(catg_II[i]);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						
					}
					
				});
				
				catg_II_Box.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						if (e.getStateChange() == ItemEvent.SELECTED) {
							if (catg_III_Box.getItemCount() != 0)
								catg_III_Box.removeAllItems();
							catg_II_Selected = e.getItem().toString();
							try {
								catg_III = cat.getCategoryIII(catg_II_Selected);
								for (int i = 0 ; i < catg_III.length; i++)
									catg_III_Box.addItem(catg_III[i]);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				
				contentPanel.add(catg_II_Box);
				contentPanel.add(catg_III_Box);
				break;
			default:
				break;
		}
		
		
		continueButton = new JButton("continue");
		continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (type) {
					case "First":
						DeleteCategoryFromDB deletion1 = new DeleteCategoryFromDB(catg_I_Box.getSelectedItem().toString());
						try {
							if (!deletion1.check())
								JOptionPane.showMessageDialog(null, "Deletion denied. There all still products belonging to this category.");
							else {
								deletion1.delete();
								JOptionPane.showMessageDialog(null, "Deletion completed.");
								frame.setVisible(false);
							}
						} catch (HeadlessException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
					case "Second":
						DeleteCategoryFromDB deletion2 = new DeleteCategoryFromDB(catg_I_Box.getSelectedItem().toString(), catg_II_Box.getSelectedItem().toString());
						try {
							if (!deletion2.check())
								JOptionPane.showMessageDialog(null, "Deletion denied. There all still products belonging to this category.");
							else {
								deletion2.delete();
								JOptionPane.showMessageDialog(null, "Deletion completed.");
								frame.setVisible(false);
							}
						} catch (HeadlessException | SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						break;
					case "Third":
						DeleteCategoryFromDB deletion3 = new DeleteCategoryFromDB(catg_I_Box.getSelectedItem().toString(), catg_II_Box.getSelectedItem().toString(), catg_III_Box.getSelectedItem().toString());
						try {
							if (!deletion3.check())
								JOptionPane.showMessageDialog(null, "Deletion denied. There all still products belonging to this category.");
							else {
								deletion3.delete();
								JOptionPane.showMessageDialog(null, "Deletion completed.");
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
		
		continuePanel = new JPanel();
		continuePanel.add(continueButton);
		
		
		content.add(introPanel);
		content.add(contentPanel);
		content.add(continuePanel);
		
		frame.setVisible(true);
	}

}
