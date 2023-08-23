package org.restAssured.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Example5 {


    JsonPath path;
    String response;

    @BeforeTest
    void setup() {
        RestAssured.baseURI = "https://api.realworld.io/";
        response = given().queryParam("offset", 0).queryParam("limit", 10).
                when().get("api/articles")
                .then().extract().response().asPrettyString();
        path = new JsonPath(response);
    }

    @Test
    void verifyArticlesCount() {
        int expectedArticlesCount = 197;
        int actualArticlesCount = path.getInt("articlesCount");
        Assert.assertEquals(actualArticlesCount, expectedArticlesCount, "FAIL: Articles count mismatch.");
    }

    @Test
    void verifyAuthorUserName() {
        String expectedAuthorName = "Anah Benešová";
        String actualAuthorName = path.getString("articles[0].author.username");
        Assert.assertEquals(actualAuthorName, expectedAuthorName, "FAIL: Author Name mismatch!!");
    }

    @Test
    void verifyTagListItems(){
        List<String> tagList = new ArrayList<>();
        tagList = path.getList("articles[0].tagList");
        boolean flag = tagList.contains("voluptate");
        Assert.assertTrue(flag, "FAIL: Item not present");
    }


}
