package Tests.GetTestCases;

import APIHandlerClasses.GETHandlers.GETAllCountriesDataAPI.GETAllCountriesDataAPIHandler;
import APIHandlerClasses.GETHandlers.GETLatestRegionStatsAPI.GETRegionStatisticsAPIHandler;
import GlobalConfiguration.GlobalConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

public class GETAllCountriesDataAPITest extends GlobalConfiguration {

    private String environment;

    @BeforeTest(alwaysRun = true)
    @Parameters({"environment"})
    private void setup(String environment){
        this.environment = environment;
    }

    @Test
    public void execute_Get_Covid_statistics_By_Region_Name(){

        GETAllCountriesDataAPIHandler getAllCountriesDataAPIHandler = new GETAllCountriesDataAPIHandler(environment);

        HashMap response = getAllCountriesDataAPIHandler.get("");
        Assert.assertEquals(response.get("responseCode").toString(), "200");

    }
}
