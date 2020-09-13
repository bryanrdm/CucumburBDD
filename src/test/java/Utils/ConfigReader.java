package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties configFile;

//    static {
//        //to access the config.properties file
//        FileInputStream fileInputStream = null;
//        try {
//            fileInputStream = new FileInputStream("config.properties");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        //to initialize properties object
//        configFile = new Properties();
//        try {
//            configFile.load(fileInputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
static{
    try{
        FileInputStream fileInputStream = new FileInputStream("config.properties");
        configFile = new Properties();
        configFile.load(fileInputStream);
        fileInputStream.close();

    }catch (IOException e){
        System.out.println("Failed to load propeties file...!");
        e.printStackTrace();
    }
}

    public static String getProperty(String key){
        return configFile.getProperty(key);
    }


}
