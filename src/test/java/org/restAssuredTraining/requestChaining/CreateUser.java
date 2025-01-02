package org.restAssuredTraining.requestChaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class CreateUser {

    @Test
    void testCreateUser(ITestContext context){
        Faker faker = new Faker();
        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("gender","male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "active");

        String accessToken = "18c9caaf7a49d07943da5306092cdb8c759e03df2e98c282c2b4ab03177ecc8d";

        int id = given()
                .pathParam("accessLevel", "public")
                .pathParam("apiVersion", "v2")
                .pathParam("dataType", "users")
                .header("Authorization", "Bearer "+accessToken)
                .contentType("application/json")
                .body(data.toString()).log().body()
                .when().post("https://gorest.co.in/{accessLevel}/{apiVersion}/{dataType}")
                .jsonPath().getInt("id");

        System.out.println("ID > "+id);
        context.getSuite().setAttribute("userId",id);
        context.getSuite().setAttribute("accessToken",accessToken);
    }
}
