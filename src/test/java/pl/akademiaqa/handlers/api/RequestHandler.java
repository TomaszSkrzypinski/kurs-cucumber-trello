package pl.akademiaqa.handlers.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
public class RequestHandler {

    @Setter
    private String endpoint;
    private Map<String, String> queryParams = new HashMap();
    private Map<String, String> pathParams = new HashMap();

    public void addQueryParam(String key, String value){
        queryParams.put(key, value);
    }
    public void addPathParam(String key, String value){
        pathParams.put(key, value);
    }
}
