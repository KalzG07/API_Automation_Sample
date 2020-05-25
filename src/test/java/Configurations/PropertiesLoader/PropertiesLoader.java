package Configurations.PropertiesLoader;

import Util.ReportOutput.ReporterOutput;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private static final Properties properties = new Properties();
    private ClassPathResource resources = new ClassPathResource("application.properties"); //Requires Spring-core dependencies in the POM
    private InputStream inputStream = null;


    /*This will load up the application.properties files*/

    public Properties loadProperties(){
        try{
            inputStream = resources.getInputStream();
            properties.load(inputStream);
        }
        catch (IOException e){
            ReporterOutput.ReporterLog(e.getMessage());
        }

        return properties;
    }

    /*Custom Property Setter*/
    public Properties setProperties(String key, String value){
        properties.setProperty(key,value);

        return properties;
    }

    /*Customer property Getter*/
    public String getProperty(String key){
        return properties.getProperty(key);
    }

    /**/
}
