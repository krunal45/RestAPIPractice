package org.restAssured.example.RequestSpecBuilderExamples;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class Example2 {

    JsonPath path;

    RequestSpecification buildRequest() {
        return new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com")
                .setBasePath("/api/v1/create")
                .setAccept(ContentType.JSON)
                .setBody("{\n" +
                        "  \"name\": \"Krunal1\",\n" +
                        "  \"salary\": \"1001\",\n" +
                        "  \"age\": \"35\"\n" +
                        "}").build();
    }


    ResponseSpecification specifyResponse() {
        return new ResponseSpecBuilder().expectContentType(ContentType.JSON)
                .expectStatusCode(200).build();
    }

    Response sendRequest() {
        return given().spec(buildRequest()).log().all().when().log().all().post()
                .then().log().all().spec(specifyResponse()).extract().response();
    }

    @Test
    void verifyResponse() {
        String responseBody = sendRequest().asString();
        path = new JsonPath(responseBody);
        String status = path.getString("status");
        String message = path.getString("message");
        Integer id = path.getInt("data.id");

        Assert.assertEquals(status, "success", "FAIL: Status value mismatch!");
        Assert.assertEquals(message, "Successfully! Record has been added.", "FAIL: message mismatch!");
        Assert.assertTrue(id instanceof Integer, "FAIL: id is not an instance of Integer!");
    }
}
