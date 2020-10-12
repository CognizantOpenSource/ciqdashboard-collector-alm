package com.cognizant.collector.alm.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.env.Environment;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by 784420 on 7/3/2019 10:36 AM
 */
public class JsonUtil {


    private static ObjectMapper objectMapper;
    private static TypeReference<Map<String, String>> mapStringTypeRef = new TypeReference<Map<String, String>>() {
    };

    private JsonUtil() {
    }

    private static void setObjectMapper(ObjectMapper objectMapper) {
        JsonUtil.objectMapper = objectMapper;
    }

    public static String getJsonString(JsonNode jsonNode, String key) {
        return jsonNode.has(key) ? jsonNode.get(key).asText() : "";
    }

    public static List<String> getJsonStringList(JsonNode jsonNode, String key) {
        JavaType stringList = objectMapper.getTypeFactory().constructCollectionType(List.class, String.class);
        return objectMapper.convertValue(jsonNode.get(key), stringList);
    }

    public static Object getJsonClassList(JsonNode jsonNode, String key, Class className) {
        JavaType classList = objectMapper.getTypeFactory().constructCollectionType(List.class, className);
        return objectMapper.convertValue(jsonNode.get(key), classList);
    }

    /*Convert JsonNode to Map<String, String> and return*/
    public static Map<String, String> getJsonStringMap(JsonNode jsonNode, String key) {
        return objectMapper.convertValue(jsonNode.get(key), mapStringTypeRef);
    }

    /*Convert JsonNode to Map<keyClass, valueClass> and return*/
    public static Object getJsonClassMap(JsonNode jsonNode, String key, Class keyClass, Class valueClass) {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(Map.class, keyClass, valueClass);
        return objectMapper.convertValue(jsonNode.get(key), mapType);
    }

    /*Convert JsonNode to Map<keyClass, Collection<class>> and return*/
    public static Object getJsonCollectionMap(JsonNode jsonNode, String key, Class keyClass, Collection valueCollectionClass) {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(Map.class, keyClass, valueCollectionClass.getClass());
        return objectMapper.convertValue(jsonNode.get(key), mapType);
    }

    /*Convert JsonNode to Class Object and return*/
    public static Object getJsonClassObject(JsonNode jsonNode, String key, Class className) {
        return objectMapper.convertValue(jsonNode.get(key), className);
    }

    /*Creating ObjectMapper and assigning to the variable for Global access*/
    public static void init(Environment environment) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                Boolean.parseBoolean(environment.getProperty("spring.jackson.deserialization.FAIL_ON_UNKNOWN_PROPERTIES")));
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        JsonUtil.setObjectMapper(objectMapper);
    }

}
