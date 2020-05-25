package APIHandlerClasses.GETHandlers.GETLatestRegionStatsAPI;

import APIHandlerClasses.GETHandlers.GETLatestRegionStatsAPI.Response.GetCovidSummaryByregionResponse;
import BaseRequests.GetRequest;
import Configurations.PropertiesLoader.PropertiesLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.Assert;

import java.util.HashMap;

import static Util.ReportOutput.ReporterOutput.ReporterLog;

public class GETRegionStatisticsAPIHandler extends GetRequest {

    GetCovidSummaryByregionResponse getCovidSummaryByregionResponse = new GetCovidSummaryByregionResponse();
    private final PropertiesLoader propertiesLoader = new PropertiesLoader();
    private final Gson gsonResponse = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private String applicationUrl;
    private final String context = propertiesLoader.getProperty("application.context.api");
    private final String apiVersion = propertiesLoader.getProperty("api.version.1");
    private final String endpoint = propertiesLoader.getProperty("endpoint.covidByRegion");

    public GETRegionStatisticsAPIHandler(String environment) {
        switch (environment.toLowerCase()){
            case "prod":
                applicationUrl = propertiesLoader.getProperty("application.url.get.covid.prod");
                break;
            default:
                Assert.fail("Invalid environment selected: " + environment);
                break;
        }
    }


    public HashMap get(String accessToken, String region){
        String fullUrl = applicationUrl + context + apiVersion + endpoint + "?region=" + region;
        ReporterLog("Getting Region stats");
        ReporterLog("URL " + fullUrl);
        HashMap returnResponse = super.get("", fullUrl);

        try {
            getCovidSummaryByregionResponse = gsonResponse.fromJson(returnResponse.get("responseBody").toString(), GetCovidSummaryByregionResponse.class);
        }
        catch (Exception e){
            ReporterLog("==================================================================================");
            ReporterLog("!!!SOMETHING WENT WRONG!!!");
            ReporterLog("==================================================================================");
            ReporterLog("response code: \n" + returnResponse.get("responseCode").toString());
            ReporterLog("response body: \n" + returnResponse.get("responseBody").toString());
            ReporterLog("==================================================================================");
            ReporterLog("ERROR MESSAGE: " + e.getMessage());
            ReporterLog("==================================================================================");
            Assert.fail();
        }

        String response = gsonResponse.toJson(getCovidSummaryByregionResponse);
        returnResponse.put("prettyJson", response);
        log(returnResponse, "Get : Region stats by region name");
        return returnResponse;

    }

    public GetCovidSummaryByregionResponse getLatestRegionStatResponse() {
        return getCovidSummaryByregionResponse;
    }
}
