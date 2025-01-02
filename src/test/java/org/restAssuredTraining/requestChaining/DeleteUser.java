package org.restAssuredTraining.requestChaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    @Test
    void testDeleteUser(ITestContext context){
        int userId = (int) context.getSuite().getAttribute("userId");
        String accessToken = (String) context.getSuite().getAttribute("accessToken");

        given()
                .pathParam("accessLevel", "public")
                .pathParam("version", "v2")
                .pathParam("dataType", "users")
                .pathParam("id", userId)
                .header("Authorization", "Bearer "+accessToken)
                .when().delete("https://gorest.co.in/{accessLevel}/{version}/{dataType}/{id}")
                .then().statusCode(204);
    }
}
