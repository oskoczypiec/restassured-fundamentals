package com.psrestassured;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestExample {

    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void hamcrestExample(){
        assertThat(10, lessThan(11));
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .statusCode(equalTo(200))
                .statusCode(lessThan(300));

        RestAssured.get(BASE_URL)
                .then()
                .header("etag", notNullValue())
                .header("etag", not(emptyString()))
                .statusCode(anyOf(equalTo(200), equalTo(202)));
    }
}
