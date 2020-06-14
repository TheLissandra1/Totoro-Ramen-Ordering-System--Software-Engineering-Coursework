import javax.swing.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;

import java.util.Date; 
import java.util.Calendar; 
import java.text.SimpleDateFormat; 

/**
* Title : PayPage.java
* Description: This class aims to execute the relative function of payment interface.
* @author Xinbei Li
* @version 4.0
*/

public class PayPage extends Interface{
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel empt1 = new JPanel();
	JPanel empt2 = new JPanel();
	JLabel stampLab;
	OrderRW order = new OrderRW();
	
	Font bigFont1 = new Font("Times New Roman",Font.BOLD,25);
	Font bigFont2 = new Font("Times New Roman",Font.BOLD,30);
	JButton card = new JButton("Pay by card");
	JButton cash = new JButton("Pay by cash");
	JButton previous = new JButton("Return");
	
	private String[] line = new String[40]; //Store strings which read from the file
	
	/*
	the actual value of needStamp is gained from 
	previous interface by setNeedStamp() method
	*/
	private int needStamp;
	private String total;
	private String date;//file name: current_date.txt
	private String orderFile="all.txt";//total orders
	private String dateWrite;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minutes;
	private int seconds;
	private int hasStamp;//whether user has membership
	
	
	/**
	* Constructor
	* The main constructor, it sets the frame and calls helper method 
	*/
	public PayPage() {
		super.showFrame(3);
		 
        //set panel size and set it transparent
		Dimension preferredSize2 = new Dimension(1300,160);
	    panel.setPreferredSize(preferredSize2);	
		panel.setOpaque(false);
		super.getContentPane().setLayout(new BorderLayout());
		super.getContentPane().add(BorderLayout.NORTH,panel);
		
	}
	
	/**
	* When user has membership number, it will execute through this path
	*/
	public void setNeed(int need) {
		stampLab = new JLabel("Current Virtual Stamps:"+LoginSuccessfully.str3,
				JLabel.CENTER);
		this.needStamp=need;
		this.hasStamp=1;
		this.showOrder();
		this.showButton();
	}
	
	/**
	* When user don't have membership number (pay directly), it will execute through this path
	*/
	public void noStamp() {
		stampLab = new JLabel("",JLabel.CENTER);
		this.needStamp=0;
		this.hasStamp=0;
		this.showOrder();
		this.showButton();
	}
	
	/**
	* A method to read the order from relative file and print it on the screen 
	*/
	public void showOrder() {

		line=order.readFile();
	
	if(needStamp==0) //do not use stamp
	   total=Float.parseFloat(line[20])+"";
	else if(needStamp==1) {
		/*
		 if user choose to use the stamp in the previous interface
		 the total price will minus by 9.9(fixed prize)
		 */
		double num1=Float.parseFloat(line[20])-9.9;
		DecimalFormat  mFormat = new DecimalFormat(".0");
		total = mFormat .format(num1);
		
	}
	
	//design the order part using JTable
	String[] columnNames = {"empty","dishes","prize","dishes","prize"};
	Object[][] content = {
		{"",line[0]+"   -------------------", ""+line[1], line[9]+"   -----------------------",""+line[10]},
		{"",line[2], "",line[11],""+line[12]},
		{"",line[3], "",line[13],""+line[14]},
		{"",line[4], "",line[15],""+line[16]},
		{"",line[5], "",line[17],""+line[18]},
		{"",line[6], "","",""},
		{"",line[7], "","",""},
		{"",line[8], "","",""},
		{"","", "","",""},
		{"",line[19]+"        ------------------------",""+total,"",""},
		};
	JTable table = new JTable(content, columnNames);
	table.setEnabled(false);
	table.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
	
	
	//set the width of column, each column has different width according to its content
	TableColumn column1=table.getColumnModel().getColumn(0);
	column1.setPreferredWidth(10);
	TableColumn column2=table.getColumnModel().getColumn(1);
	column2.setPreferredWidth(230);
	TableColumn column3=table.getColumnModel().getColumn(2);
	column3.setPreferredWidth(10);
	TableColumn column4=table.getColumnModel().getColumn(3);
	column4.setPreferredWidth(230);
	TableColumn column5=table.getColumnModel().getColumn(4);
	column5.setPreferredWidth(10);

    table.setFont(bigFont1);
	table.setRowHeight(45);
	table.setShowGrid(false);
	//set the background color of table
	table.setBackground(color);
	
	//centralize the whole table
	DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	tcr.setHorizontalAlignment(SwingConstants.LEFT);
	table.setDefaultRenderer(Object.class, tcr);
			
	JScrollPane scrollPane = new JScrollPane();;
	scrollPane.getViewport().setOpaque(false);//set JScrollPane transparent
	scrollPane.setOpaque(false);//set viewport transparent
	scrollPane.setViewportView(table);
	table.setFillsViewportHeight(true);
	
	//add the table into the container
	//Container container=getContentPane();
	//((JComponent) container).setOpaque(false); 
	Container container=new Container();
	container.setLayout(new BorderLayout());
	container.add(table, BorderLayout.CENTER);
	//add the container into frame
	super.getContentPane().add(BorderLayout.CENTER,container);
				
	}
	
	/**
	* A method to create the button and point them to relative actionListener
	*/
	public void showButton() {
		//set the properties of buttons
		stampLab.setFont(bigFont2);
		card.setFont(bigFont2);cash.setFont(bigFont2);previous.setFont(bigFont2);
		//set the size of 3 buttons
		Dimension preferredSize = new Dimension(250,60);
		card.setPreferredSize(preferredSize);
		cash.setPreferredSize(preferredSize);
		previous.setPreferredSize(preferredSize);
		card.setBackground(color);cash.setBackground(color);previous.setBackground(color);
		
		//set the size of 2 panels
		Dimension preferredSize1 = new Dimension(200,600);
		empt1.setPreferredSize(preferredSize1);
		empt2.setPreferredSize(preferredSize1);
		empt1.setOpaque(false);empt2.setOpaque(false);
	    //add both buttons and label into the panel
		panel1.add(card);panel1.add(cash);panel1.add(previous);
		panel2.setLayout(new GridLayout(2,1,10,10));
		panel2.add(stampLab);
		panel2.add(panel1);
		panel1.setOpaque(false);panel2.setOpaque(false);
		//add the panel into the frame
		super.getContentPane().add(BorderLayout.SOUTH,panel2);
		super.getContentPane().add(BorderLayout.WEST,empt1);
		super.getContentPane().add(BorderLayout.EAST,empt2);
		
		//set the actionListener
		card.addActionListener(new Pay());
		cash.addActionListener(new Pay());
		previous.addActionListener(new Return());
	}
		
	/**
	* Title : Pay
	* Description: This class is the inner class for PayPage class.
	* It execute the ActionListener function 
	* when user click the "pay by cash" or "pay by card" button 
	* @author Xinbei Li
	* @version 4.0
	*/
	public class Pay extends JFrame implements ActionListener{
		/**
		* A method to execute the function of the 2 buttons.
		* Invoke by JVM
		* @param e The object of ActionEvent class
		*/
		public void actionPerformed(ActionEvent e) {
		//gain current system time and store it
		Date now = new Date(); 
		//set the format of current date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		dateWrite= dateFormat.format(now); 
		//store date elements into different variables
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR); 
		month = c.get(Calendar.MONTH)+1; 
		day = c.get(Calendar.DATE); 
		hour = c.get(Calendar.HOUR_OF_DAY); 
		minutes = c.get(Calendar.MINUTE); 
		seconds = c.get(Calendar.SECOND);
			
		//specified the date variable according to current time
		String month1=null;String day1=null;
		if(month<10) {
			month1="0"+month;
		}
		else month1=""+month;
				
		if(day<10) {
			day1="0"+day;
		}
		else day1=""+day;
		date=year+month1+day1+".txt";
		System.out.println(month);
		
		order.writePay(dateWrite,line, date,orderFile);
		StampIncreasing();
	    
		visibleFalse();
		//return to the IdentityChoose interface(class)
		HelperControl control = new HelperControl();
		control.skipIdentityChoose();
		}   
		
		/**
		* A method to add one stamp to customer when they pay successfully
		*/
		public void StampIncreasing() {
		    if (hasStamp==1&&needStamp==0) {
		    int num = Integer.parseInt(LoginSuccessfully.str3.trim());
			int num1=num+1;
			String num2 = num1 + " ";
			LoginSuccessfully.ChangeFileData(LoginSuccessfully.str3,num2);
		    }
	    }
	}
	
	/**
	* Title : Return
	* Description: This class is the inner class for PayPage class.
	* It execute the ActionListener function when user click the "return" button 
	* @author Xinbei Li
	* @version 4.0
	*/
	public class Return extends JFrame implements ActionListener{
		
		/**
		* A method to execute the function of the return button.
		* Invoke by JVM
		* @param e The object of ActionEvent class
		*/
		public void actionPerformed(ActionEvent e) {
			
			visibleFalse();
		    //user has membership
			if(hasStamp==1) {
				//add 10 stamps
				int num = Integer.parseInt(LoginSuccessfully.str3);
				String num3=num-10+"";			 			
				String num2 = num + "";
				LoginSuccessfully.ChangeFileData(num3,num2);
				HelperControl control = new HelperControl();
				control.skipOrderMenu();
			}
			//user do not have membership
			else {
				HelperControl control = new HelperControl();
				control.skipOrderMenu();
			}
			
		}
	}
		
		
	public static void main(String[] args) {
		PayPage  p = new PayPage();
		p.noStamp();
     }
	
	
		

}


