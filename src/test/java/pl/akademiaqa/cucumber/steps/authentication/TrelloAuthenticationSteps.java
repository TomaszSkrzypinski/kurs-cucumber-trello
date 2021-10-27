package pl.akademiaqa.cucumber.steps.authentication;

import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.trello.TrelloAuthentication;

@RequiredArgsConstructor
public class TrelloAuthenticationSteps {

    private final TrelloAuthentication trelloAuthentication;
    private final RequestHandler requestHandler;

    @Given("I am authenticated to Trello")
    public void i_am_authenticated_to_trello() {

        //dodać klucz i token
        requestHandler.addQueryParam("key", trelloAuthentication.getKey());
        requestHandler.addQueryParam("token", trelloAuthentication.getToken());
    }

    @Given("I am not authenticated to Trello")
    public void i_am_not_authenticated_to_trello() {
        requestHandler.addQueryParam("key", "1234");
        requestHandler.addQueryParam("token", "1234");
    }
}
