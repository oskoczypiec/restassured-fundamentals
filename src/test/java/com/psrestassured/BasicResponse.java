package com.psrestassured;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class BasicResponse {
    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void convenienceMethods(){
        Response response = RestAssured.get(BASE_URL);
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.getContentType(), "application/json; charset=utf-8");
    }

    @Test
    public void genericHeader(){
        Response response = RestAssured.get(BASE_URL);
        assertEquals(response.getHeader("server"), "GitHub.com");
        assertEquals(response.getHeader("x-ratelimit-limit"), "60");
    }

    @Test
    public void getHeaders(){
        Response response = RestAssured.get(BASE_URL);
        Headers headers = response.getHeaders();
        String val = headers.getValue("x-frame-options");
        int size = headers.size();
        List<Header> list = headers.asList();
        boolean isPresent = headers.hasHeaderWithName("content-encoding");
        assertTrue(isPresent);
    }

    @Test
    public void timeExample(){
        Response response = RestAssured.get(BASE_URL);
        System.out.println(response.getTime());
        System.out.println(response.getTimeIn(TimeUnit.MILLISECONDS));
    }
}
