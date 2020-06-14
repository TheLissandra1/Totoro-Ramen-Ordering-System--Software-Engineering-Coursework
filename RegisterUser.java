import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class RegisterUser
{
	private String path = ".\\users";
	
	public RegisterUser(){
		
	}
	
	public boolean registerUserWriter(UserBean user,String newFilename)
	{
		ArrayList<String> userList = new ArrayList<String>();
		
		userList.add(user.toString());
		try{
			File writename = new File(path);
			if (!writename.exists()){
				writename.mkdirs();
			}
			writename = new File(path + "\\" + newFilename + ".txt");
			writename.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(writename));
			
			for (String s : userList){
				bw.write(s);
				bw.newLine();
				bw.flush();
			}
			bw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

}
