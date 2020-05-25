package APIHandlerClasses.POSTHandlers.GenerateCardIdentifiesAPI;

import APIHandlerClasses.POSTHandlers.GenerateCardIdentifiesAPI.RequestClasses.CardDetails;
import APIHandlerClasses.POSTHandlers.GenerateCardIdentifiesAPI.RequestClasses.GenerateCardIdentifiesRequest;
import APIHandlerClasses.POSTHandlers.GenerateCardIdentifiesAPI.ResponseClasses.GenerateCardIdentifiesResponse;
import BaseRequests.PostRequest1;
import PropertiesLoader.PropertiesLoader;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.Assert;

import java.util.HashMap;

import static ReportOutput.ReporterOutput.ReporterLog;

public class GenerateCardIdentifiesAPIHandler extends PostRequest1 {
    private PropertiesLoader propertiesLoader = new PropertiesLoader();
    private GenerateCardIdentifiesRequest generateCardIdentifiesRequest = new GenerateCardIdentifiesRequest();
    private GenerateCardIdentifiesResponse generateCardIdentifiesResponse = new GenerateCardIdentifiesResponse();
    private Gson gsonRequest = BuildGson();
    private Gson gsonresponse = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private String applicationUrl;
    private String context = propertiesLoader.getProperty("application.context.api");
    private String apiVersion = propertiesLoader.getProperty("api.version.1");
    private String endpoint = propertiesLoader.getProperty("endpoint.cardIdentifiers");

    public GenerateCardIdentifiesAPIHandler(String environment){
        switch (environment.toLowerCase()){
            case "prod":
                applicationUrl = propertiesLoader.getProperty("application.url.post.prod");
                break;
            default:
                Assert.fail("Invalid environment selected: " + environment);
                break;
        }

        CardDetails cardDetails = new CardDetails();
        cardDetails.setCardholderName("");
        cardDetails.setCardNumber("");
        cardDetails.setExpiryDate("");
        cardDetails.setSecurityCode("");
        generateCardIdentifiesRequest.setCardDetails(cardDetails);
    }

    public HashMap post(String accessToken){
        String fullUrl = applicationUrl + context + apiVersion + endpoint;

        String jsonBody = gsonRequest.toJson(generateCardIdentifiesRequest);
        ReporterLog("Posting Generate Session key body" + jsonBody);
        ReporterLog("URL " + fullUrl);
        HashMap returnResponse = super.post1(accessToken,fullUrl,jsonBody);

        try{
            generateCardIdentifiesResponse = gsonresponse.fromJson(returnResponse.get("responseBody").toString(), GenerateCardIdentifiesResponse.class);
        }
        catch (Exception e){
            ReporterLog("==================================================================================");
            ReporterLog("!!!SOMETHING WENT WRONG!!!");
            ReporterLog("==================================================================================");
            ReporterLog("response code: \n" + returnResponse.get("responseCode").toString());
            ReporterLog("request body: \n" + jsonBody);
            ReporterLog("==================================================================================");
            ReporterLog("response body: \n" + returnResponse.get("responseBody").toString());
            ReporterLog("ERROR MESSAGE: " + e.getMessage());
            ReporterLog("==================================================================================");
            Assert.fail();
        }

        String prettyJson = gsonresponse.toJson(generateCardIdentifiesResponse);
        returnResponse.put("prettyJson", prettyJson);
        super.log(returnResponse, "Posting generate Session Key");
        return returnResponse;
    }

    public GenerateCardIdentifiesRequest getGenerateCardIdentifiesRequest(){
        return generateCardIdentifiesRequest;
    }

    public GenerateCardIdentifiesResponse getGenerateCardIdentifiesResponse(){
        return generateCardIdentifiesResponse;
    }


    private Gson BuildGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fa) {

                try{
                    if (fa.getName().equals("VendorName") && generateCardIdentifiesRequest.getCardDetails().getCardholderName().equals("exclude")){
                        return true;
                    }
                    if (fa.getName().equals("VendorName") && generateCardIdentifiesRequest.getCardDetails().getCardNumber().equals("exclude")){
                        return true;
                    }
                    if (fa.getName().equals("VendorName") && generateCardIdentifiesRequest.getCardDetails().getExpiryDate().equals("exclude")){
                        return true;
                    }
                    if (fa.getName().equals("VendorName") && generateCardIdentifiesRequest.getCardDetails().getSecurityCode().equals("exclude")){
                        return true;
                    }
                }
                catch(NullPointerException e){
                    ReporterLog(e.getMessage());
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        });

        return gsonBuilder.serializeNulls().setPrettyPrinting().create();
    }
}
