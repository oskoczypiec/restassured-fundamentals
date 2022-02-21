package com.psrestassured.betterTests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.Test;

import static com.psrestassured.ConfigFactory.getDefaultConfig;

public class RequestSpecification {
    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void testUsingLocalRequestSpec(){
        RestAssured
                .given()
                    .spec(getDefaultRequestSpec())
                .when()
                    .get()
                .then()
                    .statusCode(200);
    }

    public static io.restassured.specification.RequestSpecification getDefaultRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setConfig(getDefaultConfig())
                .build();
    }
}
