package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.UpdateRequest;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class UpdateBoardSteps {

    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final UpdateRequest updateBoardRequest;
    private final Context context;

    @When("I update board name {string}")
    public void i_update_board_name(String newBoardName) {
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addPathParam("id", context.getBoards().get(CommonValues.BOARD_NAME));
        requestHandler.addQueryParam("name", newBoardName);

        responseHandler.setResponse(updateBoardRequest.update(requestHandler));
        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
