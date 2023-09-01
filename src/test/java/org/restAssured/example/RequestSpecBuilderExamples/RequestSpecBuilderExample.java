package org.restAssured.example.RequestSpecBuilderExamples;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class RequestSpecBuilderExample {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    JsonPath path;

    @BeforeTest
    void buildRequest() {
        requestSpecification = new RequestSpecBuilder().setBaseUri("https://reqres.in")
                .setBasePath("/api/users")
                .setBody("{\n" +
                        "    \"name\": \"Krunal\",\n" +
                        "    \"job\": \"Bhatt\"\n" +
                        "}")
                .setAccept(ContentType.JSON).build();
    }

    @BeforeTest(dependsOnMethods = {"buildRequest"})
    void specifyResponse() {

        responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(201).build();
    }

    @BeforeTest(dependsOnMethods = {"specifyResponse"})
    void sendRequest() {
        response = given().spec(requestSpecification).log().all().when().log().all().post().then().log().all().spec(responseSpecification).extract().response();
    }

    @Test
    void validateResponse() {
        String responseBody = response.asPrettyString();
        path = new JsonPath(responseBody);
        String id = path.getString("id");
        Assert.assertTrue(id instanceof String,"FAIL: id is not an String.");
    }

}
