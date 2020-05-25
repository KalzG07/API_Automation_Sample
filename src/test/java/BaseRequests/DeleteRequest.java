package BaseRequests;

import BaseRequests.BaseRequest.BaseRequest;

import java.util.HashMap;

public class DeleteRequest extends BaseRequest {

    public HashMap delete(String accessToken, String uri){
        return buildBaseRequest("delete", accessToken, uri);
    }
}
