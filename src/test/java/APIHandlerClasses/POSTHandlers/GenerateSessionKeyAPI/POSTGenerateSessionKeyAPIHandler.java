package APIHandlerClasses.POSTHandlers.GenerateSessionKeyAPI;

import BaseRequests.PostRequest;
import Configurations.PropertiesLoader.PropertiesLoader;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.Assert;
import static Util.ReportOutput.ReporterOutput.ReporterLog;

import java.util.HashMap;

public class POSTGenerateSessionKeyAPIHandler extends PostRequest {

    private PropertiesLoader propertiesLoader = new PropertiesLoader();
    private GenerateSessionKeyRequest generateSessionKeyRequest = new GenerateSessionKeyRequest();
    private GenerateSessionKeyReponse generateSessionKeyReponse = new GenerateSessionKeyReponse();
    private Gson gsonRequest = BuildGson();
    private Gson gsonresponse = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private String applicationUrl;
    private String context = propertiesLoader.getProperty("application.context.api");
    private String apiVersion = propertiesLoader.getProperty("api.version.1");
    private String endpoint = propertiesLoader.getProperty("endpoint.sessionKeys");

    public POSTGenerateSessionKeyAPIHandler(String environment){
        switch (environment.toLowerCase()){
            case "prod":
                applicationUrl = propertiesLoader.getProperty("application.url.post.prod");
                break;
            default:
                Assert.fail("Invalid environment selected: " + environment);
                break;
        }

        generateSessionKeyRequest.setVendorName("");
    }

    public HashMap post(String accessToken){
        String fullUrl = applicationUrl + context + apiVersion + endpoint;

        String jsonBody = gsonRequest.toJson(generateSessionKeyRequest);
        ReporterLog("Posting Generate Session key body" + jsonBody);
        ReporterLog("URL " + fullUrl);
        HashMap returnResponse = super.post(accessToken,fullUrl,jsonBody);

        try{
            generateSessionKeyReponse = gsonresponse.fromJson(returnResponse.get("responseBody").toString(), GenerateSessionKeyReponse.class);
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
        
        String prettyJson = gsonresponse.toJson(generateSessionKeyReponse);
        returnResponse.put("prettyJson", prettyJson);
        super.log(returnResponse, "Posting generate Session Key");
        return returnResponse;
    }
    
    public GenerateSessionKeyRequest getGenerateSessionKeyRequest(){
        return generateSessionKeyRequest;
    }
    
    public GenerateSessionKeyReponse getGenerateSessionKeyResponse(){
        return generateSessionKeyReponse;
    }
    
    
    private Gson BuildGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fa) {
                
                try{
                    if (fa.getName().equals("VendorName") && generateSessionKeyRequest.getVendorName().equals("exclude")){
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

    public String getMerchantSessionKey(String accessToken){
        HashMap response = post(accessToken);
        GenerateSessionKeyReponse generateSessionKeyReponse = gsonresponse.fromJson(response.get("responseBody").toString(), GenerateSessionKeyReponse.class);
        return generateSessionKeyReponse.getMerchantSessionKey();
    }
}
