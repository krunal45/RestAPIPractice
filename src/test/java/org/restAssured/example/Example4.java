package org.restAssured.example;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

public class Example4 {

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://api.realworld.io";
    }

    @Test
    public void verifyTags(){
        given().
                when().
                get("/api/tags")
                .then()
                .body("tags",hasItems("welcome","codebaseShow"));
    }
}
