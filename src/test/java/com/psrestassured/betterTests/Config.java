package com.psrestassured.betterTests;

import io.restassured.RestAssured;
import io.restassured.config.FailureConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.listener.ResponseValidationFailureListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class Config {
    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void maxRedirectsTest(){
        /* With his will fail, because of expected redirects 0

        RestAssured.config = RestAssured.config()
                .redirect(RedirectConfig.redirectConfig().followRedirects(true).maxRedirects(0));
        */
        RestAssured.get(BASE_URL + "repos/twitter/bootstrap")
                .then()
                .statusCode(equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void failureConfigExample(){
        ResponseValidationFailureListener failureListener =
                (reqSpec, resSpec, response) ->
                        System.out.printf("We have a failure, "+
                                "response status was %s and the body contained: %s",
                                response.statusCode(), response.body().asPrettyString());

        RestAssured.config = RestAssured.config()
                .failureConfig(FailureConfig.failureConfig().failureListeners(failureListener));

        RestAssured.get(BASE_URL + "users/andrejs-ps")
                .then()
                .body("some.path", equalTo("a thing"));
    }
}
