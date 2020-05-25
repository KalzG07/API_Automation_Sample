package BaseRequests;

import BaseRequests.BaseRequest.BaseRequest;

import java.util.HashMap;

public class GetRequest extends BaseRequest {

    public HashMap get(String accessToken, String uri){
        return buildBaseRequest("get", accessToken, uri);
    }
}
