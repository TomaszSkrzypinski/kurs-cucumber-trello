package pl.akademiaqa.cucumber.steps.card;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.UpdateRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class UpdateCardSteps {

    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;
    private final UpdateRequest updateRequest;

    @When("I move {string} to {string} list")
    public void i_move_to_list(String cardName, String listName) {
        String cardId = context.getCards().get(cardName);
        String listId = context.getLists().get(listName);

        requestHandler.setEndpoint(TrelloUrl.CARDS);
        requestHandler.addPathParam("id", cardId);
        requestHandler.addQueryParam("idList", listId);

        responseHandler.setResponse(updateRequest.update(requestHandler));

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
