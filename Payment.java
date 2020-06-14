import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Title: Payment.java
 * @author qiaonisha_2017212760 from class_2017215107 in EBU6304_Software Engineering_2019/2020
 * @version 1.0 Payment--interface 4
 */
class Payment extends Interface implements ActionListener{
    private static final long serialVersionUID = 1L;

    JPanel JP1 = new JPanel();
    JLabel descriptionLabel = new JLabel("Payment", JLabel.CENTER);
    Font font1 = new Font("Times New Roman", Font.BOLD + Font.ITALIC, 50);
    Font font2 = new Font("Times New Roman", Font.BOLD + Font.ITALIC, 23);
    Color color1 = new Color(255,249,240);
    Color color2 = new Color(8, 25, 56);//96,68,0
    JButton PayDirectly = new JButton("Pay Directly");
    JButton MemberLogin = new JButton("Member Login");
    JButton SignUp = new JButton("Sign Up");
    JButton PreviousPage = new JButton("Previous Page");
    /**
     * constructor
     */
    public Payment(){
        super.showFrame(3);
        JPanel JP1 = (JPanel)this.getContentPane();
        JP1.setOpaque(false);
        JP1.setLayout(null);
        
        descriptionLabel.setFont(font1);
        PayDirectly.setFont(font2);
        MemberLogin.setFont(font2);
        SignUp.setFont(font2);
        PreviousPage.setFont(font2);
        descriptionLabel.setForeground(color2);
        PayDirectly.setBackground(color1);
        MemberLogin.setBackground(color1);
        SignUp.setBackground(color1);
        PreviousPage.setBackground(color1);
        descriptionLabel.setBounds(450, 200, 400, 100);
        PayDirectly.setBounds(500, 290, 300, 75);
        MemberLogin.setBounds(500, 390, 300, 75);
        SignUp.setBounds(500, 490, 300, 75);
        PreviousPage.setBounds(560, 700, 180, 40);
        
        JP1.add(descriptionLabel);
        JP1.add(PayDirectly);
        JP1.add(MemberLogin);
        JP1.add(SignUp);
        JP1.add(PreviousPage);
        PayDirectly.addActionListener(this);
        MemberLogin.addActionListener(this);
        SignUp.addActionListener(this);  
        PreviousPage.addActionListener(this);   
    }


    /**
     * this actionPerformed() method helps monitor action events
     */  
    public void actionPerformed(ActionEvent e){
        JButton eventSource = (JButton) e.getSource();
        
        //turn to PayPage
        if(eventSource.equals(PayDirectly)){
            this.setVisible(false);
            HelperControl control = new HelperControl();
            control.skipPay(1,0);
            System.out.println("Turn to the Paypage");
        }
        //turn to Membership
        else if(eventSource.equals(MemberLogin)){
            this.setVisible(false);
            HelperControl control = new HelperControl();
            control.skipMembership();
            System.out.println("Turn to the Member Login page");
        }
        //turn to Register
        else if(eventSource.equals(SignUp)){
            this.setVisible(false);
            HelperControl control = new HelperControl();
            control.skipRegisterPage();
            System.out.println("Turn to the Sign Up page");
        }
        //turn to ConfirmPage
        else if(eventSource.equals(PreviousPage)){
            this.setVisible(false);
            HelperControl control = new HelperControl();
            control.skipConfirm();
            System.out.println("Turn to the previous page");
        }
    }


} 