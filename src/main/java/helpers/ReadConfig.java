package helpers;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class ReadConfig {
    StringBuilder dataFile = new StringBuilder();
    private String jsonStr = null;

    public JSONObject readConfig() throws FileNotFoundException {
        String sysPath = System.getProperty("user.dir");
        File myObj = new File(sysPath + "\\src\\main\\java\\config\\config.json");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            //create string which should contains the data from the file
            dataFile.append(data);

            System.out.println(dataFile);
        }
        JSONObject jsonObject = new JSONObject(dataFile.toString());
        return jsonObject;
    }


}
