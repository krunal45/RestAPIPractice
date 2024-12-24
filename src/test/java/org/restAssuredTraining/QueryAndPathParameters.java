package org.restAssuredTraining;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class QueryAndPathParameters {

    @Test
    void getUser(){
        //https://reqres.in/api/users?page=2
        given()
                .pathParam("param1", "users")
                .queryParam("page",2)
                .when().get("https://reqres.in/api/{param1}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
