package org.restAssured.example;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class Example6 {

    JsonPath path;
    String response;

    @BeforeTest
    void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response = given().log().all().header("Content-Type", "application/json").body(new File("src/test/java/resources/requestData.json"))
                .when().post("/booking")
                .then().log().all().extract().response().asPrettyString();
        path = new JsonPath(response);
    }

    @Test(enabled = false)
    void validateBookingID(){
        int expectedBookingID = 616;
        int actualBookingID = path.getInt("bookingid");
        Assert.assertEquals(actualBookingID, expectedBookingID, "FAIL: Booking ID's mismatch!");
    }

    @Test
    void validateFirstName(){
        String expectedFirstName = "Krunal";
        String actualFirstName = path.getString("booking.firstname");
        Assert.assertEquals(actualFirstName, expectedFirstName, "FAIL: first name values mismatch!!");
    }

    @Test
    void validateIfDepositPaid(){
        boolean expected = false;
        boolean actual = path.getBoolean("booking.depositpaid");
        Assert.assertEquals(actual, expected, "FAIL: Actual and expected mismatch!");
    }

    @Test
    void validateAdditionalNeeds(){
        String expectedValue = "Breakfast";
        String actualValue = path.getString("booking.additionalneeds");
        Assert.assertEquals(actualValue, expectedValue, "FAIL: Additional needs value mismatch !");
    }
}
