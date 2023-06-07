package clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

public class UserClient extends RestService {
    private final String CREATE_USER = "Account/v1/User";

    public UserClient() {
    }

    public <T> RestResponse postCreateUser(T body) throws JsonProcessingException {
        return post(CREATE_USER, ObjectMapperUtils.objectToJson(body));
    }

    public void deleteUser(String userId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("userId", userId);
        delete(CREATE_USER, queryParams);
    }
}
