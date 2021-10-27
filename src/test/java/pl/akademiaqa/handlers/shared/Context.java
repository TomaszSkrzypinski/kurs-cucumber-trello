package pl.akademiaqa.handlers.shared;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Context {

    private Map <String, String> boards = new HashMap <>();
    private Map <String, String> lists = new HashMap <>();
    private Map <String, String> cards = new HashMap <>();

    public void addBoards(String boardName, String boardId) {
        boards.put(boardName, boardId);
    }

    public void addLists(String listName, String listId) {
        lists.put(listName, listId);
    }

    public void addCards(String cardName, String cardId) {
        cards.put(cardName, cardId);
    }
}
