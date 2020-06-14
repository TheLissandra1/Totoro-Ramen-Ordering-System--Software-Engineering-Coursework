/**
 * @Title:        OrderRW.java
 * @Description:  This file is mainly used to write and read users' order, especially in a fixed format.
 * 				  This file acts as an entity in the whole ordering system.
 * @author:       Rui Wang, Xinbei Li
 * @version:      1.0
 */

import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OrderRW {

	Menu myMenu = new Menu();
	String fileName ="Order.txt"; 
	String[] myOrder = new String[21]; 

	private String[] line = new String[40]; //Store strings which read from the file

	public OrderRW(){
		this.initOrder();
	}

	// This method is used to get the menu ordering and record in a String array.
    public void initOrder(){

        myOrder[0] = "Fixed Price";
        myOrder[1] = myMenu.mainPrice.toString();
        for(int i = 2; i < 8; i++){
            myOrder[i] = myMenu.dishes[i-2][0];
        }
        myOrder[8] = myMenu.spicy[0];

        myOrder[9] = "Add-ons";
        myOrder[10] = "0";
        myOrder[11] = myMenu.addOns[0][0];
        myOrder[12] = "0";
        myOrder[13] = myMenu.addOns[1][0];
        myOrder[14] = "0";
        myOrder[15] = myMenu.addOns[2][0];
        myOrder[16] = "0";
        myOrder[17] = myMenu.addOns[3][0];
        myOrder[18] = "0";
        myOrder[19] = "Total";
        myOrder[20] = "0";
    }

	// This method is used to write the ordering menu into the "Order.txt" file.
    public void writeOrder(){
        try{
            File myFile = new File(fileName);
            FileWriter ordWriter = new FileWriter(myFile);
            BufferedWriter bufferedWriter = new BufferedWriter(ordWriter);
          
            
            for(int i = 0; i < 21; i++){
                bufferedWriter.write(myOrder[i]);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        }
        catch(IOException e){
            System.out.println("Error occured");
            System.exit(1);
        }
	}
	
	public String[] readFile() {
		//read the order information from Order.txt which is created in Menu interface
		try {
			
						
			File myFile = new File(fileName);
			FileReader fileReader = new FileReader(myFile);
			BufferedReader reader = new BufferedReader(fileReader);
					
			int j = 0;
			//Read only one line at a time and store the string into one element of line[]
			while((line[j] = reader.readLine())!=null) {
				j++;
			}
			reader.close();
			fileReader.close();
			} catch (Exception ex) {
			//Exception Handling
			ex.printStackTrace();
			}
	  return line;
	}
	public void writePay(String dateWrite,String line[],String date,String orderFile) {
		//write the order into total order file and current date file
				try{   
				    File file =new File(date); //current date file
				    File file1 =new File(orderFile); //total order file
				    FileWriter fileWritter = null;
				    FileWriter fileWritter1 = null;
				    if(!file.exists()){        	
				    	file.createNewFile();
				    	fileWritter=new FileWriter(file);
				    	
				    }   
				    else {
				      //"true" in constructor means writing order after the end of the previous content
				      fileWritter=new FileWriter(file,true);
				    }
				    if(!file1.exists()){        	
				    	file1.createNewFile();
				    	fileWritter1=new FileWriter(file1);
				    	
				    }   
				    else {
				      fileWritter1=new FileWriter(file1,true);
				    }
				    
				    BufferedWriter bufferedWriter = new BufferedWriter(fileWritter);
				    BufferedWriter bufferedWriter1 = new BufferedWriter(fileWritter1);
				    for(int i=0;i<19;i++) {
				    	bufferedWriter.write(line[i]+" ");  
				    	bufferedWriter.newLine();
				    	bufferedWriter1.write(line[i]+" ");  
				    	bufferedWriter1.newLine();
				    }
				    bufferedWriter.write(dateWrite);  
				    bufferedWriter.newLine();
				    bufferedWriter.newLine();
				    bufferedWriter.close(); 
				    bufferedWriter1.write(dateWrite);  
				    bufferedWriter1.newLine();
				    bufferedWriter1.newLine();
				    bufferedWriter1.close(); 
				    }catch(IOException ex){         
				    ex.printStackTrace();     
				    }
				
	}
}
