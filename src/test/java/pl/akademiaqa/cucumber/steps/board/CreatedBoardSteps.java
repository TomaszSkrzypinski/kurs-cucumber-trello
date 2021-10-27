package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.boards.CreateBoardRequest;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class CreatedBoardSteps {

    private final CreateBoardRequest createBoardRequest;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;

    private Exception actualException;


    @When("I create new board")
    public void i_create_new_board() {
        createNewBoard();
    }

    @When("I create new board without authentication")
    public void i_create_new_board_without_authentication() {
        try {
        createNewBoard(CommonValues.BOARD_NAME, HttpStatus.SC_UNAUTHORIZED);
        } catch (Exception e) {
            actualException = e;
        }
    }

    @Given("the board already exist")
    public void the_board_already_exist() {
        createNewBoard();
    }

    @When("I create new board with name was started at {string}")
    public void i_create_new_board_with_name_was_started_at(String name) {
        createNewBoard(name, HttpStatus.SC_OK);
    }

    @When("I create new board with empty {string}")
    public void i_create_new_board_with_empty(String name) throws Throwable {
        try {
            createNewBoard(name, HttpStatus.SC_BAD_REQUEST);
        } catch (Exception e) {
            actualException = e;
        }
    }

    @Then("I got a {string}")
    public void i_got(String Exception) throws Throwable {
        Assertions.assertThat(actualException.toString()).isEqualTo(Exception);
    }

    private void createNewBoard() {
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addQueryParam("name", CommonValues.BOARD_NAME);

        responseHandler.setResponse(createBoardRequest.createBoard(requestHandler));
        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        context.addBoards(CommonValues.BOARD_NAME, responseHandler.getId());
    }

    private void createNewBoard(String name, int statusCode) {
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addQueryParam("name", name);

        responseHandler.setResponse(createBoardRequest.createBoard(requestHandler));
        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(statusCode);

        context.addBoards(name, responseHandler.getId());
    }
}