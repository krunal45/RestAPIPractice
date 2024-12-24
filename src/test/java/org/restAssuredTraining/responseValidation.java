package org.restAssuredTraining;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class responseValidation {

    @Test(enabled = false)
    void getUserAppoach1() {
        //https://reqres.in/api/unknown
// Approach 1
        given()
                .pathParam("pathParam", "unknown")
                .when().get("https://reqres.in/api/{pathParam}")
                .then().statusCode(200)
                .and()
                .body("total", equalTo(12))
                .and()
                .body("data[0].name", equalTo("cerulean"))
                .and()
                .body("data[0].year", equalTo(2000));
    }


    @Test
    void getUserAppoach2() {
        Response response = given()
                .pathParam("pathParam", "unknown")
                .when().get("https://reqres.in/api/{pathParam}");

//        Validate status Code
        Assert.assertEquals(response.getStatusCode(), 200, "Fail: Status Code Mismatch");
        //Validating json fields
        Assert.assertEquals(response.getBody().jsonPath().getInt("total"), 12, "Fail: total Value Mismatch");
        Assert.assertEquals(response.getBody().jsonPath().getString("data[0].name"), "cerulean","Fail: name field mismatch");
    }
}
