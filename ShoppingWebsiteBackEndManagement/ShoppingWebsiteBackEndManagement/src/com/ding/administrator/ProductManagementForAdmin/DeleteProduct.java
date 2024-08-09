package com.ding.administrator.ProductManagementForAdmin;

import java.sql.SQLException;

import javax.swing.*;

import com.ding.utils.DeleteProductFromDB;

public class DeleteProduct {
	String productNoToDelete;
	
	public void build() {
		productNoToDelete = JOptionPane.showInputDialog(null, "Please input the productNo to delete.");
		try {
			if (new DeleteProductFromDB(productNoToDelete).delete())
				JOptionPane.showMessageDialog(null, "Deletion completed.");
			else
				JOptionPane.showMessageDialog(null, "productNo " + this.productNoToDelete + " not exists.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
