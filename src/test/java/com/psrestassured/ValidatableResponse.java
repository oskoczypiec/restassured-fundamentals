package com.psrestassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.hamcrest.number.OrderingComparison;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

public class ValidatableResponse {
    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void basicValidatableExample(){
        RestAssured.get(BASE_URL)
                .then()
                .assertThat()
                    .statusCode(200)
                .and()
                    .contentType(ContentType.JSON)
                .and().assertThat()
                    .header("x-ratelimit-limit", "60");
    }

    @Test
    public void simpleHamcrestMatchers(){
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .statusCode(lessThan(300))
                .header("cache-control", Matchers.containsStringIgnoringCase("max-age=60"))
                .time(lessThan(3L), TimeUnit.SECONDS)
                .header("etag", Matchers.notNullValue())
                .header("etag", Matchers.not(Matchers.emptyString()))
                .header("x-ratelimit-limit", Integer::parseInt, Matchers.equalTo(60))
                //take the date as a string and parse it to datetimeformatter
                //and then using class from Hamcrest OrderingComparison compare prev date to date now
                .header("date", date -> LocalDate.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME),
                        OrderingComparison.comparesEqualTo(LocalDate.now()))
                //comparing values of 2 headers
                .header("x-ratelimit-limit", response -> greaterThan(response.header("x-ratelimit-remaining")));
    }

    @Test
    public void usingMapsToTestHeaders(){

        Map<String, String> expectedHeaders = Map.of("content-encoding", "gzip",
                "access-control-allow-origin", "*",
                "content-security-policy", "default-src 'none'");

        RestAssured.get(BASE_URL)
                .then()
                .headers("content-encoding", "gzip",
                        "access-control-allow-origin", "*",
                        "content-security-policy", "default-src 'none'")
                .headers(expectedHeaders);
    }

    @Test
    public void matcherExample(){

        RestAssured.get(BASE_URL)
                .then()
                .body("current_user_url", equalTo(BASE_URL + "user"))
                .body(containsString("feeds_url"))
                //can contain more than one matcher
                .body(containsString("feeds_url"), containsString("current_user_url"));
    }

    @Test
    public void complexBodyExample(){
        RestAssured.get(BASE_URL + "users/andrejs-ps")
                .then()
                .body("url", response -> Matchers.containsString(response.body().jsonPath().get("login")));
    }

    //example with multiple data
    //define multiple data
    @DataProvider
    public Object[][] names(){
        return new Object[][]{
                {"andrejs-ps"},
                {"rest-assured"}
        };
    }
    //use in the test
    @Test(dataProvider = "names")
    public void complesBodyExamplWithDp(String name){
        RestAssured.get(BASE_URL + "users/" + name)
                .then()
                .body("url", response -> Matchers.containsString(response.body().jsonPath().get("login")));
    }
}
