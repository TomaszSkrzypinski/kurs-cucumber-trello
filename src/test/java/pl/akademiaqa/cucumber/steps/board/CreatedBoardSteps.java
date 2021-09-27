package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.boards.CreateBoardRequest;
import pl.akademiaqa.api.trello.boards.ReadBoardRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class CreatedBoardSteps {

    private final CreateBoardRequest createBoardRequest;
    private final ReadBoardRequest readBoardRequest;
    private final RequestHandler requestHandler;

    private String boardId;

    @When("I create new board")
    public void i_create_new_board() {

        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addQueryParam("name", "THIS IS NEW BOARD");

        Response response = createBoardRequest.createBoard(requestHandler);
        boardId = response.getBody().jsonPath().getString("id");

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Then("I can read created board details")
    public void i_can_read_created_board_details() {

        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addPathParam("id", boardId);

        Response response = readBoardRequest.readBoard(requestHandler);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.getBody().jsonPath().getString("name")).isEqualTo("THIS IS NEW BOARD");

    }
}
