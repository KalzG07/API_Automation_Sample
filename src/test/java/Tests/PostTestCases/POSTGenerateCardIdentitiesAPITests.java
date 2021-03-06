package Tests.PostTestCases;

import APIHandlerClasses.POSTHandlers.GenerateCardIdentifiesAPI.GenerateCardIdentifiesAPIHandler;
import APIHandlerClasses.POSTHandlers.GenerateSessionKeyAPI.POSTGenerateSessionKeyAPIHandler;
import Configurations.GlobalConfiguration.GlobalConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

public class POSTGenerateCardIdentitiesAPITests extends GlobalConfiguration {

    private String environment;
    private String accessToken = "RlptQWl6MEt4bXE3SEJkVzJzYkxod0pGSVBvOUlLdTBENFpjNVhSMGoxeUlMR0VNRjg6bjZGNmluZU93VU1BMWhjS0twM2Z1dzVRd1I4UjhHaDBuaE4wY3FyN3djMEhaR1JPbzhLcFNxY3dERlpBM1Y4TDQ=";

    @BeforeTest(alwaysRun = true)
    @Parameters({"environment"})
    private void setup(String environment) {
        this.environment = environment;
    }

    public String getMerchSessionKey() {
        POSTGenerateSessionKeyAPIHandler postGenerateSessionKeyAPIHandler = new POSTGenerateSessionKeyAPIHandler(environment);
        postGenerateSessionKeyAPIHandler.getGenerateSessionKeyRequest().setVendorName("sagepaydemo");
        System.out.println(postGenerateSessionKeyAPIHandler.getMerchantSessionKey(accessToken));
        return postGenerateSessionKeyAPIHandler.getMerchantSessionKey(accessToken);
    }

    @Test
    public void execute_Generate_Session_Key_API_Test() {
        String merchKey = getMerchSessionKey();
        GenerateCardIdentifiesAPIHandler generateCardIdentifiesAPIHandler = new GenerateCardIdentifiesAPIHandler(environment);
        generateCardIdentifiesAPIHandler.getGenerateCardIdentifiesRequest().getCardDetails().setCardholderName("Card Holder");
        generateCardIdentifiesAPIHandler.getGenerateCardIdentifiesRequest().getCardDetails().setCardNumber("4929000000006");
        generateCardIdentifiesAPIHandler.getGenerateCardIdentifiesRequest().getCardDetails().setExpiryDate("1120");
        generateCardIdentifiesAPIHandler.getGenerateCardIdentifiesRequest().getCardDetails().setSecurityCode("123");

        HashMap response = generateCardIdentifiesAPIHandler.post(merchKey);
        Assert.assertEquals(response.get("responseCode").toString(), "201");
    }
}
