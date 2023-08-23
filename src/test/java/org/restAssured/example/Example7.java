package org.restAssured.example;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resources.GoRestCreateUserRequest;

import java.util.Arrays;

public class Example7 {

    JsonPath path;
    String response;

    @BeforeTest
    void setUp() {
        RestAssured.baseURI = "https://gorest.co.in";
    }

    @Test(dataProvider ="dataProvider")
    void request(String name,String email,String gender,String status) {
        response = given().log().all().header("Content-Type","application/json").header("Authorization","Bearer 18c9caaf7a49d07943da5306092cdb8c759e03df2e98c282c2b4ab03177ecc8d").body(new GoRestCreateUserRequest().getRequest(name,email,gender,status)).when()
                .post("/public/v2/users")
                .then().log().all().assertThat().statusCode(201).extract().response().asPrettyString();
}


    @DataProvider(name = "dataProvider")
    @Test
    Object[][] provideData() {
        Object[][] data = {{"deep", "deep34@gmaill.com", "male", "active"}};
        System.out.println(Arrays.deepToString(data));
        return data;
    }
}
