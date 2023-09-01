package org.restAssured.example.RequestSpecBuilderExamples;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;

public class Example3 {

    JsonPath path;

    public RequestSpecification requestBuilder() {
        return new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/api/v1")
                .setBasePath("/employees")
                .setAccept(ContentType.JSON).build();
    }

    public ResponseSpecification responseBuilder() {
        return new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    public String sendRequest() {
        return given().spec(requestBuilder()).log().all().when().get()
                .then().log().all().spec(responseBuilder()).extract().response().asString();
    }

    @Test
    public void validateResponse() {
        path = new JsonPath(sendRequest());
        String status = path.getString("status");
        String message = path.getString("message");
        Map<String, Object> expectedEmpData = new HashMap<>();
        expectedEmpData.put("id", 24);
        expectedEmpData.put("employee_name", "Doris Wilder");
        expectedEmpData.put("employee_salary", 85600);
        expectedEmpData.put("employee_age", 23);
        Map<String,Object> actualEmpData = new HashMap<>();
        actualEmpData.put("id", path.getInt("data[23].id"));
        actualEmpData.put("employee_name", path.getString("data[23].employee_name"));
        actualEmpData.put("employee_salary", path.getInt("data[23].employee_salary"));
        actualEmpData.put("employee_age", path.getInt("data[23].employee_age"));

        Assert.assertEquals(status, "success", "FAIL:Status Values mismatch!!");
        Assert.assertEquals(message, "Successfully! All records has been fetched.", "FAIL: message Values mismatch!!");
        for (Map.Entry<String, Object> empData : expectedEmpData.entrySet()) {
            if (actualEmpData.containsKey(empData.getKey())) {
                Assert.assertTrue(actualEmpData.containsValue(empData.getValue()), "FAIL: " + empData.getKey() + " value mismatch!!");
            }
        }
    }
}
