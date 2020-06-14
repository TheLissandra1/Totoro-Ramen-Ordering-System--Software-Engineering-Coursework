/**
 * @Title:        Interface.java
 * @Description:  This file is mainly used to set basic information for all interfaces.
 *                The size, location, as well as backgrounds are set in this file.
 * @author:       Xinbei Li, Rui Wang
 * @version:      1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Interface extends JFrame  {
	// JFrame frame = new JFrame();
	ImageIcon background;
	JPanel imagePanel;
	Color color = new Color(245,255,255);

	public Interface(){
		super("Welcome to TOTORO RAMEN!-------------------Don't worry. Everything will be fine.");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1300,800);
		this.setVisible(true);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		// this.setResizable(false);

	}
	
	public void showFrame(int num) {
		
		if(num==1)//identity choose
		    background = new ImageIcon("background1.png");
		else if(num==2)//
			background = new ImageIcon("background2.png");
		else if(num==3)
			background = new ImageIcon("background3.png");
		else if(num==4)
			background = new ImageIcon("background4.png");
		else if(num==5)
			background = new ImageIcon("backgroundOrder.png");
		else if(num==6){
			background = new ImageIcon("backgroundModify.png");
		}
		else
			background = new ImageIcon("background5.png");
		
	
		//add the background into a label
		JLabel label = new JLabel(background);
		//set the size of label the same as the frame's size, make label fill with the frame
		label.setSize(1300,800);
		imagePanel = (JPanel) this.getContentPane();
		//use setOpaque method to set the panel transparent
		imagePanel.setOpaque(false);
		this.getLayeredPane().setLayout(null);
		//place the label into the lowest level of the frame
		this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
	}
	
  	public void visibleFalse() {
	  	this.setVisible(false);
  	}

}
