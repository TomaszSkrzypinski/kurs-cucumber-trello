package pl.akademiaqa.handlers.trello;

import groovy.lang.Singleton;
import lombok.SneakyThrows;

import java.util.Properties;

@Singleton
public class TrelloAuthentication {

    private static final Properties PROPERTIES = new Properties();
    private static final String KEY = "key";
    private static final String TOKEN = "token";

    @SneakyThrows
    public TrelloAuthentication(){
        PROPERTIES.load(getClass().getClassLoader().getResourceAsStream("trello.properties"));
    }

    public String getKey() {
        return PROPERTIES.getProperty(KEY);
    }

    public String getToken() {
        return PROPERTIES.getProperty(TOKEN);
    }
}