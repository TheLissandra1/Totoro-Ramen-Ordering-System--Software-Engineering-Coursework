import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;

/**
 * Title: IdentityChoose.java
 * Description: The class is the first interface that allow users to choose his/her identity as a customer/manager
 * @author qiaonisha_2017212760 from class_2017215107 in EBU6304_Software Engineering_2019/2020
 * @version 1.0 Identity Choose--interface 1
 */

class IdentityChoose extends Interface implements ActionListener{
    private static final long serialVersionUID = 1L;

    JPanel JP1 = new JPanel();   
    JLabel descriptionLabel = new JLabel("Choose Your Identity", JLabel.CENTER);
    Font font1 = new Font("Times New Roman", Font.BOLD + Font.ITALIC, 20);
    Font font2 = new Font("Times New Roman", Font.BOLD + Font.ITALIC, 23);
    Color color1 = new Color(255,249,240);
    Color color2 = new Color(96,68,0);
    JButton customerButton = new JButton("customer");
    JButton managerButton = new JButton("manager");
    /**
     * Constructor
     */
    public IdentityChoose(){
        super.showFrame(1);        
        JPanel JP1 = (JPanel)this.getContentPane();
        JP1.setOpaque(false);
        JP1.setLayout(null);
        descriptionLabel.setFont(font1);
        descriptionLabel.setForeground(color2);
        customerButton.setFont(font2);
        managerButton.setFont(font2);
        customerButton.setBackground(color1);
        managerButton.setBackground(color1);
        descriptionLabel.setBounds(825, 320, 400, 100);
        customerButton.setBounds(850, 400, 350, 75);
        managerButton.setBounds(850, 525, 350, 75);
    
        
        JP1.add(descriptionLabel);//add "Choose Your Identity"
        JP1.add(customerButton);//add "customer" button
        JP1.add(managerButton);//add "manager" button
        customerButton.addActionListener(this);
        managerButton.addActionListener(this);
    }
    /**
     * this actionPerformed() method helps monitor action events
     */     
    public void actionPerformed(ActionEvent e){
        JButton eventSource = (JButton) e.getSource();
        HelperControl control = new HelperControl();
        //Turn to OrderMenu
        if(eventSource.equals(customerButton)){
            this.setVisible(false);
            
            control.skipOrderMenu();
            System.out.println("Turn to 2nd page: OrderMenu");    
        }
        //Turn to manager
        else if(eventSource.equals(managerButton)){
            this.setVisible(false);
            // manager mng  = new manager();
            // mng.setVisible(true);
            control.skipManager();
            System.out.println("Turn to 7Th page: manager");
        }
        
    }
    

}