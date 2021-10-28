package pl.akademiaqa.cucumber.steps.list;

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
public class CreateListSteps {

    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;
    private final CreateRequest createRequest;

    @Given("I create list {string} on {string}")
    public void i_create_list_on(String listName, String boardName) {
        String boardId = context.getBoards().get(boardName);

        requestHandler.setEndpoint(TrelloUrl.LISTS);
        requestHandler.addQueryParam("idBoard", boardId);
        requestHandler.addQueryParam("name", listName);

        responseHandler.setResponse(createRequest.create(requestHandler));

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        context.addLists(listName, responseHandler.getId());
    }
}
