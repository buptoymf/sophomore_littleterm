package com.ding.administrator.CategoryManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.ding.utils.RetrievalCategory;

public class SearchCategory {
	private JFrame frame;
	private JPanel[] panel;
	private JComboBox<String> catg_I_Box, catg_II_Box, catg_III_Box;
	private String[] catg_I;
	private String[] catg_II;
	private String[] catg_III;
	private String catg_I_Selected;
	private String catg_II_Selected;
	private String catg_III_Selected;
	
	
	public void build() throws Exception {
		frame = new JFrame("Category Searching Pane");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 150,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 100, 300, 200);
		frame.setLayout(new GridLayout(3, 1));
		Container content = frame.getContentPane();
		
		RetrievalCategory cat = new RetrievalCategory();
		catg_I = cat.getCategoryI();
		catg_I_Box = new JComboBox<> (catg_I);
		catg_II_Box = new JComboBox<> ();
		catg_III_Box = new JComboBox<> ();
		
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
		
		
		panel = new JPanel[3];
		for (int i = 0; i < 3; i++)
			panel[i] = new JPanel();
		
		
		panel[0].add(catg_I_Box);
		panel[1].add(catg_II_Box);
		panel[2].add(catg_III_Box);
		
		
		for (int i = 0; i < 3; i++)
			content.add(panel[i]);
		
		
		frame.setVisible(true);
	}
	
	
}
