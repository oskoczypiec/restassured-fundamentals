package com.psrestassured;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ValidatableResponseRepeatingItems {

    public static final String BASE_URL = "https://reqres.in/api/users?page=1";

    @Test
    public void repeatingItems(){
        RestAssured.get(BASE_URL)
                .then()
                .body("data.id[0]", equalTo(1))
                .body("data.id[1]", equalTo(2))
                .body("data.first_name[2]", equalTo("Emma"))
                .body("data.first_name[3]", equalTo("Eve"))
                .body("data.first_name", hasItem("Charles"))
                .body("data.first_name", hasItems("Emma", "Eve"))
                .body ("data.first_name", hasItem(endsWith("ma")));
    }
}
