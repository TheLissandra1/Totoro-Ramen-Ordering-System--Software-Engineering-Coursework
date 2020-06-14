/**
 * @Title:        ModifyMenu.java
 * @Description:  This file is mainly used to modify the menu details for the manager of the restaurant.
 *                And this file can only implement the page 8_1.
 *                The background was added in this version.
 *                Some little changes of the interface were conducted.
 * @author:       Rui Wang
 * @version:      4.0
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font.*;
import java.io.*;
import java.awt.Color;

public class ModifyMenu extends Interface implements ActionListener{

    // Add some basic items for the interface
    private JLabel fixedPrice, add_Ons, addPrice, funcTitle;
    JTextField nodPrice;
    JTextField[] addOnChooses = new JTextField[4];
    JTextField[] addOnPrices = new JTextField[4];
    JTextField[][] specialties = new JTextField[6][3];
    JTextField[] dishes = new JTextField[6];
    JTextField[] spicyDegree = new JTextField[6];
    JTextField spiciness; 
    JButton confirm;
    JButton prePage;
    ImageIcon background;
    JPanel imagePanel;
    // int isChanged = 0;

    // Get menu for the restaurant.
    Menu myMenu = new Menu();
    HelperControl newControl = new HelperControl();

    /**
     * Set the constructor for the OrderMenu object.
     */
    public ModifyMenu(){
        super.showFrame(6);

        this.createComponents();
        this.setComponentsLayout();
        this.setAppearance();
    }

    /**
     * This method is used to get the action and set the reaction for textfields.
     */
    public void actionPerformed(ActionEvent e){

        JButton eventSource = (JButton) e.getSource();

        if(eventSource.equals(confirm)){
            resetMenu();
            myMenu.writeMenu();
            this.setVisible(false);
            newControl.skipManagerOptions();
        }else if(eventSource.equals(prePage)){
            this.setVisible(false);
            newControl.skipManagerOptions();
        }
        
    }

    /**
     * This method is used to get the new content from the textfields to reset the menu.
     */
    public void resetMenu(){
        myMenu.mainPrice = nodPrice.getText();
        System.out.println("******************************************************************");
        System.out.println("The price has been changed into: " + myMenu.mainPrice);

        myMenu.spicy[0] = spiciness.getText();
        System.out.println("The content of the current menu has been changed into: " + myMenu.spicy[0]);

        System.out.println("******************************************************************");
        for(int i = 0; i < 6; i++){
            myMenu.dishes[i][0] = dishes[i].getText();
            System.out.println("The content of the current menu has been changed into: " + myMenu.dishes[i][0]);

            for(int j = 0; j < 3; j++){
                myMenu.dishes[i][j+1] = specialties[i][j].getText();
                System.out.println("The content of the current menu has been changed into: " + myMenu.dishes[i][j+1]);
            }

            myMenu.spicy[i+1] = spicyDegree[i].getText();
                System.out.println("The content of the current menu has been changed into: " + myMenu.spicy[i+1]);
        }

        for(int i = 0; i < 4; i++){
            myMenu.addOns[i][0] = addOnChooses[i].getText();
            System.out.println("The content of the current menu has been changed into: " + myMenu.addOns[i][0]);
            myMenu.addOns[i][1] = addOnPrices[i].getText();
            System.out.println("The content of the current menu has been changed into: " + myMenu.addOns[i][1]);
        }
    }

    /**
     * This method is used to create components for the Menu page.
     */
    public void createComponents(){

        funcTitle = new JLabel("Modify your Menu!");
        fixedPrice = new JLabel("Fixed Price");
        
        nodPrice = new JTextField("9.9");

        add_Ons = new JLabel("Add-Ons");
        addPrice = new JLabel("Prices");

        // the Fixed Price part.
        spiciness = new JTextField(myMenu.spicy[0]);
        for(int i = 0; i < 6; i++){
            dishes[i] = new JTextField(myMenu.dishes[i][0]);
            for(int j = 0; j < 3; j++){
                specialties[i][j] = new JTextField(myMenu.dishes[i][j+1]);
            }
            spicyDegree[i] = new JTextField(myMenu.spicy[i+1]);
        }

        // the Add-Ons part.
        for(int i = 0; i < 4; i++){
            addOnChooses[i] = new JTextField(myMenu.addOns[i][0]);
            addOnPrices[i] = new JTextField(myMenu.addOns[i][1]);
        }

        confirm = new JButton("Done");
        confirm.addActionListener(this);

        prePage = new JButton("Previous");
        prePage.addActionListener(this);
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
            dishes[i].setBounds(235, 210+(i*75), 120, 50);
            super.getContentPane().add(dishes[i]);
            if(i<3){
                for(int j = 0; j < 3; j++){
                    specialties[i][j].setBounds(360+(j*140), 210+(i*75), 120, 50);
                    super.getContentPane().add(specialties[i][j]);
                }
            }else{
                for(int j = 0; j < 2; j++){
                    specialties[i][j].setBounds(360+(j*140), 210+(i*75), 120, 50);
                    super.getContentPane().add(specialties[i][j]);
                }
            }
            spicyDegree[i].setBounds(360+(i*70), 660, 75, 50); 
            super.getContentPane().add(spicyDegree[i]);
        }
        spiciness.setBounds(235, 660, 120, 50);
        super.getContentPane().add(spiciness);

        for(int i = 0; i < 4; i++){
            addOnChooses[i].setBounds(900, 210+(i*75), 200, 50);
            super.getContentPane().add(addOnChooses[i]);
            addOnPrices[i].setBounds(1150, 210+(i*75), 40, 50);
            super.getContentPane().add(addOnPrices[i]);
        }

        confirm.setBounds(850, 600, 200, 50);
        super.getContentPane().add(confirm);
        prePage.setBounds(1100, 700, 150, 35);
        super.getContentPane().add(prePage);
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
        confirm.setFont(buFont);
        confirm.setBackground(new Color(243, 156, 18));
        confirm.setOpaque(true);
        confirm.setBorderPainted(false);

        prePage.setFont(specFont);
        prePage.setBackground(new Color(250, 177, 160));
        prePage.setOpaque(true);
        prePage.setBorderPainted(false);
    }

    public static void main(String[] args) {
        ModifyMenu myModify = new ModifyMenu();
    }
}