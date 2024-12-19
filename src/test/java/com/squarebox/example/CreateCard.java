package com.squarebox.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.Matchers.lessThan;

public class CreateCard {

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "https://5786855b-f646-4b87-8932-7cebd43a1790.mock.pstmn.io";
        RestAssured.basePath = "v2/payments";
    }

    @Test
    public void createCardTest() {
        Response response = RestAssured.given().header("Content-Type", "application/json")
                .body(new File("src/test/java/resources/requestBody/createCard.json"))
                .when().log().all().post().then().log().all()
                .assertThat().statusCode(200)
                .assertThat().statusLine("HTTP/1.1 200 OK")
                .assertThat().contentType("application/json")
                .assertThat().headers("Content-Type", "application/json; charset=utf-8","Connection","keep-alive")
                .extract().response();

        JsonPath jsonPath = new JsonPath(response.asPrettyString());
        String id = jsonPath.getString("payment.id");
        Assert.assertEquals(id, "d2e1aLJjAFABSLD7se39xLU6ivfZY", "Fail: id value mis-match!");
    }
}
