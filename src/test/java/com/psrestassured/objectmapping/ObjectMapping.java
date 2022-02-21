package com.psrestassured.objectmapping;

import com.psrestassured.User;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ObjectMapping {
    public static final String BASE_URL = "https://api.github.com/users/rest-assured";

    @Test
    public void objectMapptingTestOne(){
        User user = RestAssured.get(BASE_URL)
                .as(User.class);

        assertEquals("rest-assured", user.getLogin());
        assertEquals(19369327, user.getId());
        assertEquals(2, user.getPublicRepos());
    }

    @Test
    public void objectMappingRelyingOnMapperType(){
        User user = RestAssured.get(BASE_URL)
                .as(User.class, ObjectMapperType.JACKSON_2);

        assertEquals("rest-assured", user.getLogin());
    }
}
