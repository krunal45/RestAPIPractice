package org.restAssured.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Example10 {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://www.boredapi.com/";
        Response response = RestAssured.given().when().get("api/activity").then().assertThat().statusCode(200).extract().response();
        JsonPath path = response.jsonPath();
        String[] activities = new String[7];
        activities[0] = path.getString("activity");
        activities[1] = path.getString("type");
        activities[2] = path.getString("participants");
        activities[3] = path.getString("price");
        activities[4] = path.getString("link");
        activities[5] = path.getString("key");
        activities[6] = path.getString("accessibility");

        for (String activity : activities) {
            System.out.println(activity);
        }
    }
}
