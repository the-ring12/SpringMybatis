package com.the_ring.util;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonSerializerUtil {

    public static String objectToString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return " ";
        }
    }

    public static Map deserialize(String jsonStr) {
        return jsonToObject(jsonStr, Map.class);
    }

    public static <T> T jsonToObject(String jsonStr, Class<T> classType) {
        try {
            return new ObjectMapper().readValue(jsonStr, classType);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
