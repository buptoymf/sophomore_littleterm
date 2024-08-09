package com.ding.administrator.ProductManagementForAdmin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.ding.utils.InsertNewProductToDB;
import com.ding.utils.RetrievalCategory;

public class InsertProduct {
	JFrame frame;
	JLabel[] label;
	JTextField nameText, descrText, priceText;
	JComboBox<String> catg_I_Box;
	JComboBox<String> catg_II_Box;
	JComboBox<String> catg_III_Box;
	JPanel[] panel;
	JButton continueButton;
	String[] catg_I;
	String[] catg_II;
	String[] catg_III;
	String catg_I_Selected;
	String catg_II_Selected;
	String catg_III_Selected;

	public void build() throws Exception {
		frame = new JFrame("Proudct Insertion Pane");
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 300,
				((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 300, 600, 600);
		Container content = frame.getContentPane();
		
		label = new JLabel[6];
		label[0] = new JLabel("Name:            ");
		label[1] = new JLabel("Description:     ");
		label[2] = new JLabel("First category:  ");
		label[3] = new JLabel("Second category: ");
		label[4] = new JLabel("Third categoty:  ");
		label[5] = new JLabel("Price:           ");
		
		panel = new JPanel[7];
		for (int i = 0; i < 7; i++)
			panel[i] = new JPanel();
		
		nameText = new JTextField(20);
		descrText = new JTextField(20);
		priceText = new JTextField(20);
		
		
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
		
		System.out.println(catg_I_Selected);
		
		panel[0].add(label[0]);
		panel[0].add(nameText);
		
		panel[1].add(label[1]);
		panel[1].add(descrText);
		
		panel[2].add(label[2]);
		panel[2].add(catg_I_Box);
		
		panel[3].add(label[3]);
		panel[3].add(catg_II_Box);
		
		panel[4].add(label[4]);
		panel[4].add(catg_III_Box);
		
		panel[5].add(label[5]);
		panel[5].add(priceText);
		
		
		continueButton = new JButton("continue");
		continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					InsertNewProductToDB insertion = new InsertNewProductToDB(nameText.getText(), descrText.getText(), catg_III_Box.getSelectedItem().toString(), priceText.getText());
					insertion.insert();
					JOptionPane.showMessageDialog(null, "Insertion completed.");
					frame.setVisible(false);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
			
		});
		
		panel[6].add(continueButton);
		
		frame.setLayout(new GridLayout(7, 1));
		for (int i = 0; i < 7; i++)
			content.add(panel[i]);
		
		
		frame.setVisible(true);
	}

}
