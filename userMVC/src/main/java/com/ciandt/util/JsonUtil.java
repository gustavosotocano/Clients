package com.ciandt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T deserializeJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
            return null; // Return null if deserialization fails
        }
    }

    public static Object deserializeJson(String json) {
        try {

            return objectMapper.readValue(json,Object.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
            return null; // Return null if deserialization fails
        }
    }


    public static String serializeJson(Object myObject) {
        try {
            return objectMapper.writeValueAsString(myObject);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
            return "{}"; // Return empty JSON object if serialization fails
        }
    }
}