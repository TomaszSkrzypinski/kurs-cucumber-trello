package pl.akademiaqa.handlers.api;

import io.restassured.response.Response;
import lombok.Data;

@Data
public class ResponseHandler {

    private Response response;

    public String getId() {
        return response.getBody().jsonPath().getString("id");
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }
}