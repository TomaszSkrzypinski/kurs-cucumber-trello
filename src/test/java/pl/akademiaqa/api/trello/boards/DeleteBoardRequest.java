package pl.akademiaqa.api.trello.boards;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import pl.akademiaqa.handlers.api.RequestHandler;

import static io.restassured.RestAssured.given;

@RequiredArgsConstructor
public class DeleteBoardRequest {

    private final BaseRequest baseRequest;

    public Response deleteBoardRequest(RequestHandler requestHandler) {

        return given()
                .spec(baseRequest.requestSetup(requestHandler.getQueryParams(), requestHandler.getPathParams()))
                .when()
                .delete(requestHandler.getEndpoint() + "{id}")
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
