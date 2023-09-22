package org.restAssured.example;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.List;

public class Example11 {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/";
        Response response = RestAssured.given()
                .body("{\n" +
                        "    \"name\": \"Krunal\",\n" +
                        "    \"salary\": \"1000\",\n" +
                        "    \"age\": \"35\"\n" +
                        "}")
                .when().post("create")
                .then().assertThat().statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        jsonPath.getString("status").equals("success");

        String name = jsonPath.getString("data.name");
        String salary = jsonPath.getString("data.salary");
        String age = jsonPath.getString("data.age");
        String id = jsonPath.getString("data.id");
        System.out.println("name : " + name);
        System.out.println("salary : " + salary);
        System.out.println("age : " + age);
        System.out.println("id : " + id);
    }
}
