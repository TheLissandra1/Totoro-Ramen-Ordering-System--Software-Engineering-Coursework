import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
public class Membership extends Interface{
	private static final long serialVersionUID = 1L;
	Font Font1 = new Font("Times New Roman",Font.PLAIN, 30);
	Font Font2 = new Font("Times New Roman",Font.ITALIC, 15);
	Font Font3 = new Font("Times New Roman",Font.PLAIN, 20);
	Font Font4 = new Font("Times New Roman",Font.ITALIC, 20);
	JPanel contentPane= new JPanel();
	JLabel lblTitle = new JLabel("Member Login Interface");
	JLabel lblMembershipNumber = new JLabel("Membership Number");
	JLabel lblPassword = new JLabel("Password");
	static JTextField TxtMembershipNumber= new JTextField();
	JPasswordField TxtPassword = new JPasswordField();
	JButton btnReset= new JButton("Reset");
	JButton btnLogin = new JButton("Login");
	JButton btnprepage= new JButton("Previous Page");
	
	public Membership() {
		super.showFrame(3);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		TxtMembershipNumber.setColumns(10);TxtPassword.setColumns(10);
		lblTitle.setBounds(452, 162, 437, 61);TxtMembershipNumber.setBounds(552, 323, 357, 34);TxtPassword.setBounds(552, 426, 357, 34);lblMembershipNumber.setBounds(329, 321, 209, 28);lblPassword.setBounds(397, 440, 106, 18);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 37));TxtMembershipNumber.setFont(Font2);TxtPassword.setFont(Font2);lblMembershipNumber.setFont(new Font("Times New Roman", Font.BOLD, 20));lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(lblTitle);contentPane.add(TxtMembershipNumber);contentPane.add(TxtPassword);contentPane.add(lblMembershipNumber);contentPane.add(lblPassword);
		this.helper();		
		}
	
	private void helper() {
		this.createButton();
		this.setColor();
	}
	
	public void createButton() {
		btnLogin.setBounds(452, 546, 106, 37);btnReset.setBounds(698, 546, 106, 37);btnprepage.setBounds(1073, 706, 209, 34);
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));btnReset.setFont(new Font("Times New Roman", Font.BOLD, 20));btnprepage.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btnLogin);contentPane.add(btnReset);contentPane.add(btnprepage);
		btnLogin.addActionListener(new btnLogin());
		btnReset.addActionListener(new Reset());
		btnprepage.addActionListener(new btnprepage());
	}

	public void setColor(){
		Color color = new Color(255,255,240);
		btnLogin.setBackground(color);btnReset.setBackground(color);btnprepage.setBackground(color);TxtMembershipNumber.setBackground(color);TxtPassword.setBackground(color);
	}
		
	public static String getTxtMembershipNumber(){
		 return TxtMembershipNumber.getText();	
	}
	
	public class btnLogin extends JFrame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String str0 = TxtMembershipNumber.getText();
			String str1 = String.copyValueOf(TxtPassword.getPassword());			
			String ss="";
		try {
			File file =new File("users/"+str0+".txt");			
            				Scanner sc = new Scanner(file);
		            while(sc.hasNextLine()) {
							ss = sc.nextLine();
							String[] str = ss.split("\\s+");
							for(int i=0;i<str.length;i++) {
								if(str[i].equals("Password:")) {										
									String str3=str[i+1];
									if(str3.equals(str1)) {
										HelperControl helpercontrol=new HelperControl();
										helpercontrol.skiploginSuccessfully();
					    				visibleFalse();
									}
									else
										 
										JOptionPane.showMessageDialog(null, "Verification failed!", "Warning",JOptionPane.PLAIN_MESSAGE);	
									
								}
								

								}
							}
			
			sc.close();
			
		            }catch(FileNotFoundException a) {
		            	JOptionPane.showMessageDialog(null, "Verification failed!", "Warning",JOptionPane.PLAIN_MESSAGE);
		}
       }
    }
	
	public class Reset extends JFrame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
	      TxtMembershipNumber.setText("");
	      TxtPassword.setText("");
       }
    }
	
	public class btnprepage extends JFrame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			visibleFalse();
			HelperControl helpercontrol=new HelperControl();
			helpercontrol.skipPayment();
       }
    }
	public static void main(String[] args) {
		Membership membership= new Membership();
		membership.setVisible(true);
	}
}
