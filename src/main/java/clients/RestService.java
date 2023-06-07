package clients;

import io.restassured.specification.RequestSpecification;

public class APIClient {

    RequestSpecification request;
    public APIClient(String url) {

    }

    public RequestSpecification getRequest() {
        return request;
    }
}
