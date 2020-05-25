package Tests.GetTestCases;

import APIHandlerClasses.GETHandlers.GETLatestRegionStatsAPI.GETRegionStatisticsAPIHandler;
import APIHandlerClasses.GETHandlers.GETLatestRegionStatsAPI.Response.GetCovidSummaryByregionResponse;
import Configurations.GlobalConfiguration.GlobalConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

public class GETAllCOVIDSummaryByRegionAPITest extends GlobalConfiguration {

    private String environment;

    @BeforeTest(alwaysRun = true)
    @Parameters({"environment"})
    private void setup(String environment){
        this.environment = environment;
    }

    @Test
    public void execute_Get_Covid_statistics_By_Region_Name(){
        GETRegionStatisticsAPIHandler getRegionStatisticsAPIHandler = new GETRegionStatisticsAPIHandler(environment);
        HashMap response = getRegionStatisticsAPIHandler.get("","new_york");
        Assert.assertEquals(response.get("responseCode").toString(), "200");
        GetCovidSummaryByregionResponse getCovidSummaryByregionResponse = getRegionStatisticsAPIHandler.getLatestRegionStatResponse();
        Assert.assertEquals(getCovidSummaryByregionResponse.getStatus().toString(), "200");
    }
}
