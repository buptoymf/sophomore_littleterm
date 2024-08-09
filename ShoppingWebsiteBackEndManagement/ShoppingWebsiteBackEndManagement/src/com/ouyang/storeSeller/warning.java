package com.ouyang.storeSeller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


/**
* Title : warning.java
* Description: This class displays a warning frame which will pop up if the user chooses a wrong answer in 'multiple attempt' mode.
* Copyright : Copyright (c) 2019┸2019
* @author MingFei OuYang
* @version 1.0
*/


public class warning extends JFrame{
	
	JPanel[] jp = new JPanel[3];
	JLabel[] jl = new JLabel[2];
    JButton JOK;
    JRadioButton level1,level2,level3;
    JFrame myFrame;
    int i;
    
    /**
     *  Constructor of the class
     */
    
	public warning()
	{
		
		
		JFrame warning = new JFrame();
		

	    
		jp[0] = new JPanel();
		jp[1] = new JPanel();
		jp[2] = new JPanel();

		jl[0] = new JLabel("Wrong username or password!",JLabel.CENTER);

		jl[0].setFont(new Font("Serif", Font.PLAIN, 25));

		jl[1] = new JLabel("Try again!",JLabel.CENTER);

		jl[1].setFont(new Font("Serif", Font.PLAIN, 25));

		JOK = new JButton("OK");
		JOK.setFont(new Font("Serif", Font.PLAIN, 25));

		JOK.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				warning.dispose();
			}
		});

		jp[0].add(jl[0]);
		jp[1].add(jl[1]);
		jp[2].add(JOK);

		warning.setLayout(new GridLayout(3, 1));
		
		
		warning.getContentPane().add(jp[0]);
		warning.getContentPane().add(jp[1]);
		warning.getContentPane().add(jp[2]);
		
	warning.pack();
	warning.setSize(400, 300);
	warning.setLocationRelativeTo(null);
	warning.setTitle("警告!");
	warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	warning.setVisible(true);
	
	}
	
}

