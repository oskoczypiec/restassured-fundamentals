package com.psrestassured.betterTests;

import com.psrestassured.AnotherUser;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.psrestassured.ConfigFactory.*;

public class Config1 {
    public static final String BASE_URL = "https://api.github.com/";

    @BeforeSuite
    void setup(){
        RestAssured.config = getDefaultConfig();
    }

    @Test
    public void cleanTestWithHiddenConfig(){
        AnotherUser user = RestAssured.get(BASE_URL + "users/rest-assured")
                .as(AnotherUser.class);
    }
}
