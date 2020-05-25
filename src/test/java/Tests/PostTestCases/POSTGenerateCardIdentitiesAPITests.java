package Tests.PostTestCases;

import APIHandlerClasses.POSTHandlers.GenerateCardIdentifiesAPI.GenerateCardIdentifiesAPIHandler;
import APIHandlerClasses.POSTHandlers.GenerateSessionKeyAPI.GenerateSessionKeyRequest;
import APIHandlerClasses.POSTHandlers.GenerateSessionKeyAPI.POSTGenerateSessionKeyAPIHandler;
import GlobalConfiguration.GlobalConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

public class POSTGenerateCardIdentitiesAPITests extends GlobalConfiguration {

    private String environment;
    private String accessToken ="RlptQWl6MEt4bXE3SEJkVzJzYkxod0pGSVBvOUlLdTBENFpjNVhSMGoxeUlMR0VNRjg6bjZGNmluZU93VU1BMWhjS0twM2Z1dzVRd1I4UjhHaDBuaE4wY3FyN3djMEhaR1JPbzhLcFNxY3dERlpBM1Y4TDQ=";

    @BeforeTest(alwaysRun = true)
    @Parameters({"environment"})
    private void setup(String environment){
        this.environment = environment;
    }

    @Test
    public void execute_Generate_Session_Key_API_Test(){

        POSTGenerateSessionKeyAPIHandler postGenerateSessionKeyAPIHandler = new POSTGenerateSessionKeyAPIHandler(environment);
        GenerateSessionKeyRequest generateSessionKeyRequest = postGenerateSessionKeyAPIHandler.getGenerateSessionKeyRequest();

        generateSessionKeyRequest.setVendorName("sagepaydemo");

        HashMap response = postGenerateSessionKeyAPIHandler.post(accessToken);
        Assert.assertEquals(response.get("responseCode").toString(), "201");

        GenerateCardIdentifiesAPIHandler generateCardIdentifiesAPIHandler = new GenerateCardIdentifiesAPIHandler(environment);
        generateCardIdentifiesAPIHandler.getGenerateCardIdentifiesRequest().getCardDetails().setCardholderName("Card Holder");
        generateCardIdentifiesAPIHandler.getGenerateCardIdentifiesRequest().getCardDetails().setCardNumber("4929000000006");
        generateCardIdentifiesAPIHandler.getGenerateCardIdentifiesRequest().getCardDetails().setExpiryDate("1120");
        generateCardIdentifiesAPIHandler.getGenerateCardIdentifiesRequest().getCardDetails().setSecurityCode("123");

        HashMap responseCardIdentification = generateCardIdentifiesAPIHandler.post(postGenerateSessionKeyAPIHandler.getGenerateSessionKeyReponse().getMerchantSessionKey());
        Assert.assertEquals(responseCardIdentification.get("ResponseCode").toString(), "201");
    }
}
