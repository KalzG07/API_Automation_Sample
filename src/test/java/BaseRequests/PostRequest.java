package BaseRequests;

import BaseRequests.BaseRequest.BaseRequest;

import java.util.HashMap;

public class PostRequest extends BaseRequest {

    public HashMap post(String accessToken, String uri, String jsonBody){
        return buildBaseRequest("post", accessToken, uri, jsonBody);
    }
}
