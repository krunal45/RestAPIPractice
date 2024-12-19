package com.paypal.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class CreateOrder {

    @BeforeMethod
    public static String generateAccessToken() {
        RestAssured.baseURI = "https://api-m.sandbox.paypal.com";
        Map<String, String> formParams = new HashMap<>();
        formParams.put("grant_type", "client_credentials");
        formParams.put("ignoreCache", "true");
        formParams.put("return_authn_schemes", "true");
        formParams.put("return_client_metadata", "true");
        formParams.put("return_unconsented_scopes", "true");

        Map<String, String> headers = new HashMap<>();
        headers.put("Cache-Control", "no-cache");
        headers.put("Host","api-m.sandbox.paypal.com");
        headers.put("Accept-Encoding","gzip, deflate, br");

        String clientId = "AUv8rrc_P-EbP2E0mpb49BV7rFt3Usr-vdUZO8VGOnjRehGHBXkSzchr37SYF2GNdQFYSp72jh5QUhzG";
        String clientSecret = "EMnAWe06ioGtouJs7gLYT9chK9-2jJ--7MKRXpI8FesmY_2Kp-d_7aCqff7M9moEJBvuXoBO4clKtY0v";

        Response response = given().log().all()
                .headers(headers)
                .auth().preemptive().basic(clientId, clientSecret)
                .formParams(formParams).when().post("/v1/oauth2/token")
                .then().statusCode(200)
                .extract().response();

        JsonPath jsonPath = new JsonPath(response.asPrettyString());
        return jsonPath.getString("access_token");
    }

    public static void createOrderRest(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("PayPal-Request-Id", "A v4 style guid");
        headers.put("Authorization", "Bearer "+generateAccessToken());

        Response response = given().log().body()
                .headers(headers)
                .body(new File("src/test/java/resources/requestBody/createOrder.json"))
                .when().log().body().post("/v2/checkout/orders")
                .then().log().all().statusCode(200)
                .extract().response();

        JsonPath jsonPath = new JsonPath(response.asPrettyString());
        Assert.assertEquals(jsonPath.get("intent"), "CAPTURE","Fail: Intent is not capture!");
        Assert.assertEquals(jsonPath.get("status"), "CREATED", "Fail: Status is not created!");
    }


    public static void main(String[] args) {
         createOrderRest();
    }
}
