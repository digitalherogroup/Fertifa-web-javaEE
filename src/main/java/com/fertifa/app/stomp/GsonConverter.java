package com.fertifa.app.stomp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GsonConverter {

    private final Gson gson;

    public Map<String, Object> convertObjectToMapObject(Object obj) {
        String responseJson = gson.toJson(obj);
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        return gson.fromJson(responseJson, type);
    }

    public List<String> gsonObjectConverter(Object obj) {
        String responseJson = gson.toJson(obj);
        Type type = new TypeToken<List<Object>>() {
        }.getType();
        return gson.fromJson(responseJson, type);
    }
}
