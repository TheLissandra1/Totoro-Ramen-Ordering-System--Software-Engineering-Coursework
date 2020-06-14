import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
/**
* Title : ConfirmPage.java
* Description: This class aims to execute the relative function of confirm interface.
* @author Xinbei Li
* @version 4.0
*/

public class ConfirmPage extends Interface {
	Font bigFont = new Font("Times New Roman",Font.BOLD,48);
	Font bigFont1 = new Font("Times New Roman",Font.BOLD,28);
	Font bigFont2 = new Font("Times New Roman",Font.BOLD,25);
	JButton b1 = new JButton("Confirm");
	JButton b2 = new JButton("Cancel");
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel empt1 = new JPanel();
	JPanel empt2 = new JPanel();
	JLabel lab = new JLabel("",JLabel.CENTER);
	JLabel lab1 = new JLabel("Please cheeck your order and click the confirm button",JLabel.CENTER);
	private String[] line = new String[40]; //Store strings which read from the file
	
	
	/**
	* Constructor
	* The main constructor, it sets the frame and call helper method
	*/
	public ConfirmPage() {
		super.showFrame(3);
		Dimension preferredSize2 = new Dimension(1300,220);
		panel.setPreferredSize(preferredSize2);	
		panel.setOpaque(false);
		lab.setFont(bigFont);lab1.setFont(bigFont2);
	    panel.add(lab);panel.add(lab1);
		panel.setLayout(new GridLayout(2,1,10,10));
		super.getContentPane().setLayout(new BorderLayout());
		super.getContentPane().add(BorderLayout.NORTH,panel);
			
	//	this.helper();
	}
	
	/**
	* Paramount method of this class
	* A method to keep to the principle that only specified interaction can occur
	* Also, it help to split the code into part
	*/
/*	private void helper() {
		this.createOrder(); 
		this.createButton();
	}
	
*/
	/**
	*  A method to read the order from relative file and print it on the screen 
	*/
	public void createOrder() {	
		OrderRW read = new OrderRW();
		line=read.readFile();
		
		//identify the number of add-ons, add :0 to make the order form clear
		int len1=line[11].length();
		int len2=line[13].length();
		int len3=line[15].length();
		int len4=line[17].length();
		char c1=line[11].charAt(len1-1);
		char c2=line[13].charAt(len2-1);
		char c3=line[15].charAt(len3-1);
		char c4=line[17].charAt(len4-1);
			
		if(c1!='1') 
		   line[11]+=": 0";
		if(c2!='1') 
		   line[13]+=": 0";
		if(c3!='1') 
		   line[15]+=": 0";
		if(c4!='1') 
		   line[17]+=": 0";
			
		//design the order part using JTable
		String[] columnNames = {"fixed dishes", "Add-ons"};
		Object[][] content = {
			    {line[2], line[9]},
			    {line[3], line[11]},
			    {line[4], line[13]},
			    {line[5], line[15]},
			    {line[6], line[17]},
			    {line[7], ""},
			    {line[8], ""},
			    };
		JTable table = new JTable(content, columnNames);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		table.setEnabled(false);
		table.setFont(bigFont2);
		table.setRowHeight(50);
		table.setShowGrid(false);
		//set the background color
		table.setBackground(color);
		
		
		//centralize the whole table
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		
		JScrollPane scrollPane = new JScrollPane();;
		scrollPane.getViewport().setOpaque(false);//set JScrollPane transparent
		scrollPane.setOpaque(false);//set viewport transparent
		scrollPane.setViewportView(table);
        
		table.setFillsViewportHeight(true);
		
		//add the table into container
		//Container container=super.getContentPane();
		Container container=new Container();
		//((JComponent) container).setOpaque(false); 
		container.setLayout(new BorderLayout());
		container.add(table, BorderLayout.CENTER);
		//container.add(table);
		
		//JPanel j = new JPanel();
		//j.add(container);
		
		//add the container into frame
		super.getContentPane().add(BorderLayout.CENTER,container);
	}
	

	/**
	* A method to create the button and point them to relative actionListener
	*/
	public void createButton() {		
	    b1.setFont(bigFont1);b2.setFont(bigFont1);
	    //set the relative size of buttons
	    Dimension preferredSize = new Dimension(250,80);
	    b1.setPreferredSize(preferredSize);
		b2.setPreferredSize(preferredSize);
		//add the buttons into the panel
		panel1.add(b1);panel1.add(b2);
		//set the panel transparent
		panel1.setOpaque(false);panel2.setOpaque(false);
		b1.setBackground(color);b2.setBackground(color);
		//set the actionListener
		b1.addActionListener(new Confirm());
		b2.addActionListener(new Cancel());
		
		//organize panels in order to show the element as expected
		panel3.add(panel2);
		panel3.add(panel1);
        Dimension preferredSize1 = new Dimension(250,600);
	    empt1.setPreferredSize(preferredSize1);
		empt2.setPreferredSize(preferredSize1);
		Dimension preferredSize2 = new Dimension(1300,200);
		panel3.setPreferredSize(preferredSize2);	 
		panel3.setLayout(new GridLayout(2,1,10,10));
		panel3.setOpaque(false);empt1.setOpaque(false);empt2.setOpaque(false);
		super.getContentPane().add(BorderLayout.SOUTH,panel3);
		super.getContentPane().add(BorderLayout.WEST,empt1);
		super.getContentPane().add(BorderLayout.EAST,empt2);	
	}
	


	/**
	* Title : Confirm
	* Description: This class is the inner class for ConfirmPage class.
	* It execute the ActionListener function when user click the confirm button
	* @author Xinbei Li
	* @version 4.0
	*/
	public class Confirm extends JFrame implements ActionListener{
		/**
		* A method to execute the function of the confirm button.
		* Invoke by JVM
		* @param e The object of ActionEvent class
		*/
		public void actionPerformed(ActionEvent e) {
			visibleFalse();
			
			//skip to the Payment interface(class)
			//Payment pay = new Payment();
			//pay.Layout4();
			HelperControl control = new HelperControl();
			control.skipPayment();
			
			
		}
	}
	
	/**
	* Title : Cancel
	* Description: This class is the inner class for ConfirmPage class.
	* It execute the ActionListener function when user click the cancel button
	* @author Xinbei Li
	* @version 4.0
	*/
	public class Cancel extends JFrame implements ActionListener{
		/**
		* A method to execute the function of the cancel button.
		* Invoke by JVM
		* @param e The object of ActionEvent class
		*/
		public void actionPerformed(ActionEvent e) {
			//setVisible(false);
			visibleFalse();
			 //return to the OrderMenu interface(class)
			HelperControl control = new HelperControl();
			control.skipOrderMenu();
		}
	}
	
		
	
	public static void main(String[] args) {
		ConfirmPage c = new ConfirmPage();
		c.createOrder(); 
		c.createButton();
	}
	
}


