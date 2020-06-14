import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.RandomAccessFile;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
public class LoginSuccessfully extends Interface{
	private static final long serialVersionUID = 1L;
	Font Font1 = new Font("Times New Roman",Font.PLAIN, 30);
	Font Font2 = new Font("Times New Roman",Font.ITALIC, 25);
	Font Font3 = new Font("Times New Roman",Font.PLAIN, 25);
	Font Font4 = new Font("Times New Roman",Font.PLAIN, 20);
	Font Font5 = new Font("Times New Roman",Font.PLAIN, 25);
	JPanel contentPane= new JPanel();
	public static String str3;
	public static String num2;
	JLabel lblTitle = new JLabel("You have logged in successfully");
	JLabel lblCaption = new JLabel("Current Virtual Stamps:");
	JLabel lblCaption_2 = new JLabel("Whether pay by virtual stamps or not?");
	JLabel lblStamps = new JLabel("");
	JButton btnYes = new JButton("Yes");
	JButton btnNo = new JButton("No");
	JButton btnprepage = new JButton("Previous Page");
	
	public LoginSuccessfully() {
		super.showFrame(2);
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		lblTitle.setBounds(753, 282, 503, 61);lblCaption.setBounds(763, 356, 365, 31);lblCaption_2.setBounds(753, 415, 540, 31);lblStamps.setBounds(1053, 348, 106, 46);
		lblTitle.setFont(Font1);lblCaption.setFont(Font2);lblCaption_2.setFont(Font3);lblStamps.setFont(Font5);
		contentPane.add(lblTitle);contentPane.add(lblCaption);contentPane.add(lblCaption_2);contentPane.add(lblStamps);
		this.helper();
	}
	private void helper() {
		this.showStamps();
		this.createButton();
		this.setColor();
	}
	
	public void createButton() {
		btnYes.setBounds(886, 484, 106, 37);btnNo.setBounds(886, 543, 106, 37);btnprepage.setBounds(1071, 628, 209, 34);
		btnYes.setFont(Font4);btnNo.setFont(Font4);btnprepage.setFont(Font4);
		contentPane.add(btnYes);contentPane.add(btnNo);contentPane.add(btnprepage);
		btnYes.addActionListener(new btnYes());
		btnNo.addActionListener(new btnNo());
		btnprepage.addActionListener(new btnprepage());
	}
	
    public void showStamps() {
		String ss="";
	    try {
			File file =new File("users/"+Membership.getTxtMembershipNumber()+".txt");			            
          			Scanner sc = new Scanner(file);
		            while(sc.hasNextLine()) {
							ss = sc.nextLine();
							String[] str = ss.split("\\s+");
							for(int i=0;i<str.length;i++) {
								if(str[i].equals("Stamps:")) {									
								    str3=str[i+1];
									lblStamps.setText(str3);
								}
								}
							}
		            sc.close();
		            }catch(FileNotFoundException a) {
			JOptionPane.showMessageDialog(null,"Something goes wrong... Please contact the cutest programmer! ");
		}
    }
    //How to modify the contents of a file
    public static void ChangeFileData(String s1,String s2) {		
		 RandomAccessFile raf = null;		
	        try {
	            raf = new RandomAccessFile("users/"+Membership.getTxtMembershipNumber()+".txt", "rw");
	            String line = null;
	            long lastPoint = 0; 
	            while ((line = raf.readLine()) != null) {
	                final long ponit = raf.getFilePointer();
	                if(line.contains("Stamps:")){
	                    String str=line.replace(s1, s2);			                    
	                    raf.seek(lastPoint);
	                    raf.writeBytes(str);
	                }
	                lastPoint = ponit;
	            }
	        } catch (FileNotFoundException e) {
	             
	            e.printStackTrace();
	        } catch (IOException e) {
	             
	            e.printStackTrace();
	        } finally {
	            try {
	                raf.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}
    //change buttons' color
	public void setColor(){
		Color color = new Color(255,255,240);
		btnYes.setBackground(color);btnNo.setBackground(color);btnprepage.setBackground(color);
	}
	//return to Membership
	public class btnprepage extends JFrame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			visibleFalse();
			HelperControl helpercontrol=new HelperControl();
			helpercontrol.skipMembership();
         }
    }
	
	public class btnYes extends JFrame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int num = Integer.parseInt(str3.trim());
			if(num<10) {	
				JOptionPane.showMessageDialog(null, 
						"You don't have enough stamps!", "Warning",JOptionPane.PLAIN_MESSAGE);
				
			}else {
				HelperControl control = new HelperControl();  
				control.skipPay(0,1);				
				visibleFalse();
				JOptionPane.showMessageDialog(null, "You  have used ten stamps!", "Message",JOptionPane.PLAIN_MESSAGE);
				 
				 int num1=num-10;
				  num2 = num1 + " ";
				  ChangeFileData(str3,num2);
			}
         }
    }
	
	public class btnNo extends JFrame implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			visibleFalse();
			HelperControl control = new HelperControl();  
			control.skipPay(0,0); 			
         }
    }
    public static void main(String[] args) {
    	LoginSuccessfully logsuccess = new LoginSuccessfully();
		logsuccess.setVisible(true);	
    }
}
