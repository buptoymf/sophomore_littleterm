package com.ding.administrator.ProductManagementForAdmin;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import com.ding.utils.DataBaseConnection;
import com.ding.utils.UpdateProductToDB;

public class UpdateProduct {
	private String productNo;
	private String[] catg_III;
	private JFrame frame;
	private JLabel introLabel, nameLabel, descrLabel, catg_III_Label;
	private JTextField nameText, descrText;
	private String oldName, oldDescription;
	private JComboBox<String> catg_III_Box;
	private JButton continueButton;
	private JPanel[] panel;
	UpdateProductToDB updateClass;
	
	private Connection conn;
    private Statement stat;
	
	public void setCatg_III() throws SQLException {
		conn = DataBaseConnection.getConnection();
		stat = conn.createStatement();
		ResultSet result;
		String sql = "SELECT category_third_name from category_third";
		
		result = stat.executeQuery(sql);
		result.last();
		int count = result.getRow();
		catg_III = new String[count];
		result.first();
		int k = 0;
		while (result.next()) {
			catg_III[k] = result.getString(1);
			k++;
		}
		
		sql = "SELECT name, description FROM product WHERE productNo = '" + this.productNo + "'";
		result = stat.executeQuery(sql);
		result.first();
		this.oldName = result.getString(1);
		this.oldDescription = result.getString(2);
		
	}
	
	
	public void build() {
		productNo = JOptionPane.showInputDialog(null, "Please input the productNo to modify");
		updateClass = new UpdateProductToDB(productNo);
		try {
			if (!updateClass.check())
				JOptionPane.showMessageDialog(null, "Product " + this.productNo + " not exists.");
			else {
				panel = new JPanel[5];
				for (int i = 0; i < 5; i++)
					panel[i] = new JPanel();
				
				frame = new JFrame("Proudct Updating Pane");
				frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2) - 200,
						((Toolkit.getDefaultToolkit().getScreenSize().height)/2) - 150, 400, 300);
				Container content = frame.getContentPane();
				frame.setLayout(new GridLayout(5, 1));
				
				introLabel = new JLabel("Keep the attribute blank if you don't want to change it.");
				nameLabel = new JLabel("New name: ");
				descrLabel = new JLabel("New description: ");
				catg_III_Label = new JLabel("New third category: ");
				
				nameText = new JTextField(20);
				descrText = new JTextField(20);
				
				
				
				this.setCatg_III();
				catg_III_Box = new JComboBox<String> (catg_III);
				
				
				
				panel[0].add(introLabel);
				
				panel[1].add(nameLabel);
				panel[1].add(nameText);
				
				panel[2].add(descrLabel);
				panel[2].add(descrText);
				
				panel[3].add(catg_III_Label);
				panel[3].add(catg_III_Box);
				
				continueButton = new JButton("continue");
				continueButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							String modifiedName = (nameText.getText().equals("")) ? oldName : nameText.getText();
							String modifiedDesc = (descrText.getText().equals("")) ? oldDescription : descrText.getText();
							
							new UpdateProductToDB(productNo, modifiedName, modifiedDesc, catg_III_Box.getSelectedItem().toString()).update();
							JOptionPane.showMessageDialog(null, "Update completed.");
							frame.setVisible(false);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
				});
				panel[4].add(continueButton);
				
				
				
				for (int i = 0; i < 5; i++)
					content.add(panel[i]);
				
				
				frame.setVisible(true);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
