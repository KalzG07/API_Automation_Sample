package BaseRequests.BaseRequest;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static Util.ReportOutput.ReporterOutput.ReporterLog;

public class BaseRequest {
    private OkHttpClient.Builder requestBuilder = new OkHttpClient.Builder();
    private final OkHttpClient client = requestBuilder
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build();

    private HashMap<String, Object> returnResponse = new HashMap<>();
    private Request request;
    private String apiMediaType = "application/json";


    protected HashMap<String, Object> buildBaseRequest(String requestType, String accessToken, String uri) {
        return buildBaseRequest(requestType, accessToken, uri, "");
    }

    protected HashMap<String, Object> buildBaseRequest(String requestType, String accessToken, String uri, String jsonBody) {

        switch (requestType) {
            case "get":
                request = getRequest(uri, accessToken);
                break;
            case "post":
                request = postRequestBasicAuth(uri, accessToken, jsonBody);
                break;
            case "post1":
                request = postRequestBearerAuth(uri,accessToken,jsonBody);
                break;
            case "put":
                request = putRequest(uri, accessToken, jsonBody);
                break;
            case "delete":
                if (jsonBody.equals(""))
                    request = deleteRequest(uri, accessToken);
                else
                    request = deleteRequest(uri, accessToken, jsonBody);
                break;
        }
        System.out.println("==================================================================================");
        executeApi();
        return returnResponse;
    }

    private void executeApi() {
        try {
            Response response = client.newCall(request).execute();
            int responseCode = response.code();
            assert response.body() != null;
            String responseBodyString = response.body().string();
            returnResponse.put("responseCode", responseCode);
            returnResponse.put("responseBody", responseBodyString);
            returnResponse.put("request", request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Request getRequest(String uri, String accessToken) {
        return new Request.Builder()
                .url(uri)
                .header("Connection", "close")
                .get()
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("content-type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
    }

    private Request deleteRequest(String uri, String accessToken) {
        return new Request.Builder()
                .url(uri)
                .header("Connection", "close")
                .delete(null)
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("content-type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
    }

    private Request deleteRequest(String uri, String accessToken, String jsonBody) {
        MediaType mediaType = MediaType.parse(apiMediaType);
        RequestBody body = RequestBody.create(mediaType, jsonBody);
        return new Request.Builder()
                .url(uri)
                .header("Connection", "close")
                .delete(body)
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("content-type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
    }

    private Request postRequestBasicAuth(String uri, String accessToken, String jsonBody) {
        MediaType mediaType = MediaType.parse(apiMediaType);
        RequestBody body = RequestBody.create(mediaType, jsonBody);
        return new Request.Builder()
                .url(uri)
                .header("Connection", "close")
                .post(body)
                .addHeader("authorization", "Basic " + accessToken)
                .addHeader("content-type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
    }

    private Request postRequestBearerAuth(String uri, String accessToken, String jsonBody) {
        MediaType mediaType = MediaType.parse(apiMediaType);
        RequestBody body = RequestBody.create(mediaType, jsonBody);
        return new Request.Builder()
                .url(uri)
                .header("Connection", "close")
                .post(body)
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("content-type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
    }


    private Request putRequest(String uri, String accessToken, String jsonBody) {
        MediaType mediaType = MediaType.parse(apiMediaType);
        RequestBody body = RequestBody.create(mediaType, jsonBody);
        return new Request.Builder()
                .url(uri)
                .header("Connection", "close")
                .put(body)
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("content-type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
    }

    public void log(HashMap response, String apiDescription) {
        ReporterLog("==================================================================================");
        try {
            ReporterLog(response.get("request").toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        ReporterLog("==================================================================================");
        ReporterLog("==================================================================================");
        try {
            ReporterLog("Response code = " + response.get("responseCode").toString()); // Converts int code to string / Logs code
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        ReporterLog("==================================================================================");
        if (response.get("prettyJson") == null) // Checks if pretty print has been set
            try {
                ReporterLog(apiDescription + " " + response.get("responseBody").toString()); // Logs String body
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        else
            ReporterLog(apiDescription + " " + response.get("prettyJson").toString()); // Logs String body
        ReporterLog("==================================================================================");
    }

}
