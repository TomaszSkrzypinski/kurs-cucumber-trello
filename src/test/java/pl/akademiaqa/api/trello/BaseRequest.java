package pl.akademiaqa.api.trello;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.akademiaqa.url.TrelloUrl;

import java.util.Map;

public class BaseRequest {

    public RequestSpecification requestSetup(Map<String, ?> queryParams){

        return new RequestSpecBuilder()
                .setBaseUri(TrelloUrl.BASE_URL)
                .setContentType(ContentType.JSON)
                .addQueryParams(queryParams)
                .build();
    }

    public RequestSpecification requestSetup(Map<String, ?> queryParams, Map<String, ?> pathParams) {

        return new RequestSpecBuilder()
                .setBaseUri(TrelloUrl.BASE_URL)
                .setContentType(ContentType.JSON)
                .addQueryParams(queryParams)
                .addPathParams(pathParams)
                .build();
    }
}
