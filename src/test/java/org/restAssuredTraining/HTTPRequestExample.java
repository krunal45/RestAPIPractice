package org.restAssuredTraining;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/*
given() > used to specify any pre-requisite of method
when()  > used to specify the method type like get | post | delete etc.,
then()  > used for assertions
 */

public class HTTPRequestExample {
    int id;

    @Test(priority =3,dependsOnMethods = {"createUser"})
    void getUser() {
        given()
                .when().get("https://reqres.in/api/users/2")
                .then().statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("support.text", equalTo("Tired of writing endless social media content? Let Content Caddy generate it for you."))
                .log().all();
    }

    @Test (priority = 1)
    void createUser(){
        HashMap<String,String> data = new HashMap<>();
        data.put("name", "Krunal");
        data.put("job", "QA");

        id = given()
                .contentType("application/json")
                .body(data)
                .when().post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

        System.out.println("user ID > "+id);
    }

    @Test(priority = 2,dependsOnMethods = {"createUser"})
    void updateUser(){
        HashMap<String,String> data = new HashMap<>();
        data.put("name", "Krunal");
        data.put("job", "SDET");

        given()
                .contentType("application/json")
                .body(data)
                .when().put("https://reqres.in/api/users/"+id)
                .then().statusCode(200)
                .body("name", equalTo(data.get("name")))
                .body("job", equalTo(data.get("job")))
                .log().all();
    }

    @Test(priority =4,dependsOnMethods = {"createUser"})
    void deleteUser(){
        given()
                .when().delete("https://reqres.in/api/users/"+id)
                .then().statusCode(204)
                .log().all();
    }
}
