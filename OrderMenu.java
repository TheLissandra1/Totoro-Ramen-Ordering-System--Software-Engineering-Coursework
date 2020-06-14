/**
 * @Title:        OrderMenu.java
 * @Description:  This file is mainly used to order and specilise the food for customers.
 *                And this file can only implement the page 2.
 *                This version added the background for the interface.
 *                This version fixed the bug that user can jump to next page without finising ordering.
 *                This version split class based on entity, controls and boundaries.
 * @author:       Rui Wang
 * @version:      4.0
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font.*;
import java.io.*;
import java.io.File;
import java.awt.Color;

public class OrderMenu extends Interface implements ActionListener{

    // Add some basic items for the interface
    JButton[] addOnChooses = new JButton[4];
    JLabel[] addOnPrices = new JLabel[4];
    JButton[][] specialties = new JButton[6][3];
    JLabel[] dishes = new JLabel[6];
    JButton[] spicyDegree = new JButton[6];
    JLabel spiciness;
    static private JLabel fixedPrice, nodPrice, add_Ons, addPrice, funcTitle;
    static private JButton eatIn, takeAway;
    JButton prePage, reset;
    ImageIcon background;
    JPanel imagePanel;
    int[] isOrdered = new int[21];

    // Get entity classes for this class.
    Menu myMenu = new Menu();
    OrderRW newOrder = new OrderRW();
    HelperControl newControl = new HelperControl();

    /**
     * Set the constructor for the OrderMenu object.
     */ 
    public OrderMenu(){
        super.showFrame(5);

        this.iniIsOrder();
        this.createComponents();
        this.setComponentsLayout();
        this.setAppearance();
    }

    /**
     *  This method is used to get the action and set the reation.
     */ 
    public void actionPerformed(ActionEvent e){

        JButton eventSource = (JButton) e.getSource();

        if(! (eventSource.equals(eatIn) || eventSource.equals(takeAway) || eventSource.equals(prePage) || eventSource.equals(reset))){

            if(eventSource.equals(addOnChooses[0])){
                isOrdered[11] = Integer.valueOf(myMenu.addOns[0][1]);
                this.buttonSetGreen(addOnChooses[0]);
            }else if(eventSource.equals(addOnChooses[1])){
                isOrdered[13] = Integer.valueOf(myMenu.addOns[1][1]);
                this.buttonSetGreen(addOnChooses[1]);
            }else if(eventSource.equals(addOnChooses[2])){
                isOrdered[15] = Integer.valueOf(myMenu.addOns[2][1]);
                this.buttonSetGreen(addOnChooses[2]);
            }else if(eventSource.equals(addOnChooses[3])){
                isOrdered[17] = Integer.valueOf(myMenu.addOns[3][1]);
                this.buttonSetGreen(addOnChooses[3]);
            }else{
                
                int arrayNum;
                for(int i = 0; i < 6; i++){
                    for(int j = 0; j < 3; j++){
                        if(eventSource.equals(specialties[i][j])){
                            arrayNum = i;
                            this.cancelGreen(arrayNum);
                            isOrdered[i+2] = j+1;
                            this.buttonSetGreen(eventSource);
                        }
                    }
                    spicyDegree[i].setOpaque(false);
                    spicyDegree[i].setBorderPainted(true);
                    if(eventSource.equals(spicyDegree[i])){
                        isOrdered[8] = i+1;
                        this.buttonSetGreen(eventSource);
                    }
                }
            }
            
        }else if(eventSource.equals(prePage)){
            this.setVisible(false);
            // IdentityChoose i = new IdentityChoose();
            newControl.skipIdentityChoose();
        }else if(eventSource.equals(reset)){
            this.setVisible(false);
            OrderMenu newOne = new OrderMenu();
            System.out.println("****************This is a new OrderMenu!******************");
        }else{
            if(this.countOrder() == 0){
                JOptionPane.showMessageDialog(null, "Please finish the specialization of the left colomn.","Attention!", JOptionPane.INFORMATION_MESSAGE);
            }else{
                recordOrder();
                System.out.println("**************************************************************************");
                System.out.println("You have finished choosing food, and you will get your order file.");
                System.out.println("**************************************************************************");

                for(int i = 0; i < 21; i++){
                    System.out.println(newOrder.myOrder[i]);
                }

                System.out.println("**************************************************************************");
                System.out.println("The following is values of isOrdered.");
                System.out.println("**************************************************************************");
                for(int i = 0; i < 21; i++){
                    System.out.println(isOrdered[i]);
                }

                newOrder.writeOrder();
                this.setVisible(false);
                // ConfirmPage myConfirm = new ConfirmPage();
                newControl.skipConfirm();
            }
            
        }
    
    }

    /**
     * This method is used to set button green.
     */
    public void buttonSetGreen(JButton iButton){
        iButton.setOpaque(true);
        iButton.setBorderPainted(false);
        iButton.setBackground(new Color(106, 176, 76));
    }

    /**
     * This method is used to cancel to set buttons green.
     */
    public void cancelGreen(int arrayNum){
        for(int j = 0; j < 3; j++){
            specialties[arrayNum][j].setOpaque(false);
            specialties[arrayNum][j].setBorderPainted(true);
            specialties[arrayNum][j].setBackground(new Color(189, 195, 199));
        }
    }

    /**
     * This method is used to initiate the isOrdered array.
     */
    public void iniIsOrder(){
        for(int i = 0; i < 21; i++){
            isOrdered[i] = 0;
        }
    }

    /**
     * This method is used to check wether the user has finished all the specialization.
     */
    public int countOrder(){
        int countOrd = 0;
        for(int i = 2; i <= 9; i++){
            if(isOrdered[i] != 0){
                countOrd ++;
            }
        }
        if(countOrd == 7){
            return 1;
        }else{
            return 0;
        }
    }
    
    /**
     * This method is used to change the ordering details based on choices of customers.
     */
    public void recordOrder(){
        for(int i = 0; i < 21; i++){
            if(isOrdered[i] != 0){
                if(i < 8){
                    newOrder.myOrder[i] = newOrder.myOrder[i] + ": " + specialties[i-2][isOrdered[i]-1].getText();
                }else if(i == 8){
                    newOrder.myOrder[i] = newOrder.myOrder[i] + ": " + spicyDegree[isOrdered[i]-1].getText();
                }else if(i > 10 && i < 18 && (i/2 != 0)){
                    newOrder.myOrder[i] = newOrder.myOrder[i] + ": 1";
                    newOrder.myOrder[i+1] = String.valueOf(isOrdered[i]);
                }
            }
        }
        isOrdered[10] = isOrdered[11] + isOrdered[13] + isOrdered[15] + isOrdered[17];
        newOrder.myOrder[10] = String.valueOf(isOrdered[10]);
        double totalPrice = isOrdered[10] + Double.valueOf(myMenu.mainPrice.toString());
        newOrder.myOrder[20] = String.valueOf(totalPrice);

    }

    /**
     * This method is used to create components for the Menu page and set the content of each components.
     */
    public void createComponents(){

        funcTitle = new JLabel("Personalize Your Ramen!");
        fixedPrice = new JLabel("Fixed Price");
        nodPrice = new JLabel("" + myMenu.mainPrice );
        add_Ons = new JLabel("Add-Ons");
        addPrice = new JLabel("Prices");

        // the Fixed Price part.
        spiciness = new JLabel(myMenu.spicy[0]);
        for(int i = 0; i < 6; i++){
            dishes[i] = new JLabel(myMenu.dishes[i][0]);
            for(int j = 0; j < 3; j++){
                specialties[i][j] = new JButton(myMenu.dishes[i][j+1]);
                specialties[i][j].addActionListener(this);
            }
            spicyDegree[i] = new JButton(myMenu.spicy[i+1]);
            spicyDegree[i].addActionListener(this);
        }

        // the Add-Ons part.
        for(int i = 0; i < 4; i++){
            addOnChooses[i] = new JButton(myMenu.addOns[i][0]);
            addOnChooses[i].addActionListener(this);
            addOnPrices[i] = new JLabel( myMenu.addOns[i][1]);
        }

        eatIn = new JButton("Eat In");
        eatIn.addActionListener(this);
        takeAway = new JButton("Take Away");
        takeAway.addActionListener(this);

        prePage = new JButton("Previous");
        prePage.addActionListener(this);
        reset = new JButton("Reset Choices");
        reset.addActionListener(this);

    }

    /**
     * This method is used to set layout for the page.
     */
    public void setComponentsLayout(){

        funcTitle.setBounds(530, 135, 300, 20);
        super.getContentPane().add(funcTitle);

        fixedPrice.setBounds(150, 175, 100, 20);
        super.getContentPane().add(fixedPrice);
        add_Ons.setBounds(800, 175, 100, 20);
        super.getContentPane().add(add_Ons);
        nodPrice.setBounds(700, 175, 50, 20);
        super.getContentPane().add(nodPrice);
        addPrice.setBounds(1150, 175, 60, 20);
        super.getContentPane().add(addPrice);

        for(int i = 0; i < 6; i++){
            dishes[i].setBounds(235, 210+(i*75), 150, 50);
            super.getContentPane().add(dishes[i]);
            if(i<3){
                for(int j = 0; j < 3; j++){
                    specialties[i][j].setBounds(350+(j*140), 210+(i*75), 130, 50);
                    super.getContentPane().add(specialties[i][j]);
                }
            }else{
                for(int j = 0; j < 2; j++){
                    specialties[i][j].setBounds(350+(j*140), 210+(i*75), 130, 50);
                    super.getContentPane().add(specialties[i][j]);
                }
            }
            spicyDegree[i].setBounds(350+(i*70), 660, 75, 50); 
            super.getContentPane().add(spicyDegree[i]);
        }
        spiciness.setBounds(235, 660, 150, 50);
        super.getContentPane().add(spiciness);

        for(int i = 0; i < 4; i++){
            addOnChooses[i].setBounds(900, 210+(i*75), 200, 50);
            super.getContentPane().add(addOnChooses[i]);
            addOnPrices[i].setBounds(1150, 210+(i*75), 40, 50);
            super.getContentPane().add(addOnPrices[i]);
        }

        eatIn.setBounds(850, 550, 200, 50);
        super.getContentPane().add(eatIn);
        takeAway.setBounds(850, 610, 200, 50);
        super.getContentPane().add(takeAway);
        prePage.setBounds(1100, 700, 150, 35);
        super.getContentPane().add(prePage);
        reset.setBounds(1100, 650, 150, 35);
        super.getContentPane().add(reset);
    }

    /**
     * This method is used to set appearance for items of the page.
     */
    public void setAppearance(){

        Font fTitle = new Font("Times New Roman", Font.BOLD, 20);
        funcTitle.setFont(fTitle);
        fixedPrice.setFont(fTitle);
        nodPrice.setFont(fTitle);
        add_Ons.setFont(fTitle);
        addPrice.setFont(fTitle);

        Font dishFont = new Font("Times New Roman", Font.BOLD, 16);
        Font specFont = new Font("Times New Roman", Font.PLAIN, 16);
        Font spicFont = new Font("Times New Roman", Font.PLAIN, 14);
        for(int i = 0; i < 6; i++){
            dishes[i].setFont(dishFont);
            for(int j = 0; j < 3; j++){
                specialties[i][j].setFont(specFont);
            }
            spicyDegree[i].setFont(spicFont);
        }
        spiciness.setFont(dishFont);

        for(int i = 0; i < 4; i++){
            addOnChooses[i].setFont(dishFont);
            addOnPrices[i].setFont(fTitle);
        }

        Font buFont = new Font("Times New Roman", Font.BOLD, 18);
        eatIn.setFont(buFont);
        eatIn.setBackground(new Color(254, 202, 87));
        eatIn.setOpaque(true);
        eatIn.setBorderPainted(false);
        takeAway.setBackground(new Color(247, 159, 31));
        takeAway.setOpaque(true);
        takeAway.setBorderPainted(false);
        takeAway.setFont(buFont);

        prePage.setFont(specFont);
        prePage.setBackground(new Color(250, 177, 160));
        prePage.setOpaque(true);
        prePage.setBorderPainted(false);

        Font resetFont = new Font("Times New Roman", Font.BOLD, 14);
        reset.setFont(resetFont);
        reset.setBackground(new Color(178, 190, 195));
        reset.setOpaque(true);
        reset.setBorderPainted(false);
    }

    public static void main(String[] args) {
        OrderMenu order = new OrderMenu();
    }
}