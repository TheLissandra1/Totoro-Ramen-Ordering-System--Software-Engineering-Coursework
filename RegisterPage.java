import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.*;

public class RegisterPage extends Interface
{
	JPanel Apanel = new JPanel();
	JLabel title = new JLabel(
			"<html><body><div style='color:#000000;font-family:Times New Roman;font-size:25px;text-align:center;'>"
			+ "Register Interface</div></body></html>");
	JLabel FN = new JLabel("<html><body><div style='color:#000000;font-family:Times New Roman;font-size:12px;'>"
			+ "Family Name: </div></body></html>");
	JTextField FNT = new JTextField();
	JLabel GN = new JLabel("<html><body><div style='color:#000000;font-family:Times New Roman;font-size:12px;'>"
			+ "Given Name: </div></body></html>");
	JTextField GNT = new JTextField();
	JLabel CW = new JLabel("<html><body><div style='color:#000000;font-family:Times New Roman;font-size:12px;'>"
			+ "E-mail Address/Mobile Number: </div></body></html>");
	JTextField CWT = new JTextField();
	JLabel PW = new JLabel("<html><body><div style='color:#000000;font-family:Times New Roman;font-size:12px;'>"
			+ "Password (valid length:8): </div></body></html>");
	JLabel CPW = new JLabel("<html><body><div style='color:#000000;font-family:Times New Roman;font-size:12px;'>"
			+ "Comfirm Password: </div></body></html>");
	JButton RB = new JButton("<html><body><div style='color:#000000;font-family:Times New Roman;font-size:15px;'>"
			+ "Register</div></body></html>");
	JButton PP = new JButton("<html><body><div style='color:#000000;font-family:Times New Roman;font-size:12px;'>"
			+ "Previous</div></body></html>");
	JPasswordField PWT = new JPasswordField();
	JPasswordField CPWT = new JPasswordField();
	
	private int userID = 0;
	private String path = ".\\users";
	Format f1 = new DecimalFormat("00000000");
	
	public RegisterPage()
	{	
		super.showFrame(2);
		Apanel.setLayout(null);

		title.setLocation(824, 100); 
        title.setSize(300, 70);
        Apanel.add(title);
        
        Apanel.setOpaque(false);
		super.setContentPane(Apanel);
        
//		this.helper();
	}
	
//	private void helper() {
//		this.setColor();
//		this.showQuesqions();
//		this.showTextBox();
//		this.showButton();
//	}
	
	public void showQuesqions() {
		FN.setLocation(810, 200); 
		FN.setSize(200, 20);
        Apanel.add(FN);

		GN.setLocation(810, 285); 
		GN.setSize(200, 20);
        Apanel.add(GN);
        
		CW.setLocation(810, 370); 
		CW.setSize(300, 20);
        Apanel.add(CW);

		PW.setLocation(810, 455); 
		PW.setSize(200, 20);
        Apanel.add(PW);

		CPW.setLocation(810, 540); 
		CPW.setSize(300, 20);
        Apanel.add(CPW);
	}

	public void showTextBox() {
		FNT.setLocation(810, 220); 
		FNT.setSize(300, 50);
        FNT.setFont(new Font("Times New Roman",Font.PLAIN,20));
    	FNT.addKeyListener(new TextFieldKeyListener());
        Apanel.add(FNT);
        	
		GNT.setLocation(810, 305); 
		GNT.setSize(300, 50);
		GNT.setFont(new Font("Times New Roman",Font.PLAIN,20));
		GNT.addKeyListener(new TextFieldKeyListener());
        Apanel.add(GNT);

		CWT.setLocation(810, 390); 
		CWT.setSize(300, 50);
		CWT.setFont(new Font("Times New Roman",Font.PLAIN,20));
        Apanel.add(CWT);
		
		PWT.setLocation(810, 475); 
		PWT.setSize(300, 50);
		PWT.setFont(new Font("Times New Roman",Font.PLAIN,20));
        Apanel.add(PWT);

		CPWT.setLocation(810, 560); 
		CPWT.setSize(300, 50);
		CPWT.setFont(new Font("Times New Roman",Font.PLAIN,20));
        Apanel.add(CPWT);
	}
	
	public void showButton() {
		RB.setLocation(880, 640); 
		RB.setSize(150, 50);
		Apanel.add(RB);
		
		PP.setBounds(1150, 700, 130, 40);
		Apanel.add(PP);
		
		RB.addActionListener(new Register());		
		PP.addActionListener(new PreviousPage());
	}
	
	public void setColor(){
		Color color = new Color(245,255,255);
		
		FNT.setBackground(color);
		GNT.setBackground(color);
		CWT.setBackground(color);
		PWT.setBackground(color);
		CPWT.setBackground(color);
		
		RB.setBackground(color);
		PP.setBackground(color);
	}
	
	public int readerFile() {
		
		File file = new File(path);
		File[] files = file.listFiles();
		if(files.length==0) {
			return userID;
		}
		else {
			for (int i = 0, max = 0; i < files.length; i++) {
				String filename = files[i].getName();
				max = Integer.parseInt(filename.substring(0, filename.lastIndexOf(".")));
				if(userID <= max) {
					userID = max;
				}
				
			}
			userID = userID + 1;
		}
		return userID;
	}

	public class Register extends JFrame implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			UserBean user = new UserBean();
			String familyName = FNT.getText();
			String givenName = GNT.getText();
			String contactWay = CWT.getText();
			String password = String.copyValueOf(PWT.getPassword());
			String cPassword = String.copyValueOf(CPWT.getPassword());
			
			System.out.println(familyName);
			System.out.println(givenName);
			System.out.println(contactWay);
			System.out.println(password);
			System.out.println(cPassword);
			
			
			if(password.equals("")||familyName.equals("")||givenName.equals("")||contactWay.equals("")||cPassword.equals("")) {
				JOptionPane.showMessageDialog(null, "The required information is incomplete!","Check", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				if(password.length() >= 8) {
					if (password.equals(cPassword)) {
						userID = readerFile();
						String newFilename = String.valueOf(f1.format(userID));
						user.setFamilyName(familyName);
						user.setGivenName(givenName);
						user.setContactWay(contactWay);
						user.setPassword(password);
					
						if (new RegisterUser().registerUserWriter(user,newFilename))
						{
							JOptionPane.showMessageDialog(null, "Registration Successful!","Prompt", JOptionPane.INFORMATION_MESSAGE);
						
							visibleFalse();
				            HelperControl control = new HelperControl();
				            control.skipMembership();
							System.out.println("Turn to the Member Login page");
						
						} else{
							JOptionPane.showMessageDialog(null, "registration failed!","Prompt", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Please confirm your password!","Prompt", JOptionPane.ERROR_MESSAGE);
						PWT.setText("");
						CPWT.setText("");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Make sure your password is longer than 8!","Prompt", JOptionPane.ERROR_MESSAGE);
					PWT.setText("");
					CPWT.setText("");
				}
			
			}
		}
	}
	
	
	public class PreviousPage extends JFrame implements ActionListener{
		public void actionPerformed(ActionEvent a)
		{
			
			visibleFalse();
            HelperControl control = new HelperControl();
            control.skipPayment();
			System.out.println("Turn to the Payment page");
		}
	}
	
	public class TextFieldKeyListener implements  KeyListener{         
		public void keyPressed(KeyEvent e){         
			
		}         
		public void keyReleased(KeyEvent e){        
			
		}         
		public void keyTyped(KeyEvent   e){             
			Object o = e.getSource();             
			if   (o instanceof JTextField){                 
				char keyCh = e.getKeyChar(); 
			
				Pattern pat = Pattern.compile("[a-z]|[A-Z]");                
				if(!pat.matcher(String.valueOf(keyCh)).matches()){                     
					if ( keyCh != ' ')                      
						e.setKeyChar('\0');                 
				}       
			}
		}
	}
	
	
}
