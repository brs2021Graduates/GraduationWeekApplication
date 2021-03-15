package cn.edu.brs._2021.service.utility;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    public static String generateErrorJson(@Nullable Map<String, Object> param) {
        if (param == null){
            param = new HashMap<>(1);
        }
        param.put("code", -1);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return new ObjectMapper().writeValueAsString(param);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String generateNormalJson(@Nullable Map<String, Object> param) {
        if (param == null){
            param = new HashMap<>(1);
        }
        param.put("code", 1);
        try {
            return new ObjectMapper().writeValueAsString(param);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String generateObjectJson(@NonNull Object param) {
        try {
            return new ObjectMapper().writeValueAsString(param);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
