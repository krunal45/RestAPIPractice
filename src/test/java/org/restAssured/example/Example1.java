package org.restAssured.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Example1 {

    @Test(enabled = false)
    public void test1() {

        RestAssured.baseURI = "https://reqres.in/";
//        given().log().all().header("Content-Type", "application/json").body("{\n" +
//                        "    \"name\": \"morpheus\",\n" +
//                        "    \"job\": \"leader\"\n" +
//                        "}").when().log().all().post("api/users").then().assertThat().statusCode(201)
//                .body("name", equalToObject("morpheus"))
//                .header("Connection", "keep-alive");

        String response = given().header("Content-Type", "application/json").body("{\n" +
                "  \"name\": \"morpheus\",\n" +
                "  \"job\": \"leader\"\n" +
                "}").when().post("api/users").then().extract().response().asPrettyString();
        System.out.println("--Response--" + response);
        JsonPath jsonPath = new JsonPath(response);
        String name = jsonPath.getString("name");
        System.out.println("--Name-- :" + name);
    }

    @Test
    public void createUser() {
        RestAssured.baseURI = "https://gorest.co.in";
        String response = given().log().all().header("Content-Type", "application/json").header("Authorization","Bearer 18c9caaf7a49d07943da5306092cdb8c759e03df2e98c282c2b4ab03177ecc8d").body("{\n" +
                        "    \"name\": \"Krunal\",\n" +
                        "    \"email\": \"k1@test.com\",\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}").when().post("/public/v2/users").then().log().all().assertThat().statusCode(201)
                .body("name", equalToObject("Krunal"))
                .body("email", equalToObject("k1@test.com"))
                .body("gender", equalToObject("male"))
                .body("status", equalToObject("active"))
                .extract().response().asPrettyString();

        JsonPath jsonPath = new JsonPath(response);
        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String email = jsonPath.getString("email");
        String gender = jsonPath.getString("gender");
        String status = jsonPath.getString("status");

        //Validating if user got created successfull via GET API Endpoint.
        given().log().all().header("Content-Type", "application/json").header("Authorization","Bearer 18c9caaf7a49d07943da5306092cdb8c759e03df2e98c282c2b4ab03177ecc8d").when().get("/public/v2/users/"+id).then().log().all()
                .assertThat().statusCode(200)
                .body("id", equalToObject(id))
                .body("name",equalToObject(name))
                .body("email",equalToObject(email))
                .body("gender",equalToObject(gender))
                .body("status",equalToObject(status));

    }
}