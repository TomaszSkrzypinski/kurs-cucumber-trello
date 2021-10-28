package pl.akademiaqa.cucumber.steps.card;

import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.CreateRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class CreateCardSteps {

    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final CreateRequest createRequest;
    private final Context context;

    @Given("I create card {string} on {string}")
    public void i_create_card_on(String cardName, String listName) {
        String listId = context.getLists().get(listName);

        requestHandler.setEndpoint(TrelloUrl.CARDS);
        requestHandler.addQueryParam("idList", listId);
        requestHandler.addQueryParam("name", cardName);

        responseHandler.setResponse(createRequest.create(requestHandler));

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        context.addCards(cardName, responseHandler.getId());
    }
}
