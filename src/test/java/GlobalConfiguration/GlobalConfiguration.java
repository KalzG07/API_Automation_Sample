package GlobalConfiguration;

import PropertiesLoader.PropertiesLoader;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class GlobalConfiguration {

    private final PropertiesLoader propertiesLoader = new PropertiesLoader();


    @BeforeTest(alwaysRun = true)
    @Parameters({"environment"})
    public void setupGlobal(String environment){
        propertiesLoader.loadProperties();
        propertiesLoader.setProperties("environment", environment);
    }
}
