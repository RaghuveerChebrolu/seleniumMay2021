package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class lib {
	public static Properties property = new Properties();
	
	
	public static void readingConfigurationProprtyFile() throws IOException{
		FileInputStream objFileInput = new FileInputStream (new File(System.getProperty("user.dir")+"//src/test//ressources//configurationProperty.properties"));
		property.load(objFileInput);
		
		System.out.println(property.getProperty("browser"));
		System.out.println(property.getProperty("GmoOnloneURL_SIT"));
		
		
	}

}
