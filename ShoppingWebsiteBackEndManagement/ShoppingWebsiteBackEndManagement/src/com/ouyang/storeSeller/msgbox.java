package com.ouyang.storeSeller;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class msgbox {

	JPanel[] jp = new JPanel[2];
	JLabel[] jl = new JLabel[1];
    JButton JOK;
    
    
	public msgbox(String text)
	{
		JFrame msg = new JFrame();

		jp[0] = new JPanel();
		jp[1] = new JPanel();
		
		jl[0] = new JLabel(text,JLabel.CENTER);
		
		jl[0].setFont(new Font("Serif", Font.PLAIN, 25));
		
		JOK = new JButton("OK");
		JOK.setFont(new Font("Serif", Font.PLAIN, 25));

		JOK.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				msg.dispose();
				
			}
		});
		
		jp[0].add(jl[0]);
		jp[1].add(JOK);
		
		msg.setLayout(new GridLayout(2, 1));
		
		msg.getContentPane().add(jp[0]);
		msg.getContentPane().add(jp[1]);
		
		
		msg.setSize(400, 300);
		msg.pack();
		msg.setLocationRelativeTo(null);
		msg.setTitle("Note");
		msg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		msg.setVisible(true);
		
	}
	
}

