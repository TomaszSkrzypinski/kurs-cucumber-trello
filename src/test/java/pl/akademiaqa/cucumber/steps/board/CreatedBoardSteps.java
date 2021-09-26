package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.boards.CreateBoardRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class CreatedBoardSteps {

    private final CreateBoardRequest createBoardRequest;
    private final RequestHandler requestHandler;

    @When("I create new board")
    public void i_create_new_board() {

        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addQueryParam("name", "THID IS NEW BOARD");

        Response response = createBoardRequest.createBoard(requestHandler);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

    }

    @Then("I see created board on the list")
    public void i_see_created_board_on_the_list() {

        // request GET na /boards
        //sprawdzenie czy board został dodany do listy

    }
}
