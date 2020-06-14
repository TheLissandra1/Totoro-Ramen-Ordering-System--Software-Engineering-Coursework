/**
 * @Title:        Menu.java
 * @Description:  This file is mainly used to save menu and supports to change the menu.
 * @author:       Rui Wang
 * @version:      3.0
 */

import java.io.*;

public class Menu{

    // Use "Menu.txt" to store the content of menu.
    String menuFile = "Menu.txt";

    // Dishes for fixed price.
    String mainPrice;
    String[][] dishes = new String[6][4];

    // Spiciness
    String[] spicy = new String[7];

    String[][] addOns = new String[4][2];

    /**
     * the Constructor
     */
    public Menu(){
        this.readMenu();
    }

    /**
     * This method is used to read the content of menu from the Menu.txt file.
     */
    public void readMenu(){
        System.out.println("************************************************************");
        System.out.println("Following is the content of Menu.");
        System.out.println("************************************************************");
        try {
            File myFile = new File(menuFile);
            FileReader meReader = new FileReader(myFile);
            BufferedReader bufferedReader = new BufferedReader(meReader);

            mainPrice = bufferedReader.readLine();
            System.out.println(mainPrice);

            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 4; j++){
                dishes[i][j] = bufferedReader.readLine();
                System.out.println(dishes[i][j]);
                }
            }
            for(int i = 0; i < 7; i++){
                spicy[i] = bufferedReader.readLine();
                System.out.println(spicy[i]);
            }
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 2; j++){
                    addOns[i][j] = bufferedReader.readLine();
                    System.out.println(addOns[i][j]);
                }
            }

            bufferedReader.close();
            meReader.close();
        } catch (Exception e) {
            System.out.println("Error occured");
            System.exit(1);
        }
    }

    /**
     * This method is used to write the new content of the menu into the Menu.txt file.
     */
    public void writeMenu(){

        System.out.println("*****************************************************************");
        System.out.println("Following is the content of Menu after being changed by manager.");
        System.out.println("*****************************************************************");

        try {
            File myFile = new File(menuFile);
            FileWriter fileWriter = new FileWriter(myFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(mainPrice);
            bufferedWriter.newLine();
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 4; j++){
                bufferedWriter.write(dishes[i][j]);
                bufferedWriter.newLine();
                }
            }
            for(int i = 0; i < 7; i++){
                bufferedWriter.write(spicy[i]);
                bufferedWriter.newLine();
            }
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 2; j++){
                    bufferedWriter.write(addOns[i][j]);
                    bufferedWriter.newLine();
                }
            }
            
            bufferedWriter.close();
            fileWriter.close();

            System.out.println();
            System.out.println("*****************************************************************");
            System.out.println("The menu has been changed successfully!");
            System.out.println("*****************************************************************");
            
        } catch (Exception e) {
            System.out.println("Error occured");
            System.exit(1);
        }
    }

    // public static void main(String[] args) {
    //     new Menu();
    // }
}