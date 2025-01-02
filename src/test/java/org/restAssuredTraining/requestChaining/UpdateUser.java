package org.restAssuredTraining.requestChaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    @Test
    void testUpdateUser(ITestContext context) {
        Faker faker = new Faker();
        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("email", faker.internet().emailAddress());
        data.put("status", "active");

        int userId = (int) context.getSuite().getAttribute("userId");
        String accessToken = (String) context.getSuite().getAttribute("accessToken");

        given()
                .pathParam("accessLevel", "public")
                .pathParam("apiVersion", "v2")
                .pathParam("dataType", "users")
                .pathParam("id", userId)
                .header("Authorization", "Bearer " + accessToken)
                .contentType("application/json")
                .body(data.toString()).log().body()
                .when().put("https://gorest.co.in/{accessLevel}/{apiVersion}/{dataType}/{id}")
                .then().statusCode(200);
    }
}
