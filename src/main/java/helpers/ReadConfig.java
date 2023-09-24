package helpers;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files



public class ReadConfig {
    public static void main(String[] args) {
        try {
            String sysPath = System.getProperty("user.dir");
            // File myObj = new File("C:\\Users\\zdrav\\Projects\\JQA_homeworks\\ZornitsaStefanova\\java-api-demo\\src\\main\\java\\config\\config.json");
            // System.out.println(sysPath);
            File myObj = new File(sysPath + "\\src\\main\\java\\config\\config.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
