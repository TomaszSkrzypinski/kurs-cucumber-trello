package pl.akademiaqa.api.trello;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import pl.akademiaqa.handlers.api.RequestHandler;

import static io.restassured.RestAssured.given;

@RequiredArgsConstructor
public class UpdateRequest {

    private final BaseRequest baseRequest;

    public Response update(RequestHandler requestHandler) {

        return given()
                .spec(baseRequest.requestSetup(requestHandler.getQueryParams(), requestHandler.getPathParams()))
                .when()
                .put(requestHandler.getEndpoint() + "{id}")
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
