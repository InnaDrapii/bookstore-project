package clients;

import utils.ObjectMapperUtils;

public class TokenClient extends RestService {
    private final String GENERATE_TOKEN = "Account/v1/GenerateToken";

    public <T> RestResponse postToken(T body) {
        return post(GENERATE_TOKEN, ObjectMapperUtils.objectToJson(body));
    }
}
