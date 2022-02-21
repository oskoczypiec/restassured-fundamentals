package com.psrestassured.otherHTTPRequests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class OtherMethods {

    public static final String BASE_URL = "https://api.github.com/user/repos";
    public static final String TOKEN = "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";

    @Test(description = "Create a repo")
    public void postTest(){
        RestAssured
                .given()
                    .header("Authorization", "token " + TOKEN)
                    .body("{\"name\": \"deleteme\"}")
                .when()
                    .post(BASE_URL)
                .then()
                .statusCode(201); //created

    }
    @Test(description = "Patch a repo")
    public void patchTest(){
        RestAssured
                .given()
                    .header("Authorization", "token " + TOKEN)
                    .body("{\"name\": \"deleteme-patched\"}")
                .when()
                    .patch("https://api.github.com/repos/oskoczypiec/deleteme")
                .then()
                    .statusCode(200); // OK
    }

    @Test(description = "Delete a repo")
    public void deleteTest(){
        RestAssured
                .given()
                    .header("Authorization", "token " + TOKEN)
                .when()
                    .delete("https://api.github.com/repos/oskoczypiec/deleteme-patched")
                .then()
                    .statusCode(204); //no content
    }
}
