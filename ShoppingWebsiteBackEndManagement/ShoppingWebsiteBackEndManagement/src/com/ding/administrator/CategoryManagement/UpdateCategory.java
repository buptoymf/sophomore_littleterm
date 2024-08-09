package com.ding.administrator.CategoryManagement;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import com.ding.utils.RetrievalCategory;
import com.ding.utils.UpdateCategoryToDB;

public class UpdateCategory {
	private String type;
	private JFrame frame;
	private JPanel[] panel;
	private JLabel[] label;
	private JComboBox<String> catg_I_Box, catg_II_Box, catg_III_Box;
	private String[] catg_I;
	private String[] catg_II;
	private String[] catg_III;
	private String catg_I_Selected, catg_II_Selected;
	private JTextField catgText;
	private JButton continueButton;
	
	
	public UpdateCategory(Object selectedType) {
		this.type = selectedType.toString();
	}

	public void build() throws Exception {
		frame = new JFrame("Category Updating Pane");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 150,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 100, 300, 200);
		frame.setLayout(new GridLayout(2, 1));
		Container content = frame.getContentPane();
		
		RetrievalCategory cat1 = new RetrievalCategory();
		catg_I = cat1.getCategoryI();
		catg_I_Box = new JComboBox<> (catg_I);
		catg_II_Box = new JComboBox<> ();
		catg_III_Box = new JComboBox<> ();
		
		continueButton = new JButton("continue");
		continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch(type) {
					case "First":
						UpdateCategoryToDB updating1 = new UpdateCategoryToDB(catg_I_Box.getSelectedItem().toString(), catgText.getText());
					try {
						if (!updating1.check())
							JOptionPane.showMessageDialog(null, "Category " + catgText.getText() + " already exists!");
						else {
							updating1.update();
							JOptionPane.showMessageDialog(null, "Updating completed.");
							frame.setVisible(false);
						}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
						break;
					case "Second":
						UpdateCategoryToDB updating2 = new UpdateCategoryToDB(catg_I_Box.getSelectedItem().toString(), catg_II_Box.getSelectedItem().toString(), catg_I_Box.getSelectedItem().toString(), catgText.getText());
						try {
							if (!updating2.check())
								JOptionPane.showMessageDialog(null, "Category " + catgText.getText() + " already exists!");
							else {
								updating2.update();
								JOptionPane.showMessageDialog(null, "Updating completed.");
								frame.setVisible(false);
							}
						} catch (HeadlessException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case "Third":
						UpdateCategoryToDB updating3 = new UpdateCategoryToDB(catg_I_Box.getSelectedItem().toString(), catg_II_Box.getSelectedItem().toString(), catg_III_Box.getSelectedItem().toString(), catg_I_Box.getSelectedItem().toString(), catg_II_Box.getSelectedItem().toString(), catgText.getText());
						try {
							if (!updating3.check())
								JOptionPane.showMessageDialog(null, "Category " + catgText.getText() + " already exists!");
							else {
								updating3.update();
								JOptionPane.showMessageDialog(null, "Updating completed.");
								frame.setVisible(false);
							}
						} catch (HeadlessException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					default:
						break;
				}
				
				
				
				
			}
			
		});
		
		switch (this.type) {
			case "First":
				panel = new JPanel[3];
				for (int i = 0; i < 3; i++)
					panel[i] = new JPanel();
				
				label = new JLabel[2];
				label[0] = new JLabel("CATG_I: ");
				label[1] = new JLabel("New CATG_I: ");
				
				catgText = new JTextField(10);
				
				panel[0].add(label[0]);
				panel[0].add(catg_I_Box);
				
				panel[1].add(label[1]);
				panel[1].add(catgText);
				
				panel[2].add(continueButton);
				
				frame.setLayout(new GridLayout(3, 1));
				
				for (int i = 0; i <= 2; i++)
					content.add(panel[i]);
				
				break;
			case "Second":
				panel = new JPanel[4];
				for (int i = 0; i < 4; i++)
					panel[i] = new JPanel();
				
				label = new JLabel[3];
				label[0] = new JLabel("CATG_I: ");
				label[1] = new JLabel("CATG_II: ");
				label[2] = new JLabel("New CATG_II: ");
				
				catg_I_Box.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							if (catg_II_Box.getItemCount() != 0) {
								catg_II_Box.removeAllItems();
							}
							catg_I_Selected = e.getItem().toString();
							try {
								catg_II = cat1.getCategoryII(catg_I_Selected);
								for (int i = 0 ; i < catg_II.length; i++)
									catg_II_Box.addItem(catg_II[i]);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						
					}
				});
				
				catgText = new JTextField(10);
				
				panel[0].add(label[0]);
				panel[0].add(catg_I_Box);
				
				panel[1].add(label[1]);
				panel[1].add(catg_II_Box);
				
				panel[2].add(label[2]);
				panel[2].add(catgText);
				
				panel[3].add(continueButton);
				
				frame.setLayout(new GridLayout(4, 1));
				
				for (int i = 0; i < 4; i++)
					content.add(panel[i]);
				
				break;
			case "Third":
				panel = new JPanel[5];
				for (int i = 0; i < 5; i++)
					panel[i] = new JPanel();
				
				label = new JLabel[4];
				label[0] = new JLabel("CATG_I: ");
				label[1] = new JLabel("CATG_II: ");
				label[2] = new JLabel("CATG_III: ");
				label[3] = new JLabel("New CATG_III: ");
				
				catg_I_Box.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							if (catg_II_Box.getItemCount() != 0) {
								catg_II_Box.removeAllItems();
							}
							catg_I_Selected = e.getItem().toString();
							try {
								catg_II = cat1.getCategoryII(catg_I_Selected);
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
								catg_III = cat1.getCategoryIII(catg_II_Selected);
								for (int i = 0 ; i < catg_III.length; i++)
									catg_III_Box.addItem(catg_III[i]);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				
				catgText = new JTextField(10);
				
				panel[0].add(label[0]);
				panel[0].add(catg_I_Box);
				
				panel[1].add(label[1]);
				panel[1].add(catg_II_Box);
				
				panel[2].add(label[2]);
				panel[2].add(catg_III_Box);
				
				panel[3].add(label[3]);
				panel[3].add(catgText);
				
				panel[4].add(continueButton);
				
				frame.setLayout(new GridLayout(5, 1));
				
				for (int i = 0; i < 5; i++)
					content.add(panel[i]);
				
				
				break;
			default:
				break;
		}
		
		
		
		
		frame.setVisible(true);
	}

}
