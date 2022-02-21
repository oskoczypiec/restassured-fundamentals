package com.psrestassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

public class BodyTest {
    public static final String BASE_URL = "https://api.github.com/rate_limit";

    @Test
    public void jsonPathReturnsMap(){
        Response response = RestAssured.get(BASE_URL);

        ResponseBody<?> body = response.body();
        JsonPath jPath = body.jsonPath();
        //another way to take body in one line by chaining
        JsonPath jPath2 = response.body().jsonPath();

        Map<String, String> fullJson = jPath2.get();
        Map<String, String> subMap = jPath.get("resources");
        Map<String, String> subMap2 = jPath2.get("resources.core");

        int value = jPath2.get("resources.core.limit");
        int value2 = jPath2.get("resources.graphql.limit");

        assertEquals(value, 60);
        assertEquals(value2, 0);
    }
}
