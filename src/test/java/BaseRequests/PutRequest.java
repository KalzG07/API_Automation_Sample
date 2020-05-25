package BaseRequests;

import BaseRequests.BaseRequest.BaseRequest;

import java.util.HashMap;

public class PutRequest extends BaseRequest {

    public HashMap put(String accessToken, String uri, String jsonBody){
        return buildBaseRequest("put", accessToken, uri, jsonBody);
    }
}
