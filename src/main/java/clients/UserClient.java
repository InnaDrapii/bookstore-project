package clients;

import utils.ObjectMapperUtils;

public class UserClient extends RestService {
    private final String CREATE_USER = "Account/v1/User";

    public <T> RestResponse postCreateUser(T body) {
        return post(CREATE_USER, ObjectMapperUtils.objectToJson(body));
    }

    public RestResponse deleteUser(String userId, String token) {
        return delete(CREATE_USER, userId, token);
    }
}
