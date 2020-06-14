import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Manager extends Interface{
	private static final long serialVersionUID = 1L; 
	Font Font1 = new Font("Times New Roman",Font.PLAIN, 30);
	Font Font2 = new Font("Times New Roman",Font.PLAIN, 20);
	Font Font3 = new Font("Times New Roman",Font.BOLD, 18);
	JButton btnLogin = new JButton("Log in");
	JButton btnReset = new JButton("Reset");
	JButton btnprepage = new JButton("Previous Page");
	JLabel lblTitle = new JLabel("Manager Login Interface");
	JLabel Caption = new JLabel("Enter Secert Key For Manager  to log in");
	JLabel lblKey = new JLabel("Key");
	JPasswordField txtKey = new JPasswordField();
	JPanel contentPane = new JPanel();
	
	public Manager() {
		super.showFrame(4);		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		txtKey.setColumns(10);
		lblTitle.setBounds(907, 311, 430, 29);Caption.setBounds(870, 387, 347, 18);txtKey.setBounds(869, 418, 390, 34);lblKey.setBounds(837, 426, 45, 18);
		lblTitle.setFont(Font1);Caption.setFont(Font3);txtKey.setFont(Font3);lblKey.setFont(Font3);
		contentPane.add(lblTitle);contentPane.add(Caption);contentPane.add(txtKey);contentPane.add(lblKey);
		this.helper();
	}
	
	private void helper() {
		this.createButton();
		this.setColor();
	}
	
	public void createButton() {
		btnLogin.setBounds(907, 500, 106, 37);btnReset.setBounds(1071, 500, 106, 37);btnprepage.setBounds(1071, 609, 209, 34);
		btnLogin.setFont(Font2);btnReset.setFont(Font2);btnprepage.setFont(Font2);
		contentPane.add(btnLogin);contentPane.add(btnReset);contentPane.add(btnprepage);
		btnLogin.addActionListener(new btnLogin());
		btnReset.addActionListener(new Reset());
		btnprepage.addActionListener(new btnprepage());
	}
	
	public void setColor(){
		Color color = new Color(255,255,240);
		btnLogin.setBackground(color);btnReset.setBackground(color);btnprepage.setBackground(color);txtKey.setBackground(color);
	}
	
	public class btnLogin extends JFrame implements ActionListener{	
		public void actionPerformed(ActionEvent e) {
			String str0 = String.copyValueOf(txtKey.getPassword());
			if(str0.equals("14036782")) {					
				HelperControl helpercontrol=new HelperControl();
				helpercontrol.skipManagerOptions();
		           visibleFalse();
		           System.out.println("Turn to 8Th page: MANAGEROPTIONS");
			}else {
				JOptionPane.showMessageDialog(null,"Verification failed!");
			}		
			
		}
	}
	
	public class Reset extends JFrame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			txtKey.setText("");
       }
    }
	
	public class btnprepage extends JFrame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			HelperControl helpercontrol=new HelperControl();
			helpercontrol.skipIdentityChoose();
			visibleFalse();
       }
    }
	public static void main(String[] args) {
		Manager manager = new Manager();
		manager.setVisible(true);
	}
	
}
