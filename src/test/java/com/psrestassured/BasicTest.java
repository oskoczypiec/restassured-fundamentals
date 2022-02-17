package com.psrestassured;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class BasicTest {
    @Test
    public void someTest(){
        RestAssured.get("https://api.github.com/")
                .then()
                .statusCode(200);
    }
}
