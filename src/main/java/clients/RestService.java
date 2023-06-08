package clients;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.groovy.util.Maps;

import java.util.Arrays;

public class RestService {

    private final String baseUrl = "https://bookstore.toolsqa.com/";

    public RestService(){}

    private RequestSpecification getRequestSpecification() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setConfig(RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()));
        builder.addFilters(Arrays.asList(new ResponseLoggingFilter(), new RequestLoggingFilter()));
        return RestAssured.given().spec(builder.build());

    }
    private RequestSpecification getSpecificationWithHeaders() {
        return getRequestSpecification()
                .headers(Maps.of(
                        "Content-Type", ContentType.JSON,
                        "accept", ContentType.JSON
                ));
    }

    private RequestSpecification getSpecificationWithContentTypeAndToken(String token) {
        return getRequestSpecification()
                .headers(Maps.of(
                        "authorization", "Bearer " + token,
                        "Content-Type", ContentType.JSON,
                        "accept", ContentType.JSON
                ));
    }

    private RequestSpecification getSpecificationWithToken(String token) {
        return getRequestSpecification()
                .headers(Maps.of(
                        "authorization", "Bearer " + token,
                        "accept", ContentType.JSON
                ));
    }

    public RestResponse post(String url, String body){
        return new RestResponse(
                getSpecificationWithHeaders()
                        .body(body)
                        .post(baseUrl.concat(url))
        );
    }

    public RestResponse postWithToken(String url, String body, String token){
        return new RestResponse(
                getSpecificationWithContentTypeAndToken(token)
                        .body(body)
                        .post(baseUrl.concat(url))
        );
    }

    public RestResponse get(String url){
        return new RestResponse(
                getSpecificationWithHeaders()
                        .get(baseUrl.concat(url))
        );
    }

   public RestResponse delete(String url, String userId, String token){
        return new RestResponse(
                getSpecificationWithToken(token)
                        .delete(baseUrl.concat(url) + "/" + userId)
        );
   }
}
