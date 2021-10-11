package pl.akademiaqa.cucumber.steps.hooks;

import io.cucumber.java.After;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.boards.DeleteBoardRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class Hooks {

    private final Context context;
    private final RequestHandler requestHandler;
    private final DeleteBoardRequest deleteBoardRequest;

    @After(value = "@cleanup")
    public void afterScenario(){


        context.getBoards().values()
                .forEach(boardId -> {
                    requestHandler.setEndpoint(TrelloUrl.BOARDS);
                    requestHandler.addPathParam("id", boardId);
                    Response response = deleteBoardRequest.deleteBoardRequest(requestHandler);
                    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
                });
    }
}
