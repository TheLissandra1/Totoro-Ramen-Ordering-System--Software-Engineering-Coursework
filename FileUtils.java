import java.io.*;

/**
 * Title : FileUtils.java
 * Description: This class aims to read file
 * @author Zhaoxiao Su
 * @version 1.0
 */
public class FileUtils {

    // read file data
    public static String readFile(String filePath, String encoding) {
        String fileStr="";
        try {
            File file=new File(filePath);
            BufferedReader bufferedReader=new BufferedReader(
                    new InputStreamReader(new FileInputStream(file),encoding));
            StringBuffer tempFile=new StringBuffer();
            String line=bufferedReader.readLine();
            while( line != null) {
                tempFile.append(line).append("\n");
                line=bufferedReader.readLine();
            }
            fileStr=new String(tempFile);

            bufferedReader.close();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
        return fileStr;
    }

}
