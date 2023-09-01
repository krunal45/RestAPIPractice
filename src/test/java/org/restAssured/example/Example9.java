package org.restAssured.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class Example9 {

    public static void main(String[] args) {
        pojo obj = new pojo();
        obj.setFirstname("Jim");
        obj.setLastName("Brown");
        obj.setTotalPrice(111);
        obj.setDepositPaid(true);

        Map<String,String> map = new HashMap<>();
        map.put("checkin", "2023-08-31");
        map.put("checkout", "2023-09-01");
        obj.setBookingdates(map);
        obj.setAdditionalneeds("Breakfast");

        RequestSpecification request = new RequestSpecBuilder()
        .setBaseUri("https://restful-booker.herokuapp.com")
                .setAccept("*/*").setBasePath("/booking")
                .addHeader("Content-Type", "application/json")
                .setBody(obj).build();
        request = given().spec(request).log().all();

        Response response = request.when().post().then().log().all().assertThat().statusCode(200).extract().response();
        String responseString = response.asPrettyString();
        System.out.println(responseString);
    }
}
