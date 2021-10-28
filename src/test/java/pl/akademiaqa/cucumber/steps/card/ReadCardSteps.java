package pl.akademiaqa.cucumber.steps.card;

import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.ReadRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class ReadCardSteps {

    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final ReadRequest readRequest;
    private final Context context;

    @Then("I see {string} on {string} list")
    public void i_see_on_list(String cardName, String listName) {
        String cardId = context.getCards().get(cardName);
        String listId = context.getLists().get(listName);

        requestHandler.setEndpoint(TrelloUrl.CARDS);
        requestHandler.addPathParam("id", cardId);

        responseHandler.setResponse(readRequest.read(requestHandler));

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(responseHandler.getResponse().getBody().jsonPath().getString("idList")).isEqualTo(listId);

    }

}
