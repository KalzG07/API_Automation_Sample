package BaseRequests;

import BaseRequests.BaseRequest.BaseRequest;

import java.util.HashMap;

public class PostRequest1 extends BaseRequest {

    public HashMap post1(String accessToken, String uri, String jsonBody){
        return buildBaseRequest("post1", accessToken, uri, jsonBody);
    }
}
