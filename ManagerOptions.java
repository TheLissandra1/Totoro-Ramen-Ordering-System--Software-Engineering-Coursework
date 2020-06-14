import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Title: ManagerOptions.java
 * @author qiaonisha_2017212760 from class_2017215107 in EBU6304_Software Engineering_2019/2020
 * @version 1.0 Identity Choose--interface 1
 */

class ManagerOptions extends Interface implements ActionListener{
    private static final long serialVersionUID = 1L;

    JPanel JP1; 
    JLabel descriptionLabel = new JLabel("Manager Options", JLabel.CENTER);
    Font font1 = new Font("Times New Roman", Font.BOLD + Font.ITALIC, 20);
    Font font2 = new Font("Times New Roman", Font.BOLD + Font.ITALIC, 23);
    Color color1 = new Color(255,249,240);
    Color color2 = new Color(96,68,0);
    JButton modifymenu = new JButton("ModifyMenu");
    JButton viewstatistics = new JButton("ViewStatistics");
    JButton previous = new JButton("Previous Page");
    /**
     * constructor
     */
    public ManagerOptions(){
        super.showFrame(4);        
        JPanel JP1 = (JPanel)this.getContentPane();
        JP1.setOpaque(false);
        JP1.setLayout(null);

        descriptionLabel.setFont(font1);
        descriptionLabel.setForeground(color2);
        modifymenu.setFont(font2);
        viewstatistics.setFont(font2);
        previous.setFont(font1);
        modifymenu.setBackground(color1);
        viewstatistics.setBackground(color1);
        previous.setBackground(color1);
        descriptionLabel.setBounds(825, 300, 400, 100);
        modifymenu.setBounds(850, 375, 350, 75);
        viewstatistics.setBounds(850, 500, 350, 75);
        previous.setBounds(935, 600, 180, 50);
        
        
        JP1.add(descriptionLabel);
        JP1.add(modifymenu);
        JP1.add(viewstatistics);
        JP1.add(previous);
        modifymenu.addActionListener(this);
        viewstatistics.addActionListener(this);
        previous.addActionListener(this);
    }

    
    /**
     * this actionPerformed() method helps monitor action events
     */      
    public void actionPerformed(ActionEvent e){
        JButton eventSource = (JButton) e.getSource();
        //Turn to ModifyMenu
        if(eventSource.equals(modifymenu)){
            this.setVisible(false);
            HelperControl control = new HelperControl();
            control.skipModifyMenu();
            System.out.println("Turn to ModifyMenu");
        }
        //Turn to 8_2
        else if(eventSource.equals(viewstatistics)){
            this.setVisible(false);
            HelperControl control = new HelperControl();
            control.skipStaticsPage();
            // control.
            System.out.println("Turn to ViewStatistics");
        }
        //Turn to manager
        else if(eventSource.equals(previous)){
            this.setVisible(false);
            HelperControl control = new HelperControl();
            control.skipManager();
            System.out.println("Turn to Previous Page");
        }
        
    }
    

}