package APIHandlerClasses.GETHandlers.GETAllCountriesDataAPI;

import APIHandlerClasses.GETHandlers.GETAllCountriesDataAPI.Response.GetAllCountriesResponse;
import BaseRequests.GetRequest;
import PropertiesLoader.PropertiesLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.Assert;

import java.util.HashMap;

import static ReportOutput.ReporterOutput.ReporterLog;

public class GETAllCountriesDataAPIHandler extends GetRequest {

    private GetAllCountriesResponse[] getAllCountriesResponse;
    private final PropertiesLoader propertiesLoader = new PropertiesLoader();
    private final Gson gsonResponse = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private String applicationUrl;
    private final String context = propertiesLoader.getProperty("application.context.rest");
    private final String apiVersion = propertiesLoader.getProperty("api.version.2");
    private final String endpoint = propertiesLoader.getProperty("endpoint.allCountries");

    public GETAllCountriesDataAPIHandler(String environment) {
        switch (environment.toLowerCase()) {
            case "prod":
                applicationUrl = propertiesLoader.getProperty("application.url.get.countries.prod");
                break;
            default:
                Assert.fail("Invalid environment selected: " + environment);
                break;
        }
    }


    public HashMap get(String accessToken) {
        String fullUrl = applicationUrl + context + apiVersion + endpoint;
        ReporterLog("Getting Region stats");
        ReporterLog("URL " + fullUrl);
        HashMap returnResponse = super.get("", fullUrl);

        try {
            getAllCountriesResponse = gsonResponse.fromJson(returnResponse.get("responseBody").toString(), GetAllCountriesResponse[].class);
        } catch (Exception e) {
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

        String response = gsonResponse.toJson(getAllCountriesResponse);
        returnResponse.put("prettyJson", response);
        log(returnResponse, "Get : Region stats by region name");
        return returnResponse;

    }

    public GetAllCountriesResponse[] getLatestRegionStatResponse() {
        return getAllCountriesResponse;
    }
}
